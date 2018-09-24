package com.hellofresh.selenium.pages;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.selenium.base.BaseSeleniumWebPageAction;




public class OrderConfirmationPage extends BaseSeleniumWebPageAction{
	
	

	private By headingLocator=By.cssSelector("h1");
	private By shippingtabLocator=By.xpath("//li[@class='step_done step_done_last four']");
	private By paymentTabLocator=By.xpath("//li[@id='step_end' and @class='step_current last']");
    private By message=By.xpath("//*[@class='cheque-indent']/strong");
	protected  WebDriverWait wait;
	protected WebDriver driver;
	
	
	
    
	public OrderConfirmationPage(final WebDriver driver, final WebDriverWait wait) {
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
	
	
	public void validateElements() throws InterruptedException {
		
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
        assertEquals(heading.getText(), "ORDER CONFIRMATION");
        assertTrue(driver.findElement(shippingtabLocator).isDisplayed());
        assertTrue(driver.findElement(paymentTabLocator).isDisplayed());
        assertTrue(driver.findElement(message).getText().contains("Your order on My Store is complete."));
        assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));



	}
	
	

	
	

}












