package api.endpoints;

import io.restassured.response.Response;

public interface IUserAuthEndpoint {
    Response login(String username, String password);
    Response logout();
}
