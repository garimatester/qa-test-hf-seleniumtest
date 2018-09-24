package com.hellofresh.selenium.driverbase;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeDriverManager extends DriverManager {

    //private ChromeDriverService chService;
	private String macBinaryPath;
	private String linuxBinaryPath;
	private String windowsBinaryPath;
	private String browserDefaultLanguage;

    public ChromeDriverManager(String browser, String browserDefaultLanguage, String macBinaryPath, String linuxBinaryPath, String windowsBinaryPath) {
		
    	this.browserDefaultLanguage=browserDefaultLanguage;
    	this.macBinaryPath=macBinaryPath;
    	this.linuxBinaryPath=linuxBinaryPath;
    	this.windowsBinaryPath=windowsBinaryPath;
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
    	log.info("in chrome get driver");
    	final String os = System.getProperty("os.name");
        final String userDirectory = System.getProperty("user.dir");
    	final ChromeOptions chromeOptions = new ChromeOptions();
        final StringBuilder chromeDriverPath = new StringBuilder();
        chromeDriverPath.append(userDirectory);
        chromeDriverPath.append(
                File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "chromedriver" + File.separator);

        if (os.contains("Linux")) {
            chromeDriverPath.append("linux64" + File.separator + "chromedriver");
            chromeOptions.setBinary(linuxBinaryPath);
        } else if (os.contains("Windows")) {
            chromeDriverPath.append("win32" + File.separator + "chromedriver.exe");
            chromeOptions.setBinary(windowsBinaryPath);
        } else if (os.contains("Mac OS")) {
            chromeDriverPath.append("mac64" + File.separator + "chromedriver");
            chromeOptions.setBinary(macBinaryPath);
        } else {
            //writeToErrorSummary(null, "No implementation found for Operating System: " + os);
        	
        	log.error( "No implementation found for Operating System: " + os);
            return;
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
        chromeOptions.addArguments("--lang=" + browserDefaultLanguage);
        
       
        try {
            driver = new ChromeDriver(chromeOptions);

        } catch (final Exception e) {
            //writeToErrorSummary(null, e.getMessage());
        	log.error(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
  
            
    
    
    

}