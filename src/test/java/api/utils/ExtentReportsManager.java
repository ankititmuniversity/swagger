package api.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager {
	private static ExtentReports extentReports;

	public static ExtentReports getInstance() {
		if(extentReports == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/ExtentReport.html");
			reporter.config().setDocumentTitle("Test Results");
			reporter.config().setReportName("Swagger API Report");
			
			extentReports = new ExtentReports();
			extentReports.attachReporter(reporter);
			extentReports.setSystemInfo("Tester", "Ankit Kumar");
			extentReports.setSystemInfo("Environment", "QA Automation Lead");
			extentReports.setSystemInfo("Browser", "Chrome");
		}
		return extentReports;
	}
}
