package com.intellective.Stepdefinations;


import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.pageObjectModel.ExternalAppHomePage;
import com.intellective.utility.Constants;
import com.intellective.utility.ElementUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ExternalAppStepDefination extends BaseAction {
    private ExternalAppHomePage externalAppHomePage = new ExternalAppHomePage(DriverFactory.getDriver(), new ElementUtil(DriverFactory.getDriver()));
    @Given("User is on the login page in external application")
    public void user_is_on_the_login_page_in_external_application() {
        if(Constants.environment.equalsIgnoreCase("INT")){
            DriverFactory.getDriver().get(Constants.externalAppURL_INT);
        }else if(Constants.environment.equalsIgnoreCase("DEV")){
            DriverFactory.getDriver().get(Constants.externalAppURL_Dev);
        }

    }

    @Then("Read test data to this scenario from excel file")
    public void readTestData(){
//        ApplicationHooks applicationHooks = new ApplicationHooks();
//        String scenari_Name = applicationHooks.beforeHook();
//        readTestData(scenari_Name);

    }
    @Then("User is on the home page")
    public void user_is_on_the_home_page() {
        Assert.assertTrue(externalAppHomePage.checkTabOpened("Connecticut Department of Energy and Environmental Protection Permits and Licenses").isDisplayed());
    }
    @Then("User check tabs are present into the application")
    public void user_check_tabs_are_present_into_the_application(DataTable tabNameList) {
        List<Map<String,String>> TabName = tabNameList.asMaps();
        for(Map<String, String> e: TabName ){
                Assert.assertTrue(externalAppHomePage.getTab(e.get("TabName")).isDisplayed());
        }

    }
    @Then("User able to render on {string} tab without error")
    public void user_able_to_render_on_tab_without_error(String tabName) {
        externalAppHomePage.clickOnTab(tabName);
        List<WebElement> ele= getWebElements("MyDrafts_Row","ExternalApp");
        if(ele.size() >  1){
            System.out.println(tabName +"is render without error");
        }
    }



}
