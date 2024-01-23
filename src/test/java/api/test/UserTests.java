package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setupdata() {
		 faker= new Faker();
		 userpayload= new User();
		 
		 userpayload.setId(faker.idNumber().hashCode());
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		 userpayload.setUsername(faker.name().username());
		 userpayload.setPassword(faker.internet().password(5, 10));
		 userpayload.setPhone(faker.phoneNumber().cellPhone());
	//log
		 logger= LogManager.getLogger(this.getClass());
		 
	}
	
	@Test(priority=1)
	public void testpostUser() {
		
		logger.info("***********creating user***********");
		
		Response responce= Userendpoints.createUser(userpayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.info("***********user is created ***********");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		logger.info("***********Reading user info***********");
	Response responce=	Userendpoints.readUser(this.userpayload.getUsername());
	responce.then().log().all();
	Assert.assertEquals(responce.getStatusCode(), 200);
	logger.info("*********** user info is displayed***********");
	}
	
	@Test(priority=3)
	public void testPostUserByName() {
		logger.info("***********updating user info***********");
		
		userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response responce= Userendpoints.updateUser(this.userpayload.getUsername(), userpayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.info("*********** user info is updated***********");
		//after updating checking data
		
		Response responceafterupdate=	Userendpoints.readUser(this.userpayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responceafterupdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("***********deleting user info***********");
		Response responce= Userendpoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.info("***********deleted user info***********");
	}
	
	
	
	
	
	
	
	
	
	
}
