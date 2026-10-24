package api.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RateLimiting {
	@Test
	public void testRateLimit() throws InterruptedException {
		for (int i = 0; i < 120; i++) {
			Response response = RestAssured.given()
					.get("https://api.github.com/users/octocat");

			int statusCode = response.getStatusCode();
			int remaining = Integer.parseInt(response.getHeader("X-RateLimit-Remaining"));

			System.out.println("Request " + i + " -> Status: " + statusCode + ", Remaining: " + remaining);

			if (remaining == 0) {
				// Expect API to start returning 429
				assertEquals(statusCode, 429, "API should enforce rate limiting");
				break;
			}
		}
	}
}
