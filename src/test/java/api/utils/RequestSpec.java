package api.utils;

import api.routes.Routes;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
	 private static RequestSpecification requestSpecification;

	    public static RequestSpecification getRequestSpec() {
	        if (requestSpecification == null) {
	            requestSpecification = new RequestSpecBuilder()
	            		.setBaseUri(Routes.baseUrl)
	                    .addHeader("Content-Type","application/json")  
	                    .addHeader("Accept","application/json")
	                    .addFilter(new RequestLoggingFilter())
	                    .addFilter(new ResponseLoggingFilter())
	                    .build();
	        }
	        return requestSpecification;
	    }
}
