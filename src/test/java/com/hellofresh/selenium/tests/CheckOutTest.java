package com.hellofresh.selenium.tests;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hellofresh.selenium.base.BaseSeleniumSetup;
import com.hellofresh.selenium.base.SeleniumToolException;
import com.hellofresh.selenium.pages.LogInPageWidget;
import com.hellofresh.selenium.pages.OrderConfirmationPage;
import com.hellofresh.selenium.pages.OrderPage;
import com.hellofresh.testng.listners.CustomListener;



@Listeners(CustomListener.class) 

public class CheckOutTest extends BaseSeleniumSetup{
    
    String existingUserEmail = "hf_challenge_123456@hf12345.com";
    String existingUserPassword = "12345678";
    
    //protected WebDriver driver;
	//protected WebDriverWait wait;

    
    @Override
    @BeforeClass
    @Parameters({"testdatafile"})
   	public void setUp(@Optional("src/test/resources/configurableData.json")String testDataFile) throws  IOException, SeleniumToolException {
    	super.setUp(testDataFile);
    	//driver=super.driver;
    	//wait = super.wait;
    }

    
    
   @Test
    public void checkoutTest() throws InterruptedException{
       
	    log.info("checkout test");
        LogInPageWidget loginpage;
		loginpage = getLoginPage(driver, wait);
		loginpage.doLogin();
        final OrderPage checkoutPage=new OrderPage(driver, wait);
        checkoutPage.placeOrder(testData);
        final OrderConfirmationPage orderconfirmationPage = getOrderConfirmationPage(driver);
        orderconfirmationPage.validateElements();}
            
        
      
  }
