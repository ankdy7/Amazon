package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;


public  class SuiteListener implements ITestListener, IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation iTestAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		iTestAnnotation.setRetryAnalyzer(RetryAnalyser.class);
		
	}

	@Override
	public void onTestStart(ITestResult result) {
	
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String fileName = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+iTestResult.getMethod().getMethodName();
		File f=((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(f, new File(fileName+".png"));
	      } catch (IOException e) {
			e.printStackTrace();
	      }
		
		
		ITestListener.super.onTestFailure(iTestResult);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
	
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		ITestListener.super.onFinish(context);
	}
	
	
	
	

}
