package com.hellofresh.testng.listners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.hellofresh.selenium.base.BaseSeleniumSetup;



public class CustomListener extends BaseSeleniumSetup implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		//log.info("Test Start------"+result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//log.info("Test Passed-----"+result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("Failed Test Screenshot Supposed");
		failed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
        //log.info("Start Of Execution(TEST)->"+ context.getName());
		
	}

	@Override
	public void onFinish(ITestContext context) {
        //log.info("End Of Execution(TEST)->"+ context.getName());
		
	}

}
