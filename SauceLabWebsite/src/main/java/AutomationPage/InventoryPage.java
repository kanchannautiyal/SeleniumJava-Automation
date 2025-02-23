package AutomationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class InventoryPage extends BasePage {
	
	
	//Handles Add to Cart , sort , filter 
	
	public InventoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//if( currentUrl.contains("https://www.saucedemo.com/inventory.html" )) {
	//	System.out.println("Shopping page opens");
		
	//}
		
	private By AddToCartFirstProd = By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
	private By cartIcon = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
	private By cartDropdown = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
	private By ContinueSHopping = By.xpath("//*[@id=\"continue-shopping\"]");
     

	public void AddToCart() {
		System.out.println("Current URL: " + driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User is NOT on the inventory page!");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(AddToCartFirstProd));  // Wait for visibility
	    wait.until(ExpectedConditions.presenceOfElementLocated(AddToCartFirstProd)); // Wait for clickability
	    wait.until(ExpectedConditions.elementToBeClickable(AddToCartFirstProd)).click();
	    System.out.println("Product added to cart successfully.");
	  
	}

	public void GoToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
		waitForElementAndClick(cartIcon);
		//driver.findElement(cartIcon).click();
	}
	
	public void ContinueSHop() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContinueSHopping));
		wait.until(ExpectedConditions.elementToBeClickable(ContinueSHopping));
		driver.findElement(ContinueSHopping).click();
	}

	
	public void Dropdown(String option) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(cartDropdown));
		driver.findElement(cartDropdown).sendKeys(option);
	}
}
