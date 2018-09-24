package com.hellofresh.selenium.driverbase;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.data.common.Common;


public class DriverManagerFactory extends Common{
	
	
	
	//This class is being called from test 
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	public static DriverManager getManager() throws  IOException 
	{		    
	        DriverManager driverManager = null;
	        String browser=testData.getBrowser();
	        String browserDefaultLanguage=testData.getBrowserDefaultLanguage();
	        String macBinaryPath= testData.getMacChromeBinaryPath();
	        String linuxBinaryPath=testData.getLinuxChromeBinaryPath();
	        String windowsBinaryPath=testData.getWindowsChromeBinaryPath();

	        switch (browser) {
	            case "chrome":
	            	default:
	                driverManager = new ChromeDriverManager(browser,browserDefaultLanguage, macBinaryPath, linuxBinaryPath, windowsBinaryPath );
	                break;
	            case "firefox":
	                driverManager = new FirefoxDriverManager(browser, browserDefaultLanguage);
	                break;
	            
	        }
	        return driverManager;

	    }



	
	}


