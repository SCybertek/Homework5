package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.DateTimeUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCases5_6_7 extends AbstractTestBase {

    public WebDriver driver;

    //Verify that difference between end and start time is exactly 1 hour
    @Test(description = "Verify that time difference between start and end is 1 hour")
    public void startTimeAndEndTime1HourTest() {
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        String startTime = calendarEventsPage.getStartTime();
        String endTime = calendarEventsPage.getEndTime();

        String format = "h:mm a";

        long actual = DateTimeUtilities.getTimeDifference(startTime,endTime,format);
        Assert.assertEquals(actual,1);
    }

    //5.Select “9:00 PM” as a start time
    // 6.Verify that end time is equals to “10:00 PM”
    @Test(description = "Verify that when start time is 9:00 PM end time is 10:00 PM")
    public void startTimeAndEndTimeTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        calendarEventsPage.clickStartTime();
        BrowserUtils.wait(2);
        calendarEventsPage.setTime900();
        //i used driver in here but it gave me nullPointerException, due to our singelton driver class

        Assert.assertEquals(calendarEventsPage.getEndTime(),"10:00 PM");

    }

    //5.Select “All-Day Event” checkbox
    // 6.Verify that “All-Day Event” checkbox is selected
    // 7.Verify that start and end time input boxes are not displayed8.Verify that start and end date input boxes are displayed
    @Test (description = "Verify that when you select all day event start and end time are not displayed" )
    public void allDayEventTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickAllDayBox();
        boolean startEndDateIsDisplayed = calendarEventsPage.startDateAndEndDateDisplayed();
        Assert.assertTrue(startEndDateIsDisplayed);
        Assert.assertFalse(calendarEventsPage.startTimeAndEndTimeIsDisplayed());



    }

}
