package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import play.api.mvc.{SimpleResult, Controller}
import controllers.NerdStatsController
import play.api.libs.ws.{Response, WS}
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import play.api.libs.json.Json._

/**
 * Specs2 tests
 */
class NerdStatsControllerSpec extends Specification {
  val AUTH_ENCODED = "Basic amRlbmlzb246"
  val AUTH_DECODED = "jdenison"

  class TestController() extends Controller with NerdStatsController {
    override  def apiCall(url: String) = Future[JsValue] {
      System.out.println(url)
      url match {
        case "/api/v2/users" => Json.parse("{\"meta\":{\"current_page\":1,\"total_pages\":1},\"users\":[{\"id\":null,\"custom_tags\":[],\"login\":\"sadie\",\"email\":\"sadie@example.com\",\"login_count\":612,\"last_login_at\":\"2012-03-13T17:26:46Z\",\"first_name\":\"Sadie\",\"last_name\":\"Hawkins\",\"postal_code\":\"02556\",\"birth_year\":1984,\"sex\":\"Female\"},{\"id\":null,\"custom_tags\":[],\"login\":\"shanon_boyer\",\"email\":\"shanon_boyer@example.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Shanon\",\"last_name\":\"Boyer\",\"postal_code\":\"73985\",\"birth_year\":1945,\"sex\":\"Male\"},{\"id\":null,\"custom_tags\":[],\"login\":\"ashley\",\"email\":\"kdaigle+ashley@geezeo.com\",\"login_count\":1,\"last_login_at\":null,\"first_name\":\"Ashley\",\"last_name\":\"Szwec\",\"postal_code\":\"06084\",\"birth_year\":1901,\"sex\":\"Female\"},{\"id\":null,\"custom_tags\":[],\"login\":\"thompstr1\",\"email\":\"tnt2776@gmail.com\",\"login_count\":1,\"last_login_at\":null,\"first_name\":\"Trena\",\"last_name\":\"Thompson\",\"postal_code\":\"60607\",\"birth_year\":1976,\"sex\":\"Female\"},{\"id\":null,\"custom_tags\":[],\"login\":\"Jeffcityjon\",\"email\":\"Jon_forck@ctsinc.biz\",\"login_count\":2,\"last_login_at\":null,\"first_name\":\"Jonathan\",\"last_name\":\"Forck\",\"postal_code\":\"65109\",\"birth_year\":1981,\"sex\":\"Male\"},{\"id\":null,\"custom_tags\":[],\"login\":\"jdayton\",\"email\":\"jdayton@geezeo.com\",\"login_count\":158,\"last_login_at\":\"2012-02-12T01:50:23Z\",\"first_name\":\"Jeff\",\"last_name\":\"Dayton\",\"postal_code\":\"06001\",\"birth_year\":1987,\"sex\":\"Male\"},{\"id\":null,\"custom_tags\":[],\"login\":\"datasafe\",\"email\":\"kdaigle+datasafe@geezeo.com\",\"login_count\":4,\"last_login_at\":\"2012-01-25T01:57:26Z\",\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1991,\"sex\":\"Male\"},{\"id\":\"1946606\",\"custom_tags\":[],\"login\":\"tharris\",\"email\":\"thomas.e.harris@gmail.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1993,\"sex\":\"Male\"},{\"id\":\"1946607\",\"custom_tags\":[],\"login\":\"tscott\",\"email\":\"tubbo@psychedeli.ca\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1993,\"sex\":\"Male\"},{\"id\":\"1946608\",\"custom_tags\":[],\"login\":\"dkleinschmidt\",\"email\":\"david@kleinschmidt.name\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1993,\"sex\":\"Female\"},{\"id\":\"1946609\",\"custom_tags\":[],\"login\":\"jgaskins\",\"email\":\"jgaskins@gmail.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1993,\"sex\":\"Male\"},{\"id\":\"1946610\",\"custom_tags\":[],\"login\":\"dratajczak\",\"email\":\"david@mockra.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1993,\"sex\":\"Male\"},{\"id\":\"jdenison\",\"custom_tags\":[],\"login\":\"jdenison\",\"email\":\"jordan@denisonweb.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1994,\"sex\":\"Female\"},{\"id\":\"nfreeman\",\"custom_tags\":[],\"login\":\"nfreeman\",\"email\":\"naomi.freeman@outlook.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1994,\"sex\":\"Female\"},{\"id\":\"pvernigorov\",\"custom_tags\":[],\"login\":\"pvernigorov\",\"email\":\"jelwood+pvernigorov@geezeo.com\",\"login_count\":0,\"last_login_at\":null,\"first_name\":\"Sample\",\"last_name\":\"User\",\"postal_code\":\"12345\",\"birth_year\":1994,\"sex\":\"Female\"}]}")
        case "/api/v2/users/jdenison/transactions" => Json.parse("{\"meta\":{\"total_pages\":2,\"current_page\":1},\"transactions\":[{\"id\":\"48bf8bcc-851e-498b-8e25-a2981930dcbe\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-06-02T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:40:59+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"82c316b9-fc4e-4657-a855-9e38658fa302\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":262.47,\"posted_at\":\"2014-06-02T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:40:59+00:00\",\"nickname\":\"Dave's Auto Repair\",\"original_name\":\"Dave's Auto Repair\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Transportation\",\"balance\":262.47}]},{\"id\":\"b3ee45e0-239f-44eb-a871-13435e46bfc5\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":200.0,\"posted_at\":\"2014-06-02T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:40:59+00:00\",\"nickname\":\"Check #125\",\"original_name\":\"Check #125\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Personal\",\"balance\":200.0}]},{\"id\":\"e591f88c-3b2b-4fa0-99a9-8a57ad5c3d94\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":\"GAMESTOP.COM/EBGAMES.COM 817-422-2085 TX\",\"balance\":5.99,\"posted_at\":\"2014-06-02T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:40:59+00:00\",\"nickname\":\"GameStop\",\"original_name\":\"GameStop\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Entertainment\",\"balance\":5.99}]},{\"id\":\"f4eab936-764b-4b6d-a6a9-464d55bee441\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":10.95,\"posted_at\":\"2014-06-02T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:40:59+00:00\",\"nickname\":\"Vonage\",\"original_name\":\"Vonage\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Utilities\",\"balance\":10.95}]},{\"id\":\"530f041f-2eaf-4d05-bded-537ba77f6bf4\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":21.21,\"posted_at\":\"2014-06-01T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:11+00:00\",\"nickname\":\"Borders Books\",\"original_name\":\"Borders Books\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Entertainment\",\"balance\":21.21}]},{\"id\":\"bb2244fa-3cff-48fd-af1a-5181019abb08\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":\"BED BATH & BEYOND #651 800-462-3966 NJ\",\"balance\":6.2,\"posted_at\":\"2014-06-01T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:01+00:00\",\"nickname\":\"Bed Bath & Beyond\",\"original_name\":\"Bed Bath & Beyond\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Household\",\"balance\":6.2}]},{\"id\":\"c2b656de-5b9a-4359-a1de-2033b32622d6\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-06-01T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:15+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"c53f93a6-d532-4ecd-a196-e2ed91170292\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":25.0,\"posted_at\":\"2014-06-01T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:06+00:00\",\"nickname\":\"Check #123\",\"original_name\":\"Check #123\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Transportation\",\"balance\":25.0}]},{\"id\":\"2329efad-1f5e-4cdd-93cc-08f2b31fa001\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-05-31T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:19+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"c4a36c68-0127-412e-8f3e-0368375b80f3\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":48.63,\"posted_at\":\"2014-05-31T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:16+00:00\",\"nickname\":\"Buy.com\",\"original_name\":\"Buy.com\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Personal\",\"balance\":48.63}]},{\"id\":\"ff2fc3b3-e648-419a-a1e8-f362e2deeb9d\",\"reference_id\":null,\"transaction_type\":\"Credit\",\"memo\":null,\"balance\":2809.85,\"posted_at\":\"2014-05-31T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:16+00:00\",\"nickname\":\"Company Payroll\",\"original_name\":\"Company Payroll\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Income\",\"balance\":2809.85}]},{\"id\":\"6bb0eba4-9ae8-4f42-a3ec-d128711162ad\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-05-30T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:20+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"21e16783-4f1a-48ae-80a8-813e5d8610e1\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":9.99,\"posted_at\":\"2014-05-29T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:20+00:00\",\"nickname\":\"Netflix.com\",\"original_name\":\"Netflix.com\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Entertainment\",\"balance\":9.99}]},{\"id\":\"b8a1e806-4dbf-4296-9674-3dd7ceae179e\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-05-29T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:21+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"78986145-2740-4b5b-9352-de7935df5d31\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":35.45,\"posted_at\":\"2014-05-28T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:21+00:00\",\"nickname\":\"California Pizza Kitchen\",\"original_name\":\"California Pizza Kitchen\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":35.45}]},{\"id\":\"7db6bb41-7606-459a-91ae-50145cf6a486\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":64.81,\"posted_at\":\"2014-05-28T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:22+00:00\",\"nickname\":\"Dillards\",\"original_name\":\"Dillards\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Household\",\"balance\":64.81}]},{\"id\":\"c6349626-9f45-485c-ba05-f6a9c35382ab\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.83,\"posted_at\":\"2014-05-28T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:22+00:00\",\"nickname\":\"Starbucks\",\"original_name\":\"Starbucks\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.83}]},{\"id\":\"babcedf1-dc8f-4dbd-9033-c6d3c08bf823\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":132.48,\"posted_at\":\"2014-05-27T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:22+00:00\",\"nickname\":\"Publix\",\"original_name\":\"Publix\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Groceries\",\"balance\":132.48}]},{\"id\":\"f9accee5-fd6d-48e7-8f1d-eed1f621e3a1\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":\"AT&T*BILL PAYMENT 800-331-0500 TX\",\"balance\":83.9,\"posted_at\":\"2014-05-26T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:23+00:00\",\"nickname\":\"AT&T\",\"original_name\":\"AT&T\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Utilities\",\"balance\":83.9}]},{\"id\":\"3afb83c3-618b-4272-8107-1c2891d4ad64\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":1.74,\"posted_at\":\"2014-05-25T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:24+00:00\",\"nickname\":\"CVS\",\"original_name\":\"CVS\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Health\",\"balance\":1.74}]},{\"id\":\"7a4ba308-ac90-4fa1-a47e-65388beebde1\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":258.34,\"posted_at\":\"2014-05-25T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:23+00:00\",\"nickname\":\"Apple Store\",\"original_name\":\"Apple Store\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Personal\",\"balance\":258.34}]},{\"id\":\"8cc172cb-29e6-4b7c-8987-3b56c45bf2e7\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":160.48,\"posted_at\":\"2014-05-25T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:24+00:00\",\"nickname\":\"Progressive Insurance\",\"original_name\":\"Progressive Insurance\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Insurance\",\"balance\":160.48}]},{\"id\":\"48e609bc-66a8-4d83-a560-530527a22530\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":1148.82,\"posted_at\":\"2014-05-23T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:24+00:00\",\"nickname\":\"Mortgage Payment\",\"original_name\":\"Mortgage Payment\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Household\",\"balance\":1148.82}]},{\"id\":\"9710dc8b-f5e5-4d73-8a88-8ae4d88feb79\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":4.22,\"posted_at\":\"2014-05-23T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:24+00:00\",\"nickname\":\"McDonalds\",\"original_name\":\"McDonalds\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":4.22}]},{\"id\":\"8963e498-597a-4051-b165-afbf1fc63f45\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":45.63,\"posted_at\":\"2014-05-21T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:25+00:00\",\"nickname\":\"Dicks Sporting Goods\",\"original_name\":\"Dicks Sporting Goods\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Personal\",\"balance\":45.63}]}],\"accounts\":[{\"id\":4383080,\"name\":\"eChecking\"},{\"id\":4383082,\"name\":\"Preferred Plus Card\"}]}")
        case "/api/v2/users/jdenison/transactions?page=2" => Json.parse("{\"meta\":{\"total_pages\":2,\"current_page\":2},\"transactions\":[{\"id\":\"1cc6eaf2-19aa-42ed-9ed1-204ecdae69a2\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":14.64,\"posted_at\":\"2014-05-18T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:25+00:00\",\"nickname\":\"Publix\",\"original_name\":\"Publix\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Groceries\",\"balance\":14.64}]},{\"id\":\"9660a8ca-ba12-416e-947d-6da385996e37\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":38.48,\"posted_at\":\"2014-05-18T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:26+00:00\",\"nickname\":\"Publix\",\"original_name\":\"Publix\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Groceries\",\"balance\":38.48}]},{\"id\":\"bdfa2633-b6f5-4370-9f31-67b1111a7a31\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":123.17,\"posted_at\":\"2014-05-18T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:26+00:00\",\"nickname\":\"Alabama Power\",\"original_name\":\"Alabama Power\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Utilities\",\"balance\":123.17}]},{\"id\":\"9e7a14b4-c459-41f7-9051-40ba760004fe\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":12.01,\"posted_at\":\"2014-05-17T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:26+00:00\",\"nickname\":\"Dominos Pizza\",\"original_name\":\"Dominos Pizza\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":12.01}]},{\"id\":\"9a9f1797-0ddb-4358-907f-21ee823327b8\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":3.84,\"posted_at\":\"2014-05-15T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:27+00:00\",\"nickname\":\"Dunkin Donuts\",\"original_name\":\"Dunkin Donuts\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":3.84}]},{\"id\":\"102eb97d-f730-4954-b5db-678fdacde675\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":5.69,\"posted_at\":\"2014-05-14T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:27+00:00\",\"nickname\":\"Burger King\",\"original_name\":\"Burger King\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":5.69}]},{\"id\":\"81bb9d93-d357-40ac-904a-b1c8b0a313a1\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":20.69,\"posted_at\":\"2014-05-14T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:27+00:00\",\"nickname\":\"eBay.com\",\"original_name\":\"eBay.com\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Personal\",\"balance\":20.69}]},{\"id\":\"dbbef2dd-2a45-48c8-b746-783112898f0b\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":89.57,\"posted_at\":\"2014-05-14T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Abercrombie & Fitch\",\"original_name\":\"Abercrombie & Fitch\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Clothing\",\"balance\":89.57}]},{\"id\":\"22791981-e62a-4f8d-99f7-d695f3a8997a\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":58.26,\"posted_at\":\"2014-05-11T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Verizon Wireless\",\"original_name\":\"Verizon Wireless\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Utilities\",\"balance\":58.26}]},{\"id\":\"5887009b-9475-478a-8721-5f24f7925c54\",\"reference_id\":null,\"transaction_type\":\"Credit\",\"memo\":null,\"balance\":2809.85,\"posted_at\":\"2014-05-11T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Company Payroll\",\"original_name\":\"Company Payroll\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Income\",\"balance\":2809.85}]},{\"id\":\"11f7f40c-86a8-498e-a41a-3f7463c35377\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":13.48,\"posted_at\":\"2014-05-09T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"FedEx\",\"original_name\":\"FedEx\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Personal\",\"balance\":13.48}]},{\"id\":\"22e4d460-d339-4211-95c1-e828fb20469a\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":48.58,\"posted_at\":\"2014-05-09T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Target\",\"original_name\":\"Target\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Personal\",\"balance\":48.58}]},{\"id\":\"5a334697-1243-4a0e-82a9-8f8c81964f49\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":120.0,\"posted_at\":\"2014-05-09T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Check #124 (Theater Tickets)\",\"original_name\":\"Check #124 (Theater Tickets)\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Entertainment\",\"balance\":120.0}]},{\"id\":\"c68446f4-bda3-46bb-a1e5-859c868bdcc6\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":162.17,\"posted_at\":\"2014-05-09T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Publix\",\"original_name\":\"Publix\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Groceries\",\"balance\":162.17}]},{\"id\":\"f71c2248-76e0-4304-b4bd-ccc9322c70e5\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":35.81,\"posted_at\":\"2014-05-09T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Applebees\",\"original_name\":\"Applebees\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Diningout\",\"balance\":35.81}]},{\"id\":\"18a9c731-5845-4124-96ff-71eca373786b\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":6.72,\"posted_at\":\"2014-05-07T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"Sonic\",\"original_name\":\"Sonic\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":6.72}]},{\"id\":\"247079ff-4b73-4394-a854-0d3c5eeb6d42\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":31.87,\"posted_at\":\"2014-05-07T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:28+00:00\",\"nickname\":\"Finance Charge\",\"original_name\":\"Finance Charge\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Fees\",\"balance\":31.87}]},{\"id\":\"6c8e0034-6541-4470-9996-5c6d131f5ffd\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":234.29,\"posted_at\":\"2014-05-07T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"Ford Credit\",\"original_name\":\"Ford Credit\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Transportation\",\"balance\":234.29}]},{\"id\":\"88f076a9-2426-4ecc-b71c-6b552fe7ceff\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":69.36,\"posted_at\":\"2014-05-07T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"Publix\",\"original_name\":\"Publix\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Groceries\",\"balance\":69.36}]},{\"id\":\"07a4f9b3-aa6f-44e8-95ee-2ab0a0b032ef\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":46.95,\"posted_at\":\"2014-05-06T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"Friendlys\",\"original_name\":\"Friendlys\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Diningout\",\"balance\":46.95}]},{\"id\":\"87d2d103-c269-4579-9f56-40ef5f101657\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":14.86,\"posted_at\":\"2014-05-06T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"Bath & Body Works\",\"original_name\":\"Bath & Body Works\",\"check_number\":null,\"links\":{\"account\":4383080},\"tags\":[{\"name\":\"Personal\",\"balance\":14.86}]},{\"id\":\"b4b7ee47-e6e3-4df1-b2bf-e728142fb665\",\"reference_id\":null,\"transaction_type\":\"Debit\",\"memo\":null,\"balance\":31.5,\"posted_at\":\"2014-05-06T00:00:00+00:00\",\"created_at\":\"2014-06-02T12:41:29+00:00\",\"nickname\":\"MovieTickets.com\",\"original_name\":\"MovieTickets.com\",\"check_number\":null,\"links\":{\"account\":4383082},\"tags\":[{\"name\":\"Entertainment\",\"balance\":31.5}]}],\"accounts\":[{\"id\":4383080,\"name\":\"eChecking\"},{\"id\":4383082,\"name\":\"Preferred Plus Card\"}]}")
        case "/api/v2/users/jdenison/cashflow/incomes" => Json.parse("{\"incomes\":[]}")
        case _ => Json.obj("error"->"unknown url handed to mock api handler.")
      }
    }
  }

  "NerdStatsController" should {
    "respond to the index Action" in new WithApplication {
      val result = new TestController().index(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")

      // did we get our users, at least the good ones?
      contentAsString(result) must contain("jdenison")
      contentAsString(result) must contain("nfreeman")
      contentAsString(result) must contain("pvernigorov")
    }

    "respond to the getStats Action" in new WithApplication {
        val result = new TestController().getStats(
          FakeRequest().withHeaders(("Authorization", AUTH_ENCODED)))

        status(result) must equalTo(OK)
        contentType(result) must beSome("text/html")

        // did things calculate correctly?
        contentAsString(result) must contain("4.4cm")                    // neckbeard length
        contentAsString(result) must contain("06/03/2014 5AM for $6.83") // fast food estimate
        contentAsString(result) must contain("06/03/2014 5PM for $34.92")// otaku shopping estimate
    }

    "respond to the javascriptRoutes Action" in new WithApplication {
      val result = new TestController().javascriptRoutes(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/javascript")

      // does it at least look like the right stuff?
      contentAsString(result) must contain("jsRoutes")
      contentAsString(result) must contain("getStats")
    }
  }

  /*"Example Page#index" should {
    "should be valid" in {
      val controller = new TestController()
      val result: Future[SimpleResult] = controller.index().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText must be equalTo "ok"
    }
  }*/
/*  "NerdStatsController" should {
    
    "getStats should return HTML" in new WithApplication {
      val result = controllers.NerdStatsController.getStats(
        FakeRequest().withHeaders(("Authorization", "Basic amRlbmlzb246")))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("Hello from Scala")
    }

  }*/
}