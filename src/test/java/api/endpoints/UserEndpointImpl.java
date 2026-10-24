package api.endpoints;

import api.payloads.User;
import api.routes.Routes;
import api.utils.RequestSpec;
import api.utils.ResponseSpec;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserEndpointImpl implements IUserCrudEndpoint,IUserAuthEndpoint {

    @Override
    public Response createUser(User userPayload) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(userPayload)
        .when()
                .post(Routes.postUrl)
        .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract().response();
    }

    @Override
    public Response createUserWithList(List<User> userPayload) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(userPayload)
        .when()
                .post(Routes.postUrl + "/createWithList")
        .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract().response();
    }

    @Override
    public Response createUserWithArray(User[] userPayload) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(userPayload)
        .when()
                .post(Routes.postUrl + "/createWithArray")
        .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract().response();
    }

    @Override
    public Response getUser(String username) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .pathParam("username", username)
        .when()
                .get(Routes.getUrl)
        .then()
                .extract().response();
    }

    @Override
    public Response updateUser(String username, User userPayload) {
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

    @Override
    public Response deleteUser(String username) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .pathParam("username", username)
        .when()
                .delete(Routes.deleteUrl)
        .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract().response();
    }

    @Override
    public Response login(String username, String password) {
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

    @Override
    public Response logout() {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .basePath("/user/logout")
                .header("accept", "application/json")
        .when()
                .get()
        .then()
                .extract().response();
    }
}
