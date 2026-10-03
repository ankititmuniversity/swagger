package api.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {

    private static ResponseSpecification responseSpecification;

    public static ResponseSpecification getResponseSpec() {
        if (responseSpecification == null) {
            responseSpecification = new ResponseSpecBuilder() 
            		.expectStatusCode(200)
                    .expectContentType(ContentType.JSON)         
                    .expectResponseTime(org.hamcrest.Matchers.lessThan(5000L))
                    .build();
        }
        return responseSpecification;
    }
}
