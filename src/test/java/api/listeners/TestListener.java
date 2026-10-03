package api.listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import api.utils.ExtentReportsManager;
import api.utils.ExtentTestManager;

public class TestListener implements ITestListener {
	public void onStart(ITestContext context) {
		ExtentReportsManager.getInstance();
	}
	public void onFinish(ITestContext context) {
		ExtentReportsManager.getInstance().flush();
	}
	public void onTestStart(ITestResult result) {
		ExtentTest test = ExtentReportsManager.getInstance().createTest(result.getMethod().getMethodName());
		ExtentTestManager.setExtentTest(test);
	}
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getExtentTest().pass("Name of Test Passed successfully is : "+result.getMethod().getMethodName());
		ExtentTestManager.removeExtentTest();
	}

	public void onTestFailure(ITestResult result) {		
		String time_stamp =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		ExtentTestManager.getExtentTest().fail(result.getThrowable());
		ExtentTestManager.removeExtentTest();
	}
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getExtentTest().skip("Test skipped: " + result.getMethod().getMethodName());
		ExtentTestManager.removeExtentTest();
	}

}
