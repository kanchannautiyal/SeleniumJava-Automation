package AutomationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
		 this.driver = driver; // Ensure driver is initialized
		// TODO Auto-generated constructor stub
	}
	
	private By viewCart = By.className("shopping_cart_link");
	//private By removeItem = By.className("btn btn_secondary btn_small cart_button");
	private By proceedCheckout = By.id("checkout");
	
	public void viewThecart() {
		waitForElementAndClick(viewCart);
		//driver.findElement(viewCart).click();
	}
	
//	public void RemoveAnItem() {
	//	driver.findElement(removeItem).click();
	//}
	
	public void ProceedToCheckout() {
		waitForElementAndClick(proceedCheckout);
			}
	

}
