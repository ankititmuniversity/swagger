package api.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;

public class UT {
Response response;
@Test
public void getCall() throws FileNotFoundException {
	PrintStream logFile = new PrintStream("target/restassured.log");

    RestAssured.config = RestAssured.config()
        .logConfig(LogConfig.logConfig().defaultStream(logFile));
    
	Response response = given()
	    .relaxedHTTPSValidation()
		//.header("Content-Type", "application/json")
		//.log().all()
	.when()	
		.get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
	.then()
		.log().all()
		.assertThat()
	.statusCode(200)
	.contentType("application/json")
	.header("Content-Type", "application/json").extract().response();
	
	Map<String,String> cookiesMap = response.getCookies();
	System.out.println(cookiesMap.size()+"---");
	cookiesMap.entrySet().stream().forEach((s-> System.out.println("Key : "+ s.getKey()+" :: "+s.getValue())));
}
}
