package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage extends AbstractBasePage{

    @FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Testers Meeting')]/following-sibling::td//a[@class='dropdown-toggle']")
    private WebElement threeDots;

    @FindBy (css = "a[title='Grid Settings']")
    private WebElement gridSettings;

    @FindBy (xpath = "//table[@class='grid table-hover table table-condensed']/tbody/tr/td[3]")
    private WebElement allOptions; // all options => try to loop and select only first index ?

    @FindBy (className = "close")
    private WebElement close;

    @FindBy (className = "grid-header-cell__label")
    private WebElement titleLocator;

    @FindBy (partialLinkText = "Create Calendar")
    private WebElement createCalendarEventButton;

    @FindBy(xpath= "//li[@class='launcher-item']//a[@title='View']")
    private WebElement view;

    @FindBy(xpath= "//li[@class='launcher-item']//a[@title='Edit']")
    private WebElement edit;

    @FindBy(xpath = "//li[@class='launcher-item']//a[@title='Delete']")
    private WebElement delete;

    @FindBy (xpath = "//div[@class='btn-group pull-right']//a")
    private WebElement expandButton;

    @FindBy (css = "[class^='btn back icons-holder']")
    private  WebElement cancelButton;

    @FindBy (xpath = "(//h1)[2]")
    private WebElement calendarEventsSubtitle;

    @FindBy (css = "[id^='time_selector_oro_calendar_event_form_start']")
    private  WebElement startTime;

    @FindBy (css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy (css = "[id^='oro_calendar_event_form_allDay-uid'")
    private WebElement allDayCheckbox;

    @FindBy (css = "[id^='date_selector_oro_calendar_event_form_start-uid']")
    private WebElement startDate;

    @FindBy (css = "[id^='date_selector_oro_calendar_event_form_end-uid']")
    private WebElement endDate;

    @FindBy(xpath = "//li[text()='9:00 PM']")
    private WebElement setTime;

    @FindBy (css = "[id^='recurrence-repeat']")
    private WebElement repeatCheckBox;

    @FindBy (css = "[id^='recurrence-repeats-view']")
    private WebElement repeatsDropDown;

    @FindBy (xpath = "//*[text()='Never']/preceding-sibling::input")
    private WebElement radioButtonNever;

    @FindBy (xpath = "//*[text()='After']/preceding-sibling::input")
    private WebElement radioButtonAfter;

    @FindBy (xpath = "//*[text()='By']/preceding-sibling::input")
    private WebElement radioButtonBy;

    @FindBy (css = "[class='control-group recurrence-summary alert-info']")
    private WebElement summaryMessage;


    @FindBy (xpath = "(//*[@class='recurrence-subview-control__datetime']//following-sibling::input)[1]")
    private WebElement placeHolderForDate;

    @FindBy (className = "ui-datepicker-month")
    private WebElement month;

    @FindBy (className = "ui-datepicker-year")
    private WebElement year;

    @FindBy (xpath = "//table//td//a[contains(text(),'18')]")
    protected WebElement date;


    public String getSummaryMessage(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(summaryMessage));
        return summaryMessage.getText();
    }

    public boolean neverIsSelected(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(radioButtonNever));
        return radioButtonNever.isSelected();
    }

    public void selectAfterRadioButtonAndEnter10(){
            BrowserUtils.waitForPageToLoad(10);
            wait.until(ExpectedConditions.visibilityOf(radioButtonAfter)).click();
            WebElement input = driver.findElement(By.xpath("//*[text()='After']//following-sibling::input"));
            input.sendKeys("10");
            WebElement occurrences = driver.findElement(By.xpath("//*[text()='After']//following-sibling::span")) ;
            occurrences.click(); //needs to be done for summary to be updated
    }

    public void selectRadioButtonByAndClickOnChooseDate(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(radioButtonBy)).click();
        wait.until(ExpectedConditions.elementToBeClickable(placeHolderForDate)).click();

    }



    public WebElement getMonth(){
        return month;
    }
     public WebElement getYear(){
        return year;
     }

     public void clickOnDate(){
        wait.until(ExpectedConditions.elementToBeClickable(date)).click();
     }

    public WebElement getRepeatsDropDown(){
        return repeatsDropDown;
    }

    public boolean getRepeatCheckBox(){
        return repeatCheckBox.isSelected();
    }

    public void clickOnRepeatBox(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(repeatCheckBox)).click();
    }


    public void setTime900(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(setTime)).click();
    }


    public void clickAllDayBox(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(allDayCheckbox)).click();
    }

    public WebElement getThreeDots(){
      return threeDots;
    }

//    public boolean optionsAreAvailable(String option){
//        BrowserUtils.wait(2);
//        WebElement element = driver.findElement(By.xpath("//li[@class='launcher-item']//a[@title='"+ option+"']"));
//        return element.isDisplayed();
//    }

    public boolean options(){
        BrowserUtils.wait(2);
        wait.until(ExpectedConditions.visibilityOf(edit));
        wait.until(ExpectedConditions.visibilityOf(delete));
        wait.until(ExpectedConditions.visibilityOf(view));
        return true;
    }

    public void clickGridOptions(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(gridSettings)).click();
    }

    public List<WebElement> gridOptions(){
       return driver.findElements(By.xpath("//table[@class='grid table-hover table table-condensed']/tbody/tr/td[3]"));
    }

    public void clickClose(){
        wait.until(ExpectedConditions.elementToBeClickable(close)).click();
    }
    public String getColumnText(){
        return wait.until(ExpectedConditions.elementToBeClickable(titleLocator)).getText();
    }
    public void clickOnCreateCalendarEvent(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEventButton)).click();
    }

    public void clickOnExpandButton(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(expandButton)).click();
    }

    public List<String> allOptions(){
        List<WebElement> allElements = driver.findElements(By.xpath("//button[@type='submit']"));
        List<String> availableOptions = new ArrayList<>();

        for (int i = 1; i < allElements.size(); i++) {
            availableOptions.add(allElements.get(i).getText());
        }
       return availableOptions;
    }

    public void clickOnCancel(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public String getCalendarEventsSubtitle(){
        return calendarEventsSubtitle.getText();
    }

    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value"); // it is input box
    }

    public void clickStartTime(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startTime)).click();

    }

    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public boolean startDateAndEndDateDisplayed(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        wait.until(ExpectedConditions.visibilityOf(endDate));
        return (startDate.isDisplayed() && endDate.isDisplayed());

    }

    public boolean startTimeAndEndTimeIsDisplayed() {
        BrowserUtils.waitForPageToLoad(10);
        return (startTime.isDisplayed() && endTime.isDisplayed());
    }

    public void selectCheckBox(String value){
        BrowserUtils.waitForPageToLoad(10);
        driver.findElement(By.xpath("//input[@value='"+value+"']")).click();
    }

}
