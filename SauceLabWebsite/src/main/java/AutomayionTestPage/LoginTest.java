package AutomayionTestPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import AutomationPage.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginTestData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", true},  // ✅ Valid user
            {"locked_out_user", "secret_sauce", false},  // ❌ Locked-out user
            {"invalid_user", "invalid_pass", false}   // ❌ Invalid login
        };
    }

    @Test(dataProvider = "loginData")
    public void TestLogin(String username, String password, boolean shouldLoginSucceed) {

        test = extent.createTest("Login Test: " + username); // Create a report entry

        LoginPage loginpg = new LoginPage(driver);
        loginpg.enterUsername(username);
       // System.out.println(username);
        loginpg.enterPassword(password);
        loginpg.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if (shouldLoginSucceed) {
            // ✅ Successful login should show the inventory page
            boolean isInventoryPageLoaded = driver.findElements(By.className("title")).size() > 0;
            Assert.assertTrue(isInventoryPageLoaded, "Login failed, inventory page not loaded!");
        } else {
            // ❌ Failed login should display an error message
            boolean isErrorDisplayed = driver.findElements(By.cssSelector(".error-message-container")).size() > 0;
            Assert.assertTrue(isErrorDisplayed, "Login should fail, but no error message found!");
        }
    }

    @AfterMethod
    public void resetSession() {
        driver.manage().deleteAllCookies(); // Clears session before next test
        driver.get("https://www.saucedemo.com"); // Reload login page
    }
}
