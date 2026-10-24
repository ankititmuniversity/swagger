package api.endpoints;

import api.payloads.User;
import io.restassured.response.Response;
import java.util.List;

public interface IUserCrudEndpoint {
    Response createUser(User userPayload);
    Response createUserWithList(List<User> userPayload);
    Response createUserWithArray(User[] userPayload);
    Response getUser(String username);
    Response updateUser(String username, User userPayload);
    Response deleteUser(String username);
}
