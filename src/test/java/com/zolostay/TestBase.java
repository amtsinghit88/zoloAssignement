package com.zolostay;

import com.zolostay.utils.ReporterUtil;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import java.lang.reflect.Method;

public class TestBase {

	protected Logger logger;
	public static String emailAddress;
	public static String emailAddressPwd;

	public TestBase() {
		logger = Logger.getLogger(com.zolostay.TestBase.class);
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {

	}

	@Step("{0}")
	public void logToReport(String message) {
		Reporter.log(message,true);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTC(Method mthd) {
		ReporterUtil.reportTC(mthd.getName(), "Started");
	}

	@AfterMethod(alwaysRun = true)
	public void logTestResult(ITestResult result, Method mthd) throws InterruptedException {
		if (null != result.getThrowable()) {
			Reporter.log("Exception : " + result.getThrowable().getMessage(), true);
		}

		ReporterUtil.reportTC(mthd.getName(), "Completed");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
	}
}
