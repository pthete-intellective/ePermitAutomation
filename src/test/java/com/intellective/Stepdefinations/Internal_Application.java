package com.intellective.Stepdefinations;

import com.intellective.factory.BaseAction;
import com.intellective.pageObjectModel.InternalApplication;
import io.cucumber.java.en.Then;

public class Internal_Application extends BaseAction {
    private final InternalApplication internalApplication = new InternalApplication();

    @Then("User able to render on {string} tab without error in internal application")
    public void user_able_to_render_on_tab_without_error_in_internal_application(String tabName) {
        internalApplication.clickOnInternalTab(tabName);
    }



}
