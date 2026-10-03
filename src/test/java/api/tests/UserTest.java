package api.tests;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import api.utils.JsonDataReader;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTest {
	private static final Logger logger = LogManager.getLogger(UserTest.class);
	User user;
	@BeforeMethod
	public void setup() {
		user = new User();
	}
	
	@Test(priority=1)
	public void createUserListTest() throws Exception {		
		logger.info("Create User List  API Call started");
		List<User> users = JsonDataReader.getUsers();
		Response response = UserEndPoint.createUserWithList(users);
	    //System.out.println(response.asPrettyString());	
		response.then()
		.statusCode(200)
		.body("code",equalTo(200))
		.body("type", equalTo("unknown"))
		.body("message", equalTo("ok"));	
		logger.info("Create User API Call completed");
		
	}
	@Test(priority=2)
	public void createUserArrayTest() throws Exception {
		logger.info("Create User Array API Call started");
		List<User> users = JsonDataReader.getUsers();
		Response response = UserEndPoint.createUserWithArray(users.toArray(new User[0]));
	   //System.out.println(response.asPrettyString());	
		response.then()
		.statusCode(200)
		.body("code",equalTo(200))
		.body("type", equalTo("unknown"))
		.body("message", equalTo("ok"));		
		logger.info("Create User Array API Call completed");
	}
	@Test(priority=3)
	public void createUserTest() throws Exception {
		logger.info("Create User  API Call started");
		List<User> users = JsonDataReader.getUsers();
		Response response = UserEndPoint.createUser(users.get(2));
	    //System.out.println(response.asPrettyString());	
		response.then()
		.statusCode(200)
		.body("code",equalTo(200))
		.body("type", equalTo("unknown"))
		.body("message", equalTo("3"));	
		logger.info("Create User API Call completed");
	}
	@Test(priority=4, dependsOnMethods="createUserTest")
	public void getUserTest() throws Exception {
		logger.info("Get User API Call");
		List<User> users = JsonDataReader.getUsers();
		String username = users.get(1).getUsername();
		Response response = UserEndPoint.getUser(username);
	    //System.out.println("Response for get user is : "+response.asPrettyString());
		response.then()
		 //Exact matches
        .body("id", equalTo(2))
        .body("username", equalTo("ankitkumar"))
        .body("firstName", equalTo("Ankit"))
        .body("lastName", equalTo("Kumar"))
        .body("email", equalTo("ankit@kumar.com"))
        .body("password", equalTo("Test@123"))
        .body("phone", equalTo("0412345678"))
        .body("userStatus", equalTo(0))
        //Type checks
        .body("id", instanceOf(Integer.class))
        .body("username", instanceOf(String.class))
        .body("email", instanceOf(String.class))
        .body("userStatus", instanceOf(Integer.class))
        //String checks
        .body("username", startsWith("ankit"))
        .body("email", containsString("@"))
        .body("password", containsString("@123"))
        .body("phone", hasLength(10)) 
        //Regex pattern checks
        .body("email", matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"))
        .body("phone", matchesPattern("^[0-9]{10}$"))
        //Value check
        .body("id", greaterThan(0))
        .body("userStatus", lessThanOrEqualTo(1))
        .body(matchesJsonSchemaInClasspath("schema/user_schema.json"))
        .log().all()
        .extract().as(User.class);
		System.out.println("User id is"+user.getId());
		System.out.println("User fname is"+user.getFirstName());
		System.out.println("User lname is"+user.getLastName());
		logger.info("Get User API Call validated succeesfully");
		
	}
	@Test(priority=5, dependsOnMethods="createUserTest")
	public void getUserLoginTest() throws Exception {
		logger.info("Get User Login API Call started");
		List<User> users = JsonDataReader.getUsers();
		String username = users.get(1).getUsername();
		String password = users.get(1).getPassword();
		Response response = UserEndPoint.getUserLogin(username,password);
	    //System.out.println("Response for Login Test is : "+response.asPrettyString());
		response.then().log().all();
		// Response Validation
		response.then()
			.statusCode(200)
			.body("code",equalTo(200))
			.body("type", equalTo("unknown"))
			.body("message", containsString("session"));
		logger.info("Get User Login API Call completed");
		
	}
	@Test(priority=6, dependsOnMethods="getUserLoginTest")
	public void getUserLogoutTest() throws Exception {
		logger.info("Get User Logout API Call started");
		Response response = UserEndPoint.getUserLogout();
//		System.out.println("Response for Log out test is : "+response.asPrettyString());
		response.then().log().all();
		logger.info("Get User Logout API Call completed");
	}
	@Test(priority=7)
	public void updateUserTest() throws Exception {
		logger.info("Update User Login API Call started");
		List<User> users = JsonDataReader.getUsers();
		users.get(1).setFirstName("Ishan");
		String username = users.get(1).getUsername();
		Response response = UserEndPoint.updateUser(username,users.get(1));
	//	System.out.println("Response for UpdatedUserTest is : "+response.asPrettyString());
		logger.info("Update User Login API Call completed");
	}
	@Test(priority=8)
	public void deleteUserTest() throws Exception {
		logger.info("Delete User Login API Call started");
		List<User> users = JsonDataReader.getUsers();
		String username = users.get(1).getUsername();
		Response response = UserEndPoint.deleteUser(username);
	//	System.out.println("Response for Deleted User Test is : "+response.asPrettyString());
		//assert false;
		logger.info("Delete User Login API Call completed");
	}
}
