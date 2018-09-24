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
import com.hellofresh.selenium.pages.MyAccountPage;
import com.hellofresh.testng.listners.CustomListener;

@Listeners(CustomListener.class) 

public class LogInTest extends BaseSeleniumSetup{
    
    String existingUserEmail = "hf_challenge_123456@hf12345.com";
    String existingUserPassword = "2345678";

    
    @Override
    @BeforeClass
    @Parameters({"testdatafile"})
   	public void setUp(@Optional("src/test/resources/configurableData.json")String testDataFile) throws  IOException, SeleniumToolException {
    	
    	super.setUp(testDataFile);
    	//driver=super.driver;
    	//wait = super.wait;
    }

    
    @Test
    public void logInTest() throws InterruptedException {
     String fullName = "Joe Black";
    	log.info("login test");
        final LogInPageWidget loginpage = getLoginPage(driver,wait);
        loginpage.doLogin();
        final MyAccountPage accountPage=new MyAccountPage(driver, wait);
        accountPage.validateElements(testData,fullName);
       
    	
       
        
    }
    
    
    

   
    

    
    
}
