package AutomayionTestPage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.lang.reflect.Method;  

import AutomationCommonPage.ExtentManager;


public class BaseTest {
	
	// a driver for all classess inside automatedtest package
	protected static WebDriver driver;

    public ExtentTest test;
	 public  static ExtentReports extent;
	   
	   
	   
	   @BeforeSuite
	    public void setUpReport() {
	        extent = ExtentManager.getExtentReports(); // Initialize report
	        
	   }
	  
	

   //@BeforeTest // Run before all test methods in a class
   
   @BeforeMethod()
  public void setup() {
	   ChromeOptions options = new ChromeOptions();
	      options.addArguments("--remote-allow-origins=*"); 
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\eclipse-workspace2024\\AutomationStepByStep\\myDriver\\chromedriver.exe");
	  
      // Initialize the ChromeDriver with the options
	  System.out.println("Initializing WebDriver...");
       driver = new ChromeDriver(options);
	  
	  //driver = new ChromeDriver();   //Launch chromedriver
	  driver.get("https://www.saucedemo.com");
	  driver.manage().window().maximize();
  }
   
   
   @BeforeMethod
   public void startTest(Method method) {
       test = extent.createTest(method.getName()); // Use test method name dynamically
   }
  
  
  @AfterMethod
  public void teardown(ITestResult result) {
      try {
          // Ensure ExtentTest is initialized before logging
          if (test != null) { 
              if (result.getStatus() == ITestResult.FAILURE) {
                  test.fail("Test Failed: " + result.getThrowable());
              } else if (result.getStatus() == ITestResult.SUCCESS) {
                  test.pass("Test Passed");
              }
          } else {
              System.out.println("ExtentTest object is null. Skipping report logging.");
          }

          // Check if driver is still running before quitting
          if (driver != null) {
              System.out.println("Closing browser...");
              driver.quit();
              driver = null;  // Ensure the reference is cleared
          }

      } catch (Exception e) {
          System.out.println("Error while quitting driver: " + e.getMessage());
      }

  }

  @AfterSuite
  public void tearDownReport() {
      if (extent != null) {
          extent.flush();
      }
      if (driver != null) {
          System.out.println("Closing WebDriver...");
          driver.quit();
      }
  }
}
