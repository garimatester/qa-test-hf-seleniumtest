package com.hellofresh.selenium.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.data.common.TestDataGson;
import com.hellofresh.selenium.base.BaseSeleniumWebPageAction;





public class OrderPage extends BaseSeleniumWebPageAction{


	private By linkWomen=By.linkText("Women");
	By  FadedShortSleeve=By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
	private By submit=By.name("Submit");
    private By proceedToCheckout=By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
    private By cartNavigation=By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
    private By processAddress=By.name("processAddress");
    private By unifromCGV=By.id("uniform-cgv");
    private By processCarrier=By.name("processCarrier");
    private By bankWire=By.className("bankwire");
    private By cartNavigationButton=By.xpath("//*[@id='cart_navigation']/button");
	protected  WebDriverWait wait;
	protected WebDriver driver;

	public OrderPage(final WebDriver driver, final WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
	}
	
	
	
	
	

	/**
	 * Close browser.
	 * @throws InterruptedException 
	 *
	 * @throws Exception the exception
	 */
	/*protected void closeBrowser() throws Exception {
		log.info("Close browser");
		driver.close();
	}

*/
	


	public void placeOrder(TestDataGson testData) throws InterruptedException {
		
		  wait.until(ExpectedConditions.visibilityOfElementLocated(linkWomen));
		  click(linkWomen);
		  
		 
		  
		  
		  String dressName = testData.getParam("FadedShortSleeve");
		  driver.findElement(By.xpath(dressName)).click();
		  driver.findElement(By.xpath(dressName)).click();

		  
		  
		  Thread.sleep(5000);

		
		  wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
		  click(submit);
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckout));
		  click(proceedToCheckout);
		  
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(cartNavigation));
		  click(cartNavigation);
		  
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(processAddress));
		  click(processAddress);
		  
	     wait.until(ExpectedConditions.visibilityOfElementLocated(unifromCGV)).click();

		  
		  
		 wait.until(ExpectedConditions.visibilityOfElementLocated(processCarrier));
		 click(processCarrier);
		  
		 
	      wait.until(ExpectedConditions.visibilityOfElementLocated(bankWire)).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(cartNavigationButton));
		  click(cartNavigationButton);
		  
		  
		 

		
	}


}












