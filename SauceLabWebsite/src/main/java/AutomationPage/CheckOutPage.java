package AutomationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends BasePage{

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	//Enters checkout details (First Name, Last Name, Zip Code).
	//Completes the checkout process.
	//Verifies if the order confirmation message appears.
   //https://www.saucedemo.com/checkout-step-one.html
	
	
	private By firstName = By.xpath("//*[@id=\"first-name\"]");
	private By lastName = By.xpath("//*[@id=\"last-name\"]");
	private By zipCode = By.xpath("//*[@id=\"postal-code\"]");
	private By ContinueBtn = By.xpath("//*[@id=\"continue\"]");
	private By finishBtn = By.xpath("//*[@id=\"finish\"]");
	
	// hard code all values
	/*
	 public void CheckoutDetails() {
		driver.findElement(firstname).sendKeys("Sukiriti");
		driver.findElement(lastname).sendKeys("tiwari");
		driver.findElement(zipcode).sendKeys("201301");
		driver.findElement(ContinueBtn).click();
	}
	*/
	
	//Here we will send data from test file 
	
	public void CheckoutDetails(String firstname , String lastname ,String zipcode) {
		
		waitForElementAndSendKeys(firstName, firstname);
		driver.findElement(lastName).sendKeys(lastname);
		driver.findElement(zipCode).sendKeys(zipcode);
		driver.findElement(ContinueBtn).click();
	}
	
	
	public void checkoutComplete() {
		driver.findElement(finishBtn).click();
	}
	public void ContinueCheckout() {
		driver.findElement(ContinueBtn).click();
	}
	
	public String OrderPlacedtext() {
		String orderconfirmation = driver.findElement(By.className("complete-header")).getText();
		return orderconfirmation;
	}
	
	
	public void OrderPlaced() {
		 String text =driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
	       if(text.equalsIgnoreCase("Checkout: Complete!")){
	    	   System.out.println("Order placed confirmation done");
			
		}
	       else {
	    	   System.out.println("Order doesen't placed");
	       }
		
	}
	
	
	

}
