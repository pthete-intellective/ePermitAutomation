package com.intellective.pageObjectModel;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.utility.Constants;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseAction {
    private final WebDriver driver = DriverFactory.getDriver() ;


    public String formName  ="ILogin";
    public WebElement userName = getWebElement("userIdText", formName);
    public WebElement passWord = getWebElement("passwordText", formName);
    public WebElement homepageLogo=getWebElement("headerOfLoginPage",formName);
    public WebElement titleAfterLogin ;


    public void fnLoginApplication(String userId, String password) {
        elementUtil.fnWaitForVisibility(userName, Constants.wait);
        Assert.assertTrue(elementUtil.fnWaitForVisibility(userName, Constants.wait));
        userName.sendKeys(userId);
        passWord.clear();
        Assert.assertTrue(elementUtil.fnWaitForVisibility(passWord, Constants.wait));
        passWord.sendKeys(password);
        elementUtil.waitAndClick(getWebElement("logInButton", formName), Constants.wait);
        Assert.assertTrue(elementUtil.fnWaitForVisibility(getWebElement("stateOfCTLogo",formName),Constants.wait));
        titleAfterLogin = getWebElement("stateOfCTLogo",formName);
    }
    public void getTitle(){
        driver.getTitle();
    }
    public String TitleOfPageAfterLogin(){
        return driver.getTitle();
    }

}
