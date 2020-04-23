package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCases8to12 extends AbstractTestBase {
    // 4.Click on “Create Calendar Event” button
    // 5.Select “Repeat” checkbox
    // 6.Verify that “Repeat” checkbox is selected
    // 7.Verify that “Repeat Every” radio button is selected
    // 8.Verify that “Never” radio button is selected as an “Ends” option.9.Verify that following message as a summary is displayed: “Summary: Daily every 1 day”
    @Test(description = "Verify that calendar event is repeated daily")
    public void repeatDailyEventTest() {

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckBox()); //returns true if selected
        Select select = new Select(calendarEventsPage.getRepeatsDropDown());
        String actual = select.getFirstSelectedOption().getText();
        Assert.assertEquals(actual, "Daily", "Different option is selected in dropdown");
        List<WebElement> repeatOptions = select.getOptions(); // all available options
        //to convert to String :
        List<String> repeatOptionsAsString = new ArrayList<>();
        for (WebElement each : repeatOptions) {
            repeatOptionsAsString.add(each.getText());
        }

        List<String> expectedOptions = Arrays.asList("Daily", "Weekly", "Monthly", "Yearly");
        Assert.assertEquals(repeatOptionsAsString, expectedOptions, "Options are different");

    }

    //5.Select “Repeat” checkbox
    // 6.Select “After 10 occurrences” as an “Ends” option.
    // 7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, endafter 10 occurrences”
    @Test(description = "Verify that calendar event is repeated daily and verify the message summary")
    public void repeatDailyEventTestWIthMessageSummary() {

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckBox()); //returns true if selected
        Assert.assertTrue(calendarEventsPage.neverIsSelected()); //returns true if radio button Never is selected
        Assert.assertEquals(calendarEventsPage.getSummaryMessage(), "Summary:\n" + "Daily every 1 day", "Different message is displayed");
    }

    //5.Select “Repeat” checkbox
    // 6.Select “By Nov 18, 2021” as an “Ends” option.
    // 7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
    @Test(description = "Verify that calendar event is repeated daily and ends after 10 days, with summary message")
    public void repeatDailyEventTestEndingAfter10days() {

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        calendarEventsPage.selectAfterRadioButtonAndEnter10(); //click on After radio button and enter value as 10 days
        Assert.assertEquals(calendarEventsPage.getSummaryMessage(), "Summary:\n" + "Daily every 1 day, end after 10 occurrences", "Different message is displayed");
    }
    //test11 :
    //Select “Repeat” checkbox
    // 6.Select “By Nov 18, 2021” as an “Ends” option.
    // 7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”

    @Test(description = "Verify that special message is displayed when End date is selected as Nov 18,2021")
    public void verifyMessageTest() {

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        calendarEventsPage.selectRadioButtonByAndClickOnChooseDate(); //this step is failing
        //clicking on empty input box to add in value
        Select month = new Select(calendarEventsPage.getMonth()); //date is under Select tag
        month.selectByVisibleText("Nov");
        Select year = new Select(calendarEventsPage.getYear());
        year.selectByVisibleText("2021");
        calendarEventsPage.clickOnDate();
        Assert.assertEquals(calendarEventsPage.getSummaryMessage(), "Summary:\n" + "Daily every 1 day, end by Nov 19,2021", "Different message is displayed");

    }


    //test12 :
    //Select “Repeat” checkbox
    // 6.Select “Weekly” options as a “Repeat” option
    // 7.Select “Monday and Friday” options as a “Repeat On” options
    // 8.Verify that “Monday and Friday” options are selected
    // 9.Verify that following message as a summary is displayed: “Summary: Weekly every 1 week onMonday, Friday”

    @Test(description = "Verify that special message is displayed when repeat On is selected as Monday and Friday")
    public void verifyMondayAndFridayTest() {

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.clickOnCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        Select select = new Select(calendarEventsPage.getRepeatsDropDown());
        select.selectByVisibleText("Weekly");
        calendarEventsPage.selectCheckBox("monday");
        calendarEventsPage.selectCheckBox("friday");
        BrowserUtils.wait(2);
        Assert.assertEquals(calendarEventsPage.getSummaryMessage(), "Summary:\n" + "Weekly every 1 week on Monday, Friday", "Different message is displayed");

    }




}
