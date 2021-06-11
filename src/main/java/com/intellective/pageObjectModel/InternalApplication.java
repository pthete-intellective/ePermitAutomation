package com.intellective.pageObjectModel;

import com.intellective.factory.BaseAction;
import com.intellective.utility.Constants;
import org.junit.Assert;

public class InternalApplication extends BaseAction {
//    private WebDriver driver = DriverFactory.getDriver();
    public String formName= "Internal";

    public void clickOnInternalTab(String tabname) {
        elementUtil.waitAndClick(elementUtil.getSpanText(tabname), Constants.wait);
        if (tabname.equalsIgnoreCase("Supervisor Tasks")) {
            elementUtil.fnWaitForVisibility(getWebElement( "SupervisorTab",formName), Constants.wait);
            Assert.assertTrue(getWebElement( "SupervisorTab",formName).isDisplayed());
        } else if (tabname.equalsIgnoreCase("Program Tasks")) {
            elementUtil.fnWaitForVisibility(getWebElement( "ProgramTab",formName), Constants.wait);
            Assert.assertTrue(getWebElement( "ProgramTab",formName).isDisplayed());
        } else if (tabname.equalsIgnoreCase("Personal Tasks")){
            elementUtil.fnWaitForVisibility(getWebElement( "PersonalTab",formName), Constants.wait);
            Assert.assertTrue(getWebElement("PersonalTab",formName).isDisplayed());
        }else if(tabname.equalsIgnoreCase("Common Properties")){
            elementUtil.fnWaitForVisibility(getWebElement("applicationId",formName),Constants.wait);
            Assert.assertTrue(getWebElement("applicationId",formName).isDisplayed());
        }else if(tabname.equalsIgnoreCase("Application")){
            elementUtil.fnWaitForVisibility(getWebElement("existingcaseApplicationTab",formName),Constants.wait);
            Assert.assertTrue(getWebElement("existingcaseApplicationTab",formName).isDisplayed());
        }else if(tabname.equalsIgnoreCase("Documents")){
            elementUtil.fnWaitForVisibility(getWebElement("existingCaseCaseDocTab",formName),Constants.wait);
            Assert.assertTrue(getWebElement("existingCaseCaseDocTab",formName).isDisplayed());
        }else if(tabname.equalsIgnoreCase("History/Comments")){
            Assert.assertTrue(elementUtil.getSpanText("Comment").isDisplayed());
        }

    }

}
