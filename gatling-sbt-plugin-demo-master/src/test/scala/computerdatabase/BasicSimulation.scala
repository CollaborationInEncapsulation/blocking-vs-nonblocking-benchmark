package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

	val httpConf = http
		.baseURL("http://13.95.122.132:8080") // Here is the root for all relative URLs
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
		.acceptEncodingHeader("gzip, deflate").acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

	val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
		.repeat(5) {
			           exec(http("request_1").get("/large"))
		           }

	setUp(scn.inject(atOnceUsers(10000)).protocols(httpConf))
}
