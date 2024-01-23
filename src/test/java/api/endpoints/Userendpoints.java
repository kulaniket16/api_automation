package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//created for CRUD  request to the user


public class Userendpoints {

	public static Response createUser(User payload) {
		
	Response response =	given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
			            .when().post(Routes.post_url);
	
		return response; 
		
	}
	
	
	public static Response readUser(String UserName) {
		
		Response response =	given().pathParam("username", UserName)
				            .when().get(Routes.get_url);
		
			return response; 
			
		}
	
	
	public static Response updateUser(String UserName , User payload) {
		
		Response response =	given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", UserName).body(payload)
				            .when().put(Routes.update_url);    
		
			return response; 
			
		}
	
	public static Response deleteUser(String UserName) {
		
		Response response =	given().pathParam("username", UserName)
				            .when().delete(Routes.delete_url);
				            
		
			return response; 
			
		}
	
	
	
	
}
