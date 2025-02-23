package AutomationPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
  protected WebDriver driver ;
  protected WebDriverWait wait;
  
  public BasePage(WebDriver driver) {
	  this.driver = driver;
	  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 sec timeout
	  
  }
  
// Generic method to find an element (Wait + Return WebElement)
  protected WebElement findElement(By locator) {
      return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }
  
  public void waitForElementAndClick(By locator) {
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      element.click();
  }

// Enter text into a field after waiting for it to be visible
  protected void waitForElementAndSendKeys(By locator, String text) {
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      element.clear();  // Clears any existing text before entering new text
      element.sendKeys(text);
  }
  	
  // Wait for an element to be visible
  protected void waitForElementToBeVisible(By locator) {
      wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
	
  

}
