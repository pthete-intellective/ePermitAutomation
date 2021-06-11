package com.intellective.Stepdefinations;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.pageObjectModel.CommonsApp;
import com.intellective.pageObjectModel.Ext_NewApplication;
import com.intellective.utility.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class Ext_NewApplicationStepDef extends BaseAction {
    private final Ext_NewApplication ext_newApplication = new Ext_NewApplication();
    private final CommonsApp commonsApp = new CommonsApp();
    private String caseId;
    private WebDriver driver= DriverFactory.getDriver();
    private List<String>ListOfAffiliation;


    @Then("User click on {string} button")
    public void user_click_on_button(String newApplication) {
        ext_newApplication.clickonNewAppBtn();

    }

    @Then("User capture username and email_id from the profile tab")
    public void user_capture_username_and_email_id_from_the_profile_tab() {
        ext_newApplication.captureUserNameEmail();
        System.out.println("User name is " + ext_newApplication.userName);
        System.out.println("User name is " + ext_newApplication.userEmailId);
    }

    @Then("User check Creator and Email-id value is reflected from the users profile")
    public void user_check_creator_and_email_id_value_is_reflected_from_the_users_profile() {
        ext_newApplication.validateUserNameandEmailId();
    }

    @Then("User Check Next button is disabled")
    public void user_check_next_button_is_disabled() {
        Assert.assertTrue(getWebElement("NextBtndisabled", "ExternalApp").isDisplayed());

    }

    @Then("USer validate that correct list of applicationType is displayed")
    public void u_ser_validate_that_correct_list_of_applicationType_is_displayed(DataTable affliationList) {
        elementUtil.waitAndClick(getWebElement("ApplicationSearchIcon", "ExternalApp"), Constants.wait);
        List<WebElement> element = getWebElements("searchTable", "ExternalApp");
        if (element.size() > 1) {
//        elementUtil.fnGetNoOfRecordPresentnDashboard("Application Name");
            List<Map<String, String>> app_Name = affliationList.asMaps();
            for (Map<String, String> e : app_Name) {
                // getWebElement("Application","ExternalApp").sendKeys(e.get("AffiliationList")+ Keys.ENTER);
                try {
                    Assert.assertTrue(elementUtil.getSpanText(e.get("AffiliationList")).isDisplayed());
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }
        elementUtil.waitAndClick(getWebElement("SearchIconClose", "ExternalApp"), Constants.wait);


    }

    @Then("USer check that Application is marked as mandatory")
    public void u_ser_check_that_application_is_marked_as_mandatory() {
        Assert.assertTrue(getWebElement("NewAppMAndatory", "ExternalApp").isDisplayed());
    }

    @Then("Click on Create button and go through Common Affiliations wizard")
    public void click_on_create_button_and_go_through_common_affiliations_wizard() {
        elementUtil.waitAndClick(elementUtil.getSpanText("Save"), Constants.wait);
        sleep(5000);
        elementUtil.waitAndClick(elementUtil.getSpanText("Create"), Constants.ProcessingWait);
        elementUtil.fnWaitForVisibility(elementUtil.getButtonText("Back"), Constants.ProcessingWait);
    }

    @Then("user select application as {string}")
    public void user_select_application_as(String formname) {
       // elementUtil.setFocusEnter(getWebElement("Application", "ExternalApp"), formname);
//        jScriptSetText(driver,formname,"EP_FormID" );
//        getWebElement("Application", "ExternalApp").sendKeys(Keys.ENTER);
          elementUtil.fnWaitForVisibility(getWebElement("Application","ExternalApp"),Constants.wait);
          getWebElement("Application", "ExternalApp").click();
          elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//li[text()='"+formname+"']")),Constants.wait);
          driver.findElement(By.xpath("//li[text()='"+formname+"']")).click();
    }
    @Then("User Capture Case Id from the URL Of the application")
    public void user_capture_case_id_from_the_url_of_the_application() {
        String[] url = DriverFactory.getDriver().getCurrentUrl().split("/");
        caseId = url[url.length-1];
    }


    @Then("User check the affiliation list for the selected application and check the {string} and {string} affiliation is mandatory")
    public void user_check_the_affiliation_list_for_the_selected_application_and_check_the_and_affiliation_is_mandatory(String applicant, String BillingContact) {
        getWebElement("description","ExternalApp").click();
        sleep(4000);
        DriverFactory.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        elementUtil.fnWaitForVisibility(elementUtil.getPText(applicant), Constants.ProcessingWait);
        elementUtil.moveToElement(elementUtil.getPText(applicant));
        sleep(300);
        elementUtil.fnWaitForVisibility(elementUtil.getText("Required"), Constants.wait);
        Assert.assertTrue(elementUtil.getText("Required").isDisplayed());
        elementUtil.fnWaitForVisibility(elementUtil.getText("Required"), Constants.wait);
        elementUtil.moveToElement(elementUtil.getPText(BillingContact));
        Assert.assertTrue(elementUtil.getText("Required").isDisplayed());
        ListOfAffiliation= ext_newApplication.fnAddAffiliationInList();
        setListOfAffiliation(ListOfAffiliation);


    }

    @Then("Click on {string} button on commons io field.")
    public void click_on_button_on_commons_io_field(String nextbtn) {
        // Write code here that turns the phrase above into concrete actions
        elementUtil.waitAndClick(elementUtil.getButtonText(nextbtn), Constants.wait);
        elementUtil.fnWaitForVisibility(getWebElement("SubsAggrement", "ExternalApp"), Constants.wait);
    }

    @Then("User select company_entity_indivisual as {string} and click on next button")
    public void user_select_company_entity_indivisual_as_and_click_on_next_button(String company) {
        ext_newApplication.fnSubscribeAggrement(company);
    }

    @Then("User select or add new address")
    public void user_select_or_add_new_address() {
        commonsApp.fnSelectAddress();
    }

    @Then("User Select or add new phone number")
    public void user_select_or_add_new_phone_number() {
        commonsApp.fnAddNewPhoneNo();
    }

    @Then("user select or add new contact")
    public void user_select_or_add_new_contact() {
        commonsApp.fnselectExistingContactPerson();
    }
    @Then("Add the different affiliation in the application")
    public void add_the_different_affiliation_in_the_application(){
        commonsApp.fnSelectaffiliaction("Yes",getListOfAffiliation());
    }
    @Then("User open the created ePermiting case")
    public void user_open_the_created_ePermiting_case(){
        List<WebElement> elements = getWebElements("PageNo","ExternalApp");
        WebElement element;
        element = elements.get(elements.size()-2);
        element.click();
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText(caseId),Constants.wait);
        Assert.assertTrue(elementUtil.getSpanText(caseId).isDisplayed());
        elementUtil.waitAndClick(elementUtil.selectdoticontoOpencase(caseId),Constants.wait);
        getWebElement("Properties","ExternalApp").click();
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText("Dhage Jyoti"),Constants.wait);
    }
    @Then("After click on next button application navigated to Application tab")
    public void after_click_on_next_button_application_navigated_to_application_tab(){
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText("Delete Draft"),Constants.wait);
        Assert.assertTrue(elementUtil.getSpanText("Delete Draft").isDisplayed());
    }

    @Then("USer validate that Affiliation control presents the same data that was entered in the wizard and Next button is enabled")
    public void user_validate_that_Affiliation_control_presents_same_data_that_was_entered_in_the_wizard_and_Next_button_is_enabled(){
        sleep(3000);
        DriverFactory.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
//        Assert.assertTrue((elementUtil.getSpanText("Jyoti Dhage").isDisplayed()));
        Assert.assertTrue(elementUtil.getSpanText("Next").isEnabled());
    }
    @Then("User click on Next button and validate correct data is populated on next screen")
    public void user_click_on_next_button_and_validate_correct_data_is_populated_on_next_screen(){
        elementUtil.waitAndClick(elementUtil.getSpanText("Next"),Constants.wait);
        elementUtil.fnWaitForVisibility(getWebElement("PartII","ExternalApp"),Constants.wait);

    }
    @Then("User validate that site address validation works for valid address")
    public void user_validate_that_site_address_validation_works_for_valid_address(DataTable address){
        try{
            elementUtil.fnWaitForVisibility(getWebElement("Part1Expander","ExternalApp"),Constants.wait);
            elementUtil.waitAndClick(getWebElement("Part1Expander","ExternalApp"),Constants.wait);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //elementUtil.fnWaitForVisibility(elementUtil.getSpanContainsText("PUBLIC NOTICE OF APPLICATION"),20);
            elementUtil.waitAndClick(getWebElement("NotificationExpander","ExternalApp"),Constants.wait);

        }catch (Exception e){
          e.printStackTrace();
        }
        try{
            elementUtil.fnWaitForVisibility(getWebElement("disabledAddverificationpopup","ExternalApp"),Constants.wait);
        }catch (Exception e){
            elementUtil.waitAndClick(elementUtil.expandPart("part iii"),Constants.wait);
        }
        try{
            elementUtil.fnWaitForVisibility(getWebElement("disabledAddverificationpopup","ExternalApp"),Constants.wait);
            Assert.assertTrue(getWebElement("IconForAddress","ExternalApp").isDisplayed());
        }catch (Exception e){
            elementUtil.fnWaitForVisibility(getWebElement("enableAddressVarificationPopup","ExternalApp"),Constants.wait);
            Assert.assertFalse(getWebElement("IconForAddress", "ExternalApp").isDisplayed());
            elementUtil.waitAndClick(getWebElement("enableAddressVarificationPopup","ExternalApp"),Constants.wait);
        }
        List<Map<String, String>> Address = address.asMaps();
        String street = Address.get(0).get("Street");
        String city = Address.get(0).get("City");
        String zipcode = Address.get(0).get("ZipCode");
        String state = Address.get(0).get("State");
        ext_newApplication.enterSiteAddress(street,city,zipcode,state);
        ext_newApplication.invalidAddressVerification();
        ext_newApplication.clearText();
         street = Address.get(1).get("Street");
         city = Address.get(1).get("City");
         zipcode = Address.get(1).get("ZipCode");
         state = Address.get(1).get("State");
        ext_newApplication.enterSiteAddress(street,city,zipcode,state);
        ext_newApplication.validAddressVerification();

    }
    @Then("User Validate that logic for hidden fields works in Part III_Q5 after selecting Yes-LavelA-Yes")
    public void user_validate_that_logic_for_hidden_fields_works_in_part_iii_q5_after_selecting_yes_level_a_yes() {
        ext_newApplication.PartIIIQ5("Yes","Lavel A","No");
        sleep(1000);
    }

    @Then("User validate that new rows can be added to the table in Part III_Q7")
    public void user_validate_that_new_rows_can_be_added_to_the_table_in_part_III_Q7(){
        ext_newApplication.PartIII_Q7();
    }
    @Then("User validate that logic works in Part III_Q12 when user checks Beaches or Dunes and when check Wildlife")
    public void user_validate_that_logic_works_in_part_III_12_when_user_checks_Beaches_Or_Dunes_and_when_check_wildlife(){
        ext_newApplication.partIIIQ12();
    }
    @Then("User validate that Next button becomes available only after all required fields are populated")
    public void User_validate_that_next_button_becomes_available_only_after_all_required_fields_are_populated(){
           elementUtil.waitAndClick(elementUtil.getSpanText("Next"),Constants.wait);
           if(elementUtil.fnWaitForVisibility(elementUtil.getHtag("Part VI: Supporting Documents"),Constants.wait)){
               Assert.assertTrue(elementUtil.getHtag("Part VI: Supporting Documents").isDisplayed());
           }
    }
    @Then("User select Muncipal Zonning as {string} Q2 of PartIII")
    public void user_select_muncipal_zonning_as_Q2_of_partIII(String munciplezonning){
        ext_newApplication.PartIIIQ2(munciplezonning);
    }

    @Then("User select Indian Lands option as {string} Q4 of PartIII")
    public void user_select_indian_lands_option_as_Q4_of_partIII(String indianLandOption){
        ext_newApplication.PartIIIQ4(indianLandOption);
    }
    @Then("User select Conservation or Preservation restration option as {string} Q6 of PartIII")
    public void user_select_conservation_or_preservation_restration_option_as_Q6_of_partIII(String option){
        ext_newApplication.PartIIIQ6(option);
    }
    @Then("User select soil and_or groundwater remediation option as {string} Q8 of PartIII")
    public void user_select_soil_and_or_groundwater_remediation_option_as_Q8_of_partIII(String option){
        ext_newApplication.PartIIIQ8(option);
    }
    @Then("User select Enforcement History option as {string} Q9 of PartIII")
    public void user_select_enforcement_history_option_as_Q9_of_partIII(String option){
        ext_newApplication.PartIIIQ9(option);
    }
    @Then("User select REGULATORY LIMIT option as {string} Q10 of PartIII")
    public void user_select_regulatory_limit_option_as_Q10_partIII(String option){
        ext_newApplication.PartIII10(option);
    }
    @Then("User enter value in Tidal Elevations in Q11 of PartIII")
    public void user_enter_value_in_tidal_elevation_in_Q11_of_partIII(){
        ext_newApplication.PartIII11();
    }

    @Then("User select multiple value in waterbody Q3 of PartIII")
    public void user_select_multiple_value_in_waterbody_Q3_of_partIII(){
        ext_newApplication.PartIII03();
    }
    @Then("User fill the information in Part IV Project Information tab")
    public void user_fill_the_information_in_part_IV_project_information_tab(){
        ext_newApplication.fnProjectInformation();
    }
    @Then("User fill the information in Part V: Engineering Support Documentation and Certification tab")
    public void user_fill_the_information_in_part_v_Enfineering_support_document_and_certification_tab(){
        ext_newApplication.fnEnggSupport();
    }
    @Then("User fill the information in Part VI: Certificate of Permission Eligibility")
    public void user_fill_the_information_in_part_vi_Certificate_of_Permission_Eligibility(){
        ext_newApplication.certificateOfPermission();
    }

    @Then("After click on Next button and attach documents for required attachments")
    public void after_click_next_button_and_attach_documents_for_required_attachments(){
        ext_newApplication.fnUploadDocumentInDocumentSection();
    }

    @Then("User click on Next button and validate that Certifier Agreement and Certifier signature is displayed")
    public void user_click_on_next_button_and_validate_that_certifier_aggrement_and_certifier_signature_is_displayed(){
        elementUtil.waitAndClick(elementUtil.getSpanText("Next"),Constants.wait);
        elementUtil.fnWaitForVisibility(elementUtil.getHtag("CERTIFIER AGREEMENT"),Constants.wait);
        Assert.assertTrue(elementUtil.getHtag("CERTIFIER AGREEMENT").isDisplayed());
        Assert.assertTrue(elementUtil.getHtag("CERTIFIER SIGNATURE").isDisplayed());
        elementUtil.executeExtJsClick(DriverFactory.getDriver(),getWebElement("ConfirmationBtn","ExternalApp"));
        elementUtil.fnWaitForVisibility(getWebElement("CompleteBtn","ExternalApp"),Constants.wait);
        elementUtil.setFocusClick(getWebElement("CompleteBtn","ExternalApp"));
       // elementUtil.fnWaitForVisibility(getWebElement("complitionMsg","ExternalApp"),200);
        //System.out.println(getWebElement("complitionMsg","ExternalApp").getText());
        sleep(8000);


    }
    @Then("User Fill out checking data for payment and finish the payment")
    public void user_fill_out_checking_data_for_payment_and_finish_the_payment(){
        ext_newApplication.fillPaymentDetails();
    }

    @Then("Validate that this application is now on My Application tab in Payment Pending status. Validate that application # and submission data are populated")
    public void validate_create_application_is_under_my_application_tab(){
        List<WebElement> elements = getWebElements("MyApplicationPageNo","ExternalApp");
        WebElement element = elements.get(elements.size()-2);
        element.click();
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText(caseId),Constants.wait);
        Assert.assertTrue(elementUtil.getSpanText(caseId).isDisplayed());
        Assert.assertTrue(elementUtil.checkStatusOfCase(caseId,"Pending Payment").isDisplayed());
//        elementUtil.waitAndClick(elementUtil.checkStatusOfCase(caseId,"Pending Payment"),Constants.wait);
    }

    public List<String> getListOfAffiliation() {
        return ListOfAffiliation;
    }

    public void setListOfAffiliation(List<String> listOfAffiliation) {
        ListOfAffiliation = listOfAffiliation;
    }
}