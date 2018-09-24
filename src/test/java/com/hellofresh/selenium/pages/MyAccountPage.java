package com.hellofresh.selenium.pages;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.data.common.TestDataGson;
import com.hellofresh.selenium.base.BaseSeleniumWebPageAction;




public class MyAccountPage extends BaseSeleniumWebPageAction{
	
	

	private By headingLocator=By.cssSelector("h1");
	private By straccountInfo=By.className("info-account");
    private By strlogout=By.className("logout");
	protected  WebDriverWait wait;
	protected WebDriver driver;

	public MyAccountPage(final WebDriver driver, final WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
	}
	
	
	
	
	

	/**
	 * Close browser.
	 * @param testData 
	 * @param registrationData 
	 * @throws InterruptedException 
	 *
	 * @throws Exception the exception
	 */
	/*protected void closeBrowser() throws Exception {
		log.info("Close browser");
		driver.close();
	}

*/
	
	
	public void validateElements(TestDataGson testData, String fullName) throws InterruptedException {
		
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
        assertEquals(heading.getText(), "MY ACCOUNT");
        assertEquals(driver.findElement(By.className("account")).getText(), fullName);
        assertTrue(driver.findElement(straccountInfo).getText().contains("Welcome to your account."));
        assertTrue(driver.findElement(strlogout).isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}
	
	
/*public void validateElementsLogin(TestDataGson testData, String fullName) throws InterruptedException {
		
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
        assertEquals(heading.getText(), "MY ACCOUNT");
        assertEquals(driver.findElement(straccountName).getText(), fullName);
        assertTrue(driver.findElement(straccountInfo).getText().contains("Welcome to your account."));
        assertTrue(driver.findElement(strlogout).isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}
	*/
	
	

}












