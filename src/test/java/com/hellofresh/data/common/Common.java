package com.hellofresh.data.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.hellofresh.selenium.base.SeleniumToolException;

public class Common {


	protected Logger log = LoggerFactory.getLogger(this.getClass());
	protected static TestDataGson testData;
	
	

    /**
     * A divider for logs consisting of plus signs.
     */
    private static final String LOG_DIVIDER_PLUS = "++++++++++++++++++++++++++++++++++++++++++++++++++";

    /**
     * A divider for logs consisting of minus signs.
     */
    private static final String LOG_DIVIDER_MINUS = "--------------------------------------------------";

   
   



	/**
	 * Reads the test data from a file.
	 *
	 * @param testDataFile Name of the xml file. When an empty string is passed, the name is build using the
	 *            instantiating class + .xml.
	 */

	@BeforeMethod
	@Parameters({ "testdatafile" })
	protected void setUp(String testDataFile) throws  IOException, SeleniumToolException {
		log.info("Common::setUp()");
		readTestData(testDataFile);
	}


	protected void readTestData(String testDataFile) {


		try {
			log.info("Common::readTestData()");
			if (testDataFile == null || testDataFile.length() < 1) {
				log.info("No test data file is created");
			}
			testData=TestDataGson.get(testDataFile);
			log.info("+ Running the test " + testDataFile + " against: " +""+ testData); //stagingEnv);
			 logPlusDivider();



		} catch (final Exception ex) {
			java.util.logging.Logger.getLogger(Common.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			//errorSummary.add(null, "could not read test data: " + testDataFile);
			Assert.fail("could not read test data: " + testDataFile);
		}
	}

	
	
	/**
     * Logs a row of plus signs, for prettier logs.
     */
    protected void logPlusDivider() {
        log.info(LOG_DIVIDER_PLUS);
    }
    
    
    /**
     * Logs a row of minus signs, for prettier logs.
     */
    protected void logMinusDivider() {
        log.info(LOG_DIVIDER_MINUS);
    }



}
