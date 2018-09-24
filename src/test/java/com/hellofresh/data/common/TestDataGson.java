package com.hellofresh.data.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;





public class TestDataGson {
	
	
	
    private static TestDataGson testData;


	String browser;
	
	
    String MacChromeBinaryPath;
	
	
    String LinuxChromeBinaryPath;
	
	
    String WindowsChromeBinaryPath;
	
	
    String environment;
	
	
	
	
    String browserDefaultLanguage;
    
    
    public List<PARAM> getListparam() {
		return listparam;
	}


	public void setListparam(List<PARAM> listparam) {
		this.listparam = listparam;
	}



	List<PARAM> listparam;
    
    
    static class PARAM {
    	
    	String environment;
        
        String key;

      
        String value;
    }



	
   List<URL> listurl;

	Logger log = LoggerFactory.getLogger(TestDataGson.class);
	
	
	

	static class URL {
       
        String environment;

      
        String baseurl;
    }

    
	
	
	

    public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public String getMacChromeBinaryPath() {
		return MacChromeBinaryPath;
	}


	public void setMacChromeBinaryPath(String macChromeBinaryPath) {
		MacChromeBinaryPath = macChromeBinaryPath;
	}


	public String getLinuxChromeBinaryPath() {
		return LinuxChromeBinaryPath;
	}


	public void setLinuxChromeBinaryPath(String linuxChromeBinaryPath) {
		LinuxChromeBinaryPath = linuxChromeBinaryPath;
	}


	public String getWindowsChromeBinaryPath() {
		return WindowsChromeBinaryPath;
	}


	public void setWindowsChromeBinaryPath(String windowsChromeBinaryPath) {
		WindowsChromeBinaryPath = windowsChromeBinaryPath;
	}


	public String getBrowser() {
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	public String getBrowserDefaultLanguage() {
		return browserDefaultLanguage;
	}


	public void setBrowserDefaultLanguage(String browserDefaultLanguage) {
		this.browserDefaultLanguage = browserDefaultLanguage;
	}
	
	
	public static TestDataGson get(String filename) {
        
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filename)) {

			// Convert JSON to Java Object
        	testData = gson.fromJson(reader, TestDataGson.class);
            System.out.println(testData.getBrowser());
            
            return testData;

			// Convert JSON to JsonElement, and later to String
            /*JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);*/
   
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		return testData;
	
	
	}
    
    
    
    
    
   public String getUrlForEnvironment() {
	   
	   final String environment = getEnvironment(testData);
	   testData.setEnvironment(environment);

        if (listurl != null) {
            final URL url = (listurl).stream()
                    .filter(p -> p.environment.equals(getEnvironment()) || p.environment.equals(""))
                    .sorted((p1, p2) -> p2.environment.compareTo(p1.environment))
                    .findFirst().orElse(null);
            if (url != null) {
            	log.info("url being returned is"+ url.baseurl);
                return url.baseurl;
            }
        }
        
        return null;
    }
   
   
   
   private static String getEnvironment(final TestDataGson td) {

       String environment = td.getEnvironment();
       
       if (System.getProperty("qa.environment") != null) {
    	   environment = System.getProperty("qa.environment");

       } else if (System.getProperty("user.dir") != null) {
           final String fileName = System.getProperty("user.home") + File.separator + ".qa-test-hf-new" + File.separator + "environment.properties";
           final File propFile = new File(fileName);
           if (propFile.isFile()) {
               final Properties prop = new Properties();

               try {
                   try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                       prop.load(fileInputStream);
                       final String current = prop.getProperty("current");
                       if (current != null && !current.isEmpty()) {
                    	   environment = current;
                       }
                   }
               } catch (final IOException ex) {
                   //log.info(TestDataGson.class.getName()).log(Level.SEVERE, "could not read " + fileName, ex);
               }
           }

       }

       return environment;
   }

   
   
   
   public String getParam(final String key) {
       if (listparam != null) {
           final PARAM get = listparam.stream()
                   .filter(p -> p.key.equals(key))
                   .filter(p -> p.environment.equals(getEnvironment()) || p.environment.equals(""))
                   .sorted((p1, p2) -> p2.environment.compareTo(p1.environment))
                   .findFirst().orElse(null);
           if (get != null) {
               return get.value;
           }
       }
       
       return null;
   }

  
}


