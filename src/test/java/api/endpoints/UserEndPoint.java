package api.endpoints;

import api.payloads.User;
import api.routes.Routes;
import api.utils.RequestSpec;
import api.utils.ResponseSpec;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class UserEndPoint {

    public static Response createUserWithList(List<User> userPayload) {
        return given()
                    .spec(RequestSpec.getRequestSpec())
                    .body(userPayload)                       
               .when()
                    .post(Routes.postUrl+"/createWithList")                     
               .then()
               		.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
    public static Response createUserWithArray(User [] userPayload) {
        return given()
                    .spec(RequestSpec.getRequestSpec())
                    .body(userPayload)                       
               .when()
                    .post(Routes.postUrl+"/createWithArray")                     
               .then()
               		.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
    public static Response createUser(User userPayload) {
        return given()
                    .spec(RequestSpec.getRequestSpec())
                    .body(userPayload)                       
               .when()
                    .post(Routes.postUrl)                     
               .then()
               		.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
    public static Response getUser(String username) {
        return given()
                    .spec(RequestSpec.getRequestSpec()) 
                    .pathParam("username", username)
               .when()
                    .get(Routes.getUrl)                     
               .then()
               		//.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
    public static Response getUserLogin(String username,String password) {
        return given()
        			.spec(RequestSpec.getRequestSpec())
        			.basePath("/user/login")                 
                    .queryParam("username", username)
                    .queryParam("password", password)
               .when()
                    .get()                     
               .then()           
                    .extract().response();                    
    }
    public static Response getUserLogout() {
        return given()
        			.spec(RequestSpec.getRequestSpec())
        			.basePath("/user/logout")
                    .header("accept","application/json")
               .when()
                    .get()                     
               .then()           
                    .extract().response();                    
    }
    
    public static Response updateUser(String username, User userPayload) {
        return given()
                    .spec(RequestSpec.getRequestSpec())
                    .pathParam("username", username)
                    .body(userPayload)                       
               .when()
                    .put(Routes.updateUrl)                     
               .then()
               		.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
    
    public static Response deleteUser(String username) {
        return given()
                    .spec(RequestSpec.getRequestSpec()) 
                    .pathParam("username", username)
               .when()
                    .delete(Routes.deleteUrl)                     
               .then()
               		.spec(ResponseSpec.getResponseSpec())
                    .extract().response();                    
    }
}
