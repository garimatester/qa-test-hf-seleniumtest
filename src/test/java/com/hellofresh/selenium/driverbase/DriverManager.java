package com.hellofresh.selenium.driverbase;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.data.common.Common;

public abstract class DriverManager extends Common {
	
	    protected WebDriver driver;
	    //protected abstract void startService();
	    //protected abstract void stopService();
	    protected abstract void createDriver();
	    protected Logger log = LoggerFactory.getLogger(DriverManager.class);

	    public void quitDriver() {
	        if (null != driver) {
	            driver.quit();
	            driver = null;
	        }

	    }

	    public WebDriver getDriver() {
	        if (null == driver) {
	            //startService();
	            createDriver();
	        }
	        return driver;
	    }

}
