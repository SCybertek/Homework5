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

    @Test(description = "Verify that when start time is 9:00 PM end time is 10:00 PM")
    public void startTimeAndEndTimeTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        calendarEventsPage.clickStartTime();
        BrowserUtils.wait(2);
        WebElement time = driver.findElement(By.xpath("//div[@class='ui-timepicker-wrapper']//li[contains(text(),'9:00 PM')]"));
        wait.until(ExpectedConditions.visibilityOf(time));
        time.click();
//???


       // Assert.assertEquals(calendarEventsPage.getEndTime(),"10:00 PM");

    }

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
