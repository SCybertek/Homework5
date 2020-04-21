package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase1and2 extends AbstractTestBase {


    @Test (description = "Verify that view, edit and delete options are available")
    public void optionsVisibilityTest(){

//        test = report.createTest("Verify options availability");
//        LoginPage loginPage = new LoginPage();
//        loginPage.login();
//        loginPage.navigateTo("Activities", "Calendar Events");
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        actions.moveToElement(calendarEventsPage.getThreeDots()).
                pause(1000).
                build().
                perform();
        Assert.assertTrue(calendarEventsPage.options());
    }

    @Test (description = "Verify that ONLY title column is displayed ")
    public void gridOptionsTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickGridOptions();
        List<WebElement> options = calendarEventsPage.gridOptions();

        for (int i = 1; i < options.size() ; i++) { // leave only first one (Title) selected
            options.get(i).click();
        }
        calendarEventsPage.clickClose();
        String actual = calendarEventsPage.getColumnText();
        String expected = "TITLE";
        Assert.assertEquals(expected,actual);





    }
}
