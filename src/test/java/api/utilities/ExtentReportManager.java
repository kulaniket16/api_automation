package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	
	public ExtentTest test;     // UI part report
	public ExtentReports extent; //commondata= enviroment & user information, operating system,project & module name,common details
	public ExtentSparkReporter sparkreporter; //write report entries fail or passed entry
	String repName;
	
	public void onStart(ITestContext testContext) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //timestamp
		repName="Test-Report-"+timeStamp+".html";
				
		sparkreporter= new ExtentSparkReporter(".\\reports\\"+repName);  //specify location of the report
		sparkreporter.config().setDocumentTitle("RestAssuredAutomationProject");  //Title of report	
		sparkreporter.config().setReportName("Pet Store User API"); //name of the report
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Pest Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user","Aniket");
				
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");	
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());	
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());	
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
