package com.intellective.Stepdefinations;

import com.intellective.factory.DriverFactory;
import com.intellective.pageObjectModel.LoginPage;
import com.intellective.utility.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class LoginSetDef {
    private LoginPage loginPage ;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        if(Constants.environment.equalsIgnoreCase("INT")){
            DriverFactory.getDriver().get(Constants.internalAppURL_INT);
        }else if(Constants.environment.equalsIgnoreCase("DEV")){
            DriverFactory.getDriver().get(Constants.internalAppURL_Dev);
        }

        loginPage = new LoginPage();
        Assert.assertTrue(loginPage.userName.isDisplayed());
    }
    @Then("User verify the username and password field on login page")
    public void user_verify_the_username_and_password_field_on_login_page() {
        Assert.assertTrue(loginPage.userName.isDisplayed());
        Assert.assertTrue(loginPage.passWord.isDisplayed());

    }

    @When("title of login page is Unity Sign In")
    public void title_of_login_page_is_unity_sign_in() {
        loginPage.getTitle();
    }
    @Then("User login into the application by entering username and password")
    public void user_login_into_the_application_by_entering_username_and_password(DataTable dataTable) {
        List<Map<String,String>> credential = dataTable.asMaps();
        String username="";
        String password="";
        if(Constants.environment.equalsIgnoreCase("INT")){
            username = Constants.INT_UserName;
            password = Constants.INT_password;
        }else if(Constants.environment.equalsIgnoreCase("DEV")){
            username=Constants.Dev_UserName;
            password=Constants.Dev_password;
        }

//        loginPage.fnLoginApplication(credential.get(0).get("username"),credential.get(0).get("password"));
        loginPage.fnLoginApplication(username,password);

    }
    @Then("user is on home page")
    public void user_is_on_home_page() {
        loginPage.homepageLogo.isDisplayed();
    }

    @Then("Title of the home page is {string}")
    public void title_of_the_home_page_is(String titleAfterLogin) {
        Assert.assertEquals(loginPage.TitleOfPageAfterLogin(),titleAfterLogin);
    }



}
