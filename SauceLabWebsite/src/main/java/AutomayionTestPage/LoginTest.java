package AutomayionTestPage;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import AutomationCommonPage.ReadExcel;
import AutomationPage.CartPage;
import AutomationPage.CheckOutPage;
import AutomationPage.InventoryPage;
import AutomationPage.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "dataProviderTest1")
    public Object[][] loginTestData() {
        return new Object[][] { 
            {"locked_out_user", "sauce", false},  
            {"invalid_user", "invalid_pass", false},   
            {"standard_user", "secret_sauce", true}    
        };
    }

    @DataProvider(name = "dataProviderTest2")
    public Object[][] checkout() {
        ReadExcel.LoadExcel("src/main/resources/testdata.xlsx", "FIRST");
        int rowCount = ReadExcel.TotalRowCount();
        Object[][] data = new Object[rowCount][3];

        System.out.println("Array Size: " + data.length);

        for (int i = 0; i < rowCount; i++) {
            data[i][0] = ReadExcel.getCellData(i, 0);
            data[i][1] = ReadExcel.getCellData(i, 1);
            data[i][2] = ReadExcel.getCellData(i, 2);
        }
        return data;
    }
    
 /*
	
    @Test(priority = 1 , dataProvider = "dataProviderTest1")
    public void TestLogin(String username, String password, boolean shouldLoginSucceed) {

        test = extent.createTest("Login Test: " + username); // Create a report entry

        LoginPage loginpg = new LoginPage(driver);
        loginpg.enterUsername(username);
       // System.out.println(username);
        loginpg.enterPassword(password);
        loginpg.clickLoginButton();

        if (shouldLoginSucceed) {
            System.out.println("Login successfully with correct username & password");
           
        } else {
            // ❌ Failed login should display an error message
            boolean isErrorDisplayed = driver.findElements(By.cssSelector(".error-message-container")).size() > 0;
            Assert.assertTrue(isErrorDisplayed, "Login should fail but no error message found!");
            System.out.println("Login Failed with Incorrect credentials");
        }
    }
    */
    

    @Test(priority = 2, dataProvider = "dataProviderTest2")
    public void CheckoutProcess(String firstname, String lastname, String zipcode) {
        test = extent.createTest("Checkout Process for: " + firstname + " " + lastname);

        CartPage cart = new CartPage(driver);
        CheckOutPage checkout = new CheckOutPage(driver);
        LoginPage loginpg = new LoginPage(driver);
        loginpg.enterUsername("standard_user");
        loginpg.enterPassword("secret_sauce");
        loginpg.clickLoginButton();

        boolean isInventoryPageLoaded = driver.findElements(By.className("title")).size() > 0;
        Assert.assertTrue(isInventoryPageLoaded, "Login failed, inventory page not loaded!");
        InventoryPage inven = new InventoryPage(driver);     
        inven.AddToCart();
        
        int cartItemCount = driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).size();
        Assert.assertTrue(cartItemCount > 0, "Cart is empty! Items were not added.");
        System.out.println("totalitems in cart is : " +cartItemCount);
        System.out.println("Clicking on 'Go to Cart'...");
        inven.GoToCart();
        System.out.println("📌 Current Page URL: " + driver.getCurrentUrl());
        System.out.println("Return back to LoginTest.java file.."); 
        
        cart.ProceedToCheckout();
        System.out.println("Now come back to Logintest.java file...");
        checkout.CheckoutDetails(firstname, lastname, zipcode);
        System.out.println("Now click on continue checkout...");
        checkout.continueCheckout();
        System.out.println("after continue button...");
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement checkoutHeader = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header_container\"]/div[2]/span")));
         checkoutHeader = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header_container']/div[2]/span")));
        String checkoutPageText = checkoutHeader.getText();
        Assert.assertEquals(checkoutPageText, "Checkout: Overview", "Not on the expected checkout overview page!");
        System.out.println("Successfully reached the Checkout: Overview page.");
        checkout.checkoutComplete();
        System.out.println("checkout completed...");

        String confirmationMsg = checkout.OrderPlaced();
        Assert.assertEquals(confirmationMsg, "Thank you for your order!", "Order confirmation message mismatch!");
        
        /*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return confirm('Do you want to shop more?');";

        // Execute script and store the result
        Object result = js.executeScript(script);

        // Handle potential null values (some browsers may return null)
        boolean userChoice = result != null && (Boolean) result;

        if (userChoice) {
            driver.findElement(By.id("back-to-products")).click();
            System.out.println("User got back to cart page to shop more.");
        } else {
            System.out.println("User decided to finish shopping.");
        }
          */
        
        
        //Choice
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.confirm('Do you want to shop more..?')");
        Alert alert = driver.switchTo().alert();
        String popup = alert.getText();
        System.out.println(popup);
       
        if(popup.equals("Do you want to shop more..?")) {
        	alert.accept();
        	driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();
        	System.out.println("User wants to shop more.");
        }
        else {
        	alert.dismiss();
        	System.out.println("user don't want to shop");
        }
       

    }
}
