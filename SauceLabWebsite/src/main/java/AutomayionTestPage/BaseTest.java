package AutomayionTestPage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.lang.reflect.Method;  
import AutomationCommonPage.ExtentManager;

public class BaseTest {

    protected static WebDriver driver;
    public ExtentTest test;
    public static ExtentReports extent;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getExtentReports(); // Initialize ExtentReports
    }

    @BeforeMethod
    public void setup(Method method) {
        // Initialize ExtentTest for each test
        test = extent.createTest(method.getName());

        // Set ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", 
            "C:\\Users\\user\\eclipse-workspace2024\\AutomationStepByStep\\myDriver\\chromedriver.exe");

        System.out.println("Initializing WebDriver...");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        try {
            if (test != null) { 
                if (result.getStatus() == ITestResult.FAILURE) {
                    test.fail("❌ Test Failed: " + result.getThrowable());
                    System.out.println("⚠️ Test failed: Keeping browser open for debugging...");
                    return; // Do not close browser on failure
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.pass("✅ Test Passed");
                }
            } else {
                System.out.println("ExtentTest is null. Skipping report logging.");
            }

            // Close browser only if the test passes
            if (driver != null) {
                System.out.println("Closing browser...");
                driver.quit();
                driver = null;  // Reset the driver reference
            }
        } catch (Exception e) {
            System.out.println("Error while quitting driver: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();  // Save the report
        }
    }
}
