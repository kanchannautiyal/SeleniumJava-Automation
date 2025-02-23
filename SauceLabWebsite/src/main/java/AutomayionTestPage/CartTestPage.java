package AutomayionTestPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationCommonPage.ReadExcel;
import AutomationPage.CartPage;
import AutomationPage.CheckOutPage;
import AutomationPage.InventoryPage;

public class CartTestPage extends BaseTest {
	
	private InventoryPage inven;
    private CartPage cart;
    private CheckOutPage checkout;
    //protected WebDriver driver;

	// Adding multiple products to the cart and verifying they are added.
	// Completing a checkout process and verifying the order confirmation.
	
	
	
	@Test(priority=1)
	public void TestProd() {
		
		 cart = new CartPage(driver);
		 inven = new InventoryPage(driver);
		//Adding first product to the cart
		inven.AddToCart();
		String currenturl1 = driver.getCurrentUrl();
		Assert.assertTrue(currenturl1.contains("inventory"), "Product addedd sucessfulyy");
		//goto cart 
		inven.GoToCart();
		//verify the user is on cart page   
		//Checks whether the URL contains the word "cart".
		//If the user is correctly redirected to the Cart Page, the URL will contain "cart".
		String currenturl = driver.getCurrentUrl();
		Assert.assertTrue(currenturl.contains("cart"),"User failed to navigate to cart page");	
	}
	
    @BeforeMethod(dependsOnMethods = "setup")
	public void setupPages() {
		
		  if (driver == null) {
		        throw new IllegalStateException("Driver is not initialized!");
		    }
		
	   inven = new InventoryPage(driver);
	   cart = new CartPage(driver);
	     checkout = new CheckOutPage(driver);
	}
	
	
	@DataProvider(name = "CheckoutProcess")
	public Object[][] checkout(){
		ReadExcel.LoadExcel("src/main/resources/testdata.xlsx", "FIRST");
		 int rowCount = ReadExcel.TotalRowCount();
		 Object[][] data = new Object[rowCount][3];  // 2D Array of type Objet so that we can loop 
		 
		 System.out.println("Array Size.." +data.length);
		 
		 for(int i=0;i<rowCount;i++) {
			 data[i][0] = ReadExcel.getCellData(i, 0);
			 data[i][1] = ReadExcel.getCellData(i, 1);
			 data[i][2] = ReadExcel.getCellData(i, 2);
		 }
		
		return data;
		
	}
	
	
	
	@Test(priority=2, dataProvider = "CheckoutProcess")
    public void CheckoutProcess(String firstname , String lastname , String Zipcode) {
		inven.AddToCart();
	    inven.GoToCart();
	    
	    cart.ProceedToCheckout();
	    checkout.CheckoutDetails(firstname, lastname, Zipcode);
	    checkout.ContinueCheckout();
	    checkout.checkoutComplete();
	    
	    String ConfirmationMsg = checkout.OrderPlacedtext();
	    Assert.assertEquals(ConfirmationMsg, "Thank you for your order!", "Order confirmation message mismatch!");
	}
}

