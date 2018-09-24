package com.hellofresh.selenium.driverbase;

import java.io.File;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class FirefoxDriverManager extends DriverManager {

    
	private String browserDefaultLanguage;
	
    public FirefoxDriverManager(String browser, String browserDefaultLanguage) {
    	this.browserDefaultLanguage=browserDefaultLanguage;
}
//	@Override
//    public void startService() {
//        if (null == chService) {
//            try {
//                chService = new ChromeDriverService.Builder()
//                    .usingDriverExecutable(new File("chromedriver.exe"))
//                    .usingAnyFreePort()
//                    .build();
//                chService.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void stopService() {
//        if (null != chService && chService.isRunning())
//            chService.stop();
//    }

   

	@Override
    public void createDriver() {
    	
    	log.info("in firefox get driver");
    	final String os = System.getProperty("os.name");
        //final String userDirectory = System.getProperty("user.dir");
    	
    	final StringBuilder geckoDriverPath = new StringBuilder();

        geckoDriverPath.append("src" + File.separator + "test" + File.separator + "resources" + File.separator + "geckodriver" + File.separator);

        if (os.contains("Linux")) {
            geckoDriverPath.append("linux64" + File.separator + "geckodriver");
        } else if (os.contains("Windows")) {
            geckoDriverPath.append("win64" + File.separator + "geckodriver.exe");
        } else if (os.contains("Mac OS")) {
            geckoDriverPath.append("mac64" + File.separator + "geckodriver");
        } else {
            //writeToErrorSummary(null, "No implementation found for Operating System: " + os);
            return;
        }
        System.setProperty("webdriver.gecko.driver", geckoDriverPath.toString());

        final FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", browserDefaultLanguage);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip,application/octet-stream");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.tabs.remote.autostart.2", false);
        profile.setPreference("javascript.enabled", true);

        final FirefoxOptions options = new FirefoxOptions();
        options.addPreference("marionette", true);
        options.addPreference("marionette.logging", "ERROR");
        options.setCapability(FirefoxDriver.PROFILE, profile);

        try {
            driver = new FirefoxDriver(options);
        } catch (final Exception e) {
            //writeToErrorSummary(null, e.getMessage());
        	log.error(e.getMessage());
        }
       
}
    
    
    
    
    
    
    
    
    
  
            
    
    
    

}