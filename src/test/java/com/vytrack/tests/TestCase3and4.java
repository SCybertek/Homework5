package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCase3and4 extends AbstractTestBase {

    @Test (description = "Verify that Safe and Close has expandable options")
    public void saveAndCloseMenuTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        calendarEventsPage.clickOnExpandButton();
        List<String > expectedOptions = Arrays.asList("Save And Close", "Save And New", "Save");
        Assert.assertEquals(expectedOptions, calendarEventsPage.allOptions(), "Options do NOT match" );

    }

    @Test (description = "Verify All Calendar Events page subtitle is Displayed")
    public void calendarEventsSubtitleTest(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        calendarEventsPage.clickOnCancel();
        BrowserUtils.wait(5);
        String expected = "All Calendar Events";
        Assert.assertEquals(calendarEventsPage.getCalendarEventsSubtitle(), expected);

    }
}
