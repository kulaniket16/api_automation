package api.test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.endpoints.Userendpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	
	
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class)
	public void testpostuser(String userID ,String userName, String fname , String lname , String useremail, String pwd , String ph	) 
	{
		User userpayload= new User();
		 userpayload.setId(Integer.parseInt(userID));
		 userpayload.setFirstName(fname);
		 userpayload.setLastName(lname);
		 userpayload.setEmail(useremail);
		 userpayload.setUsername(userName);
		 userpayload.setPassword(pwd);
		 userpayload.setPhone(ph);
		 
		 Response responce= Userendpoints.createUser(userpayload);
		Assert.assertEquals(responce.getStatusCode(), 200);
	}

	@Test(priority=2, dataProvider="UserNames", dataProviderClass= DataProviders.class)
	public void testdeleteuser(String userName) {
		
		 Response responce= Userendpoints.deleteUser(userName);
			Assert.assertEquals(responce.getStatusCode(), 200);
	}
	
	
	
	
}
