package com.vytrack.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public abstract class AbstractTestBase {

    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;
    protected BrowserUtils browserUtils;

    @BeforeTest
    //specify reports
    @Parameters("reportName")
    public void setup(@Optional String reportName){

        reportName = reportName == null ? "report.html" : reportName + ".html"; //making our report dynamic
        report = new ExtentReports(); //one report for everyone

        String reportPath = "";
        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }

        System.out.println("Report name: " +reportName);

        htmlReporter = new ExtentHtmlReporter(reportPath);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Result");


    }

    @AfterTest
    public void afterTest(){
        report.flush();
    }


    @BeforeMethod
    public void setup(){
        Driver.getDriver().get(ConfigurationReader.getProperty("qa1"));
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),20);
        actions = new Actions(Driver.getDriver());
        //test = report.createTest("Verify options availability");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        loginPage.navigateTo("Activities", "Calendar Events");
    }
    //Before method also includes these steps that are common for every test :
    //1.Go to “https://qa1.vytrack.com/"
    // 2.Login as a store manager
    // 3.Navigate to “Activities -> Calendar Events”

    @AfterMethod
    public void teardown(ITestResult iTestResult) throws IOException {
        //if test failed, take screenshot
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            //screenshot will have a name of the test
            String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
            test.fail(iTestResult.getName());//attach test name that failed
            test.addScreenCaptureFromPath(screenshotPath, "Failed");//attach screenshot
            test.fail(iTestResult.getThrowable());//attach console output
        }

        Driver.closeDriver();
    }


}
