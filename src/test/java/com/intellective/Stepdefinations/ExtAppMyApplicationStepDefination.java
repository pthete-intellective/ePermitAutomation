package com.intellective.Stepdefinations;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.pageObjectModel.ExternalAppHomePage;
import com.intellective.utility.Constants;
import com.intellective.utility.ElementUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ExtAppMyApplicationStepDefination extends BaseAction {

    private final ExternalAppHomePage externalAppHomePage = new ExternalAppHomePage(DriverFactory.getDriver(), new ElementUtil(DriverFactory.getDriver()));
    @Then("User click on {string} tab.")
    public void user_click_on_tab(String tabName) {
        externalAppHomePage.clickOnMyAppTab(tabName);

    }

    @Then("User checks existing case is present in my application having status not Draft")
    public void user_checks_existing_case_is_present_in_my_application_having_status_not_draft() {
        List<WebElement>  element =getWebElements("PersonalTaskTable","Internal");
        if(element.size() >=  1) {
            System.out.println("Record is present in the My application menu");
        }

    }

    @Then("User checks existing case is present in Supervisor Tasks having status not Draft")
    public void user_checks_existing_case_is_present_in_Supervisor_Tasks_having_status_not_draft() {
        List<WebElement>  element =getWebElements("SupervisorTab","Internal");
        if(element.size() >=  1) {
            System.out.println("Record is present in Supervisor tab");
        }

    }

    @Then("User open the existing case")
    public void user_open_the_existing_case() {
//        int recNo=externalAppHomePage.checkRenderSuccessful("Application Type");
        List<WebElement> list = getWebElements("PersonalTaskTable","Internal");
        if(list.size() >=  1) {
            externalAppHomePage.ContextClickOn1stRow();

        }
    }
    @Then("User open the existing case from the {string} tab.")
    public void user_open_the_existing_case_from_the_tab(String tabName) {
        List<WebElement> list=null;
        if(tabName.equalsIgnoreCase("Program Tasks")){
            list = getWebElements("TaskTable","Internal");
        }else if(tabName.equalsIgnoreCase("Supervisor Tasks"))
            list = getWebElements("SupervisorTaskTable","Internal");
        else if(tabName.equalsIgnoreCase("Personal Tasks"))
            list = getWebElements("PersonalTaskTable","Internal");
        if(list.size() >=  1) {
            externalAppHomePage.ContextClickOn1stRow();
        }
    }

    @Then("User check following tabs are present into the application")
    public void user_check_following_tabs_are_present_into_the_application(DataTable tabNameList) {
        List<Map<String,String>> TabName = tabNameList.asMaps();
        for(Map<String, String> e: TabName ){
            elementUtil.fnWaitForVisibility(elementUtil.getSpanText(e.get("TabName")), Constants.wait);
            Assert.assertTrue(externalAppHomePage.existingtabName(e.get("TabName")).isDisplayed());
        }

    }

    @Then("User check application allow user to navigate on {string} tab without error")
    public void user_check_application_allow_user_to_navigate_on_tab_without_error(String tabName) {
        externalAppHomePage.clickOnExistingCaseTab(tabName);
    }



}

