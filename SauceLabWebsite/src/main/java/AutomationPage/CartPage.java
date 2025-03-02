package AutomationPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);//calls base class constructor
		 this.driver = driver; // Ensure driver is initialized
		// TODO Auto-generated constructor stub
	}
	
	private By viewCart = By.className("shopping_cart_link");
	//private By removeItem = By.className("btn btn_secondary btn_small cart_button");
	private By proceedCheckout = (By.id("checkout"));
	
	public void viewThecart() {
		waitForElementAndClick(viewCart);
		//driver.findElement(viewCart).click();
	}
	
//	public void RemoveAnItem() {
	//	driver.findElement(removeItem).click();
	//}
	
	public void ProceedToCheckout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
		// Scroll the element into view (important if it's not visible)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

		checkoutBtn.click();
		System.out.println("Navigate to final page");
			}
	

}
