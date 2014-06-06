package controllers

import play.api.Play.current
import play.api.mvc._
import play.api.libs.json._
import play.api.{Play, Routes}
import play.api.libs.ws.WS

import org.joda.time._

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.ning.http.client.Realm.AuthScheme
import scala.concurrent.Future
import play.api.mvc.Security.{AuthenticatedRequest, AuthenticatedBuilder}
import com.ning.http.util.Base64
import java.nio.charset.Charset
import play.api.libs.json.JsArray
import play.api.libs.json.JsObject
import collection.JavaConversions._

object Identified extends AuthenticatedBuilder(
  // Yes, not a for comprehension. I argue that this happens to be more readable for this case,
  // as we're just passing a single value through a series of transformations.
  _.headers.get("Authorization")
    .map(_.substring(6))
    .map(Base64.decode(_))
    .map(new String(_, Charset.forName("UTF-8")))
    .map(_.split(":")(0))
)

trait NerdStatsController {
  this: Controller =>

  // just get 'em - if there's a problem here the server shouldn't even come up
  val fastFood = Play.configuration.getStringList("fastFood").get
  val nerdyPlaces = Play.configuration.getStringList("nerdyPlaces").get
  val apiKey = Play.configuration.getString("apiKey").get
  val apiUrl = Play.configuration.getString("apiBaseUrl").get

  // Geezeo API convenience method. Able to be real simple because we only
  // need JSON GET requests in this app. This also makes it a lot easier to mock.
  def apiCall(url: String) = {
    WS.url(apiUrl + url).withAuth(apiKey, null, AuthScheme.BASIC).get.map(response => Json.parse(response.body))
  }

  def userUrl[A](url: String)(implicit request: AuthenticatedRequest[A, String]) =
    "/api/v2/users/" + request.user + url

  def index = Action.async {
    apiCall("/api/v2/users").map(
      // I think this demo will survive on only the first page of users
      users => Ok(views.html.index((users\"users").as[Seq[JsValue]].
        filter(u => (u\"id").asOpt[String].nonEmpty)))
    )
  }

  def frequencyDays(freq: String): Double = freq match {
    case "Daily" => 1
    case "Every four weeks" => 28
    case "Every other week" => 14
    case "Weekly" => 7
    case "Yearly" => 365
    // These are unsupported for this example:
    // "Every six months", "Monthly", "Once", "Quarterly", "Twice a month",
  }

  def getStats = Identified.async {
    implicit request =>
    for {
      transactions <- apiCall(userUrl("/transactions"))
      restOfTransactions <- Future.sequence((2 to (transactions\"meta"\"total_pages").as[Int]).map(
        page => apiCall(userUrl("/transactions?page="+page))))
      incomesJson <- apiCall(userUrl("/cashflow/incomes"))
    } yield {
      val allTransactions =
        (transactions \\ "transactions" ++ restOfTransactions.flatMap(_ \\ "transactions")).
        foldLeft(new JsArray())((arr, i) => arr ++ i.as[JsArray]).
        as[Seq[JsValue]]

      // hooray for lexically sortable date formats
      val oldest = allTransactions.map(t => (t\"posted_at").as[String]).min(Ordering.by((s:String) => s))
      val days = (DateTime.now().getMillis() - DateTime.parse(oldest).getMillis())/(1000*60*60*24)
      val incomes = (incomesJson\"incomes").asOpt[Seq[JsObject]].getOrElse(Seq())

      def transactionTimes(transactions: Seq[JsValue]) =
        transactions.map(t => DateTime.parse((t\"posted_at").as[String]).getMillis())

      def transactionsForNicknames(nicks: Seq[String]) =
        allTransactions.filter(t => nicks.contains((t \ "nickname").as[String]))

      def spendingByNicknames(nicks: Seq[String]) = transactionsForNicknames(nicks).
        foldLeft(0.0)((sum, transaction) =>
        sum + (transaction\"balance").as[JsNumber].value.toDouble
        )

      def median(s: Seq[Double]): Double = {
        val sorted = s.sortWith(_<_)
        ((sorted(s.length/2)+sorted(s.length/2+(1-s.length%2)))/2.0)
      }

      def deltas(s: Seq[Long]) = s.zip(s.drop(1)).map(pair => pair._1-pair._2)

      val fastFoodSpending = spendingByNicknames(fastFood)
      val fastFoodXtions: Seq[JsValue] = transactionsForNicknames(fastFood)
      val fastFoodTimes = transactionTimes(fastFoodXtions)
      val nerdySpending = spendingByNicknames(nerdyPlaces)
      val nerdyXtions: Seq[JsValue] = transactionsForNicknames(nerdyPlaces)
      val nerdyTimes = transactionTimes(nerdyXtions)

      val totalIncome = incomes.filter(i => (i\"stopped_on").asOpt[String].isEmpty).
        // don't use floats for money etc etc. Should be okay for this calculation.
        foldLeft(0.0)((sum, income) =>
        sum + ((income\"amount").as[String].toDouble / frequencyDays((income\"frequency").as[String]))
        ) match {
          case 0.0 | Double.NegativeInfinity | Double.PositiveInfinity | Double.NaN =>
            // No cashflow set up, or some other madness. Fall back to scanning transactions.
            allTransactions.filter(t => (t\"transaction_type").as[String] == "Credit").
                            map(t => (t\"balance").as[Double]).sum / days
          case amt => amt
      }

      val neckbeardLength = (nerdySpending + fastFoodSpending * fastFoodSpending) / days / totalIncome
      val fastFoodDelay = median(deltas(fastFoodTimes).map(_.toDouble))
      val nextFastFoodCost = median(fastFoodXtions.map(t => (t \ "balance").as[Double]))
      val nerdDelay = median(deltas(nerdyTimes).map(_.toDouble))
      val nextNerdCost = median(nerdyXtions.map(t => (t \ "balance").as[Double]))

      Ok(views.html.stats(
        neckbeardLength,
        new LocalDateTime(fastFoodTimes.max + fastFoodDelay.toLong).toString("MM/dd/yyyy ha"),
        nextFastFoodCost,
        new LocalDateTime(nerdyTimes.max + nerdDelay.toLong).toString("MM/dd/yyyy ha"),
        nextNerdCost))
    }
  }

  def javascriptRoutes = Action { implicit request =>
    Ok(Routes.javascriptRouter("jsRoutes")(
       routes.javascript.NerdStatsController.getStats)).as(JAVASCRIPT)
  }
}

object NerdStatsController extends Controller with NerdStatsController