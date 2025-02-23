package AutomationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//locators
	
	private By username = By.id("user-name");
	private By password = By.id("password");
	private By Login = By.id("login-button");
	
	
	//Mehtods for locators
	public void enterUsername(String name) {
		driver.findElement(username).sendKeys(name);
		
	}
	
	public void enterPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
		
	}
	
	public void clickLoginButton() {
		driver.findElement(Login).click();
	}
	
	

}
