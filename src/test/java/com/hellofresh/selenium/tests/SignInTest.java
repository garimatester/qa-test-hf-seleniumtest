package com.hellofresh.selenium.tests;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hellofresh.data.common.RandomDataGenerator;
import com.hellofresh.selenium.base.BaseSeleniumSetup;
import com.hellofresh.selenium.base.SeleniumToolException;
import com.hellofresh.selenium.pages.MyAccountPage;
import com.hellofresh.selenium.pages.SignInPage;
import com.hellofresh.testng.listners.CustomListener;

@Listeners(CustomListener.class) 

public class SignInTest extends BaseSeleniumSetup{
    
    



	private RandomDataGenerator registrationData;




	@Override
    @BeforeClass
    @Parameters({"testdatafile"})
   	public void setUp(@Optional("src/test/resources/configurableData.json")String testDataFile) throws  IOException, SeleniumToolException {
    	super.setUp(testDataFile);
    	//driver=super.driver;
    	//wait = super.wait;
    	registrationData= new RandomDataGenerator();
    	
    }

    
    
    
    @Test
      public void signInTest() throws InterruptedException {
    	log.info("sign intest");
        final SignInPage mainpage = getHomePage(driver);
       String fullName= mainpage.fillRegistrationData(registrationData);
        final MyAccountPage accountPage=new MyAccountPage(driver, wait);
        accountPage.validateElements(testData,fullName);
       
        

    	
    }
    
   

    
    
}
