package com.w2a.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = rep.startTest(result.getName().toUpperCase());

		if (!(TestUtil.isTestRunnable(result.getName(), excel))) {
			throw new SkipException("Skipping the test " + result.getName().toUpperCase() + " as the Runmode is NO");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		// ReportNG Report
		Reporter.log("Click to see screenshot");
		TestUtil.captureScreenshot();
		Reporter.log("<br><a href=" + TestUtil.screenshotName + ">Screenshot</a>");
		Reporter.log("<br/>");
		Reporter.log("<br/>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200  width=200></img></a>");
		// ExtentReport
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " is successfully executed");
		// test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ITestListener.super.onTestFailure(result);

		Reporter.log("Click to see screenshot");
		TestUtil.captureScreenshot();
		Reporter.log("<br><a href=" + TestUtil.screenshotName + ">Screenshot</a>");
		Reporter.log("<br/>");
		Reporter.log("<br/>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200  width=200></img></a>");

		test.log(LogStatus.FAIL,
				result.getName().toUpperCase() + " is Failed with Exception: " + result.getThrowable());
		// test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " Skipped the test as Run mode is NO");
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	// ISuiteListener methods
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onStart(suite);

	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		String messageBody = null;
		ISuiteListener.super.onFinish(suite);
		MonitoringMail mail = new MonitoringMail();
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenFramework01March/ExtentReport/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
