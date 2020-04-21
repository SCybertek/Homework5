package com.vytrack.pages;

import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractBasePage{

    @FindBy (id = "prependedInput")
    private WebElement usernameInput;

    @FindBy (id = "prependedInput2")
    private WebElement passwordInput;

    /**
     * login in using credentials in Configuration properties
     */
    public void login(){
        this.usernameInput.sendKeys(ConfigurationReader.getProperty("store_manager"));
        this.passwordInput.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }






}
