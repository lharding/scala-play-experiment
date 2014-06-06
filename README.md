# Geezeo Other Stats Dashboard

![My image](https://raw.githubusercontent.com/lharding/geezeo-test/master/screenshot.png)

A simple application that uses the Geezeo API to calculate a couple of less-common
statistics for the users a given partner account can access.

## Running

It's an Activator project, so assuming you have Scala and dependencies installed,
you should be able to <code>cd</code> into the project directory and just do
<code>./activator run</code> and you'll get a server on http://localhost:9000.

To actually do anything you'll need to provide an API key and partner domain.
These are set in <code>conf/application.conf</code>, via the <code>apiKey</code>
and <code>apiBaseUrl</code> properties. You can also adjust <code>fastFood</code>
and <code>nerdyPlaces</code> here in order to tune the neckbeard length calculation
according to your personal beliefs.

See screenshot.png for an example of what things are supposed to look like.

## Caveats

Since this is a small project with a team of one, there are some differences in
execution as compared with how I would do a larger project in a team context.
To wit:

* Shuffling JsValues around directly, rather than deserializing the API results
into proper data model objects and working with those.
* Basically everything put together in the NerdStatsController file for the sake
of easy navigability, rather than decomposing utility methods into separate objects
or other such modularization.
* Accompanyingly, no separate unit tests for the tiny utility functions. Normally I
would consider any kind of utils bucket class one of the most important places to
have complete and excruciatingly detailed test coverage.
* Not even any client-side template engine. Obviously there's just not enough to
make it necessary here, but I usually use client-side rendering a lot more heavily
once the initial page load is done.
* Didn't git push early and often. That's mostly okay when working alone, and
suicide when part of a team.