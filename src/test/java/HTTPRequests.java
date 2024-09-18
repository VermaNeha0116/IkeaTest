

import org.testng.annotations.Test;

import POJO.Post_Request;
import POJO.Post_Request2;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class HTTPRequests {
	int id;

	@Test
	void getUsers() {
		
		System.out.println("Test");

		when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).log().all();
	}
	
	@Test
	void createUser() {
//		HashMap data =new HashMap();
//		data.put("name","Praveen");
//		data.put("job","trainer");
		Post_Request2 data = new Post_Request2();
		
		data.setName("Praveen");

		id =given().contentType("application/json").body(data)
		.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
	}
	
	@Test(dependsOnMethods= {"createUser"})
	void updateUser() {
		HashMap data =new HashMap();
		data.put("name","Neha");
		data.put("job","Teacher");
		given().contentType("application/json").body(data)
		.when().put("https://reqres.in/api/users/"+id)
		.then().statusCode(200).log().all();
		
	}

}
