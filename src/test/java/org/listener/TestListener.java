package org.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import org.testbase.BaseClass;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener implements ITestListener {

    ExtentReports extent;
    ExtentTest test;

    static String timeStamp;
    static String reportFolder;

    @Override
    public void onStart(ITestContext context) {

        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFolder = "reports/Run_" + timeStamp;
        new File(reportFolder).mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFolder + "/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String path = captureScreenshot(result.getName());
        test.pass("Test Passed");
        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = captureScreenshot(result.getName());
        test.fail(result.getThrowable());
        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Capture screenshot with timestamp
    public String captureScreenshot(String testName) {

        TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String path = reportFolder + "/" + testName + "_" + timestamp + ".png";

        File dest = new File(path);

        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest.getAbsolutePath(); // absolute path ensures ExtentReports can find it
    }
}