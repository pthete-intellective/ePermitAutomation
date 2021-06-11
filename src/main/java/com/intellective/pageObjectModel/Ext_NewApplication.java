package com.intellective.pageObjectModel;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.utility.Constants;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.List;

public class Ext_NewApplication extends BaseAction {
    private final WebDriver driver= DriverFactory.getDriver();
    private final String formName= "ExternalApp";
    public String userName;
    public String userEmailId;


    public void clickonNewAppBtn(){
        elementUtil.fnWaitForVisibility(getWebElement("NewApplicationBtn",formName), Constants.wait);
        elementUtil.waitAndClick(getWebElement("NewApplicationBtn",formName),Constants.wait);
        elementUtil.fnWaitForVisibility(getWebElement("Application",formName),Constants.wait);
    }
    public void captureUserNameEmail(){
        elementUtil.waitAndClick(getWebElement("ProfileIcon",formName),Constants.wait);
        sleep(2000);
        userName = getWebElement("ProfileUsername",formName).getText();
        userEmailId = getWebElement("ProfileMailId",formName).getText();
        String []value = capturenameIfFromUserProfile();
        sleep(2000);
        Assert.assertEquals(userName,value[0]);
        Assert.assertEquals(userEmailId,value[1]);
//        Assert.assertEquals(userEmailId,value[1]);
        driver.navigate().refresh();
    }
    public String[] capturenameIfFromUserProfile(){
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText("User Profile"),Constants.wait);
        elementUtil.waitAndClick(elementUtil.getSpanText("User Profile"),Constants.wait);
        elementUtil.fnWaitForVisibility(getWebElement("UserProfileFName",formName),Constants.wait);
        Assert.assertTrue(getWebElement("UserProfileFName",formName).isDisplayed());
        Assert.assertTrue(getWebElement("UserProfileLname",formName).isDisplayed());
        String Lname = getWebElement("UserProfileLname",formName).getText();
        String Fname = getWebElement("UserProfileFName",formName).getText();
        String userName  = Fname+" "+Lname;
        String email = getWebElement("UserProfileEmail",formName).getText();
        String[] l = {userName,email};
        elementUtil.waitAndClick(getWebElement("UserProfileBackBtn",formName),Constants.wait);
        return l;

    }
    public void validateUserNameandEmailId(){
        elementUtil.fnWaitForVisibility(getWebElement("Creator",formName),Constants.wait);
        Assert.assertEquals(getWebElement("Creator",formName).getAttribute("value"),userName);
        Assert.assertEquals(getWebElement("Email",formName).getAttribute("value"),userEmailId);
    }
    public List<String> fnAddAffiliationInList(){
        sleep(2000);
        List<WebElement> affiliation = getWebElements("AffiliationList","ExternalApp");
        for (int i = 1;i<affiliation.size();i++){
            String name = affiliation.get(i).getText();
            if(!name.isEmpty()){
                ListOfAffiliation.add(affiliation.get(i).getText());
            }
        }
        return ListOfAffiliation;
    }
    public void fnSubscribeAggrement(String company){
        try {
            WebElement subsAggrement = getWebElement("SubsAggrement","ExternalApp");
            elementUtil.fnWaitForVisibility(subsAggrement, Constants.wait);
            if (subsAggrement.isDisplayed())
                selectComboboxSendKeys(subsAggrement, company);
            //Click on Next button
            elementUtil.sendKeysEnter(getWebElement("nextButtonNew","ExternalApp"));
            }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void enterSiteAddress(String street , String city , String zipcode , String state){
//        elementUtil.setFocus(getWebElement("Street",formName),street);
        getWebElement("Street",formName).sendKeys(street);
//        elementUtil.setFocus(getWebElement("City",formName),city);
        setdataSendKeysEnterkeyDown(getWebElement("City",formName),city);
        elementUtil.setFocus(getWebElement("State",formName),state);
        elementUtil.setFocus(getWebElement("Zipcode",formName),zipcode);

    }
   public void clearText(){

        sleep(3000);
       elementUtil.emptyTextData(getWebElement("Street",formName));
        sleep(2000);
       elementUtil.emptyTextData(getWebElement("State",formName));
       sleep(2000);
       elementUtil.emptyTextData(getWebElement("City",formName));
       sleep(3000);
       elementUtil.emptyTextData(getWebElement("Zipcode",formName));
       sleep(2000);
   }
    public void invalidAddressVerification(){
        try {
            sleep(5000);
            elementUtil.moveToElement(getWebElement("IconForAddress", formName));
            elementUtil.fnWaitForVisibility(elementUtil.getSpanText("Address was not verified."),Constants.wait);
            Assert.assertTrue(elementUtil.getSpanText("Address was not verified.").isDisplayed());
            elementUtil.moveToElement(elementUtil.getSpanText("Address was not verified."));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void validAddressVerification(){
        try {
            sleep(5000);
            //elementUtil.moveToElement(getWebElement("IconForAddress", formName));
            elementUtil.fnWaitForVisibility(getWebElement("ValidAddressPopup",formName),Constants.wait);
            Assert.assertTrue(getWebElement("ValidAddressPopup", formName).isDisplayed());
            elementUtil.moveToElement(getWebElement("ValidAddressPopup",formName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void PartIIIQ5(String Value, String lavel, String LavelYesOrNo){
        if(Value.equalsIgnoreCase("Yes")){
//            elementUtil.fnWaitForVisibility(getWebElement("aquiferProtectiontrue",formName),Constants.wait);
            sendKeysSpace(getWebElement("aquiferProtectiontrue",formName));
            if(elementUtil.getSpanText("Level A").isDisplayed()){
                Assert.assertTrue(elementUtil.getSpanText("Level A").isDisplayed());
                if(lavel.equalsIgnoreCase("Lavel A")){
                    sendKeysSpace(getWebElement("aquiferProtectionLevelLevelA",formName));
//                    elementUtil.fnWaitForVisibility(getWebElement("aquiferProtectionLevelAtrue",formName),Constants.wait);
                   // Assert.assertTrue(getWebElement("aquiferProtectionLevelAtrue",formName).isDisplayed());
                    if(LavelYesOrNo.equalsIgnoreCase("Yes")){
                        sendKeysSpace(getWebElement("aquiferProtectionLevelAtrue",formName));
                        sleep(2000);
                        try {
                            elementUtil.fnWaitForVisibility(elementUtil.getSpanContainsText("If yes,  and  your  business  is not already registered with the Aquifer Protection Program, contact the"),30);
                            Assert.assertTrue(elementUtil.getSpanContainsText("If yes,  and  your  business  is not already registered with the Aquifer Protection Program, contact the").isDisplayed());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if(LavelYesOrNo.equalsIgnoreCase("No")){
                        sendKeysSpace(getWebElement("aquiferProtectionLevelAfalse",formName));
                    }
                }else if(lavel.equalsIgnoreCase("Lavel B")){
                    sendKeysSpace(getWebElement("aquiferProtectionLevelLevelB",formName));
                }

            }

        }else
        {
            elementUtil.waitAndClick(getWebElement("aquiferProtectionfalse",formName),Constants.wait);
        }
    }
    public void PartIII_Q7(){
//        elementUtil.waitAndClick(getWebElement("Q7Newbutton",formName),Constants.wait);
        sleep(2000);
        try {
            JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;

            String jsQuery;

            jsQuery = "document.querySelector(\"[name='insert']\").click()";

            jsExecutor.executeScript(jsQuery);
           // elementUtil.setFocusClick(getWebElement("Q7Newbutton", formName));
            sleep(1000);
            elementUtil.fnWaitForVisibility(getWebElement("Q7TableRow", formName), Constants.wait);
            List<WebElement> RowNO = getWebElements("Q7TableRow", formName);
            Assert.assertEquals(RowNO.size(), 1);
        }catch (Exception e){

        }
    }

    public void partIIIQ12(){
        try {
            jsClick(driver,"id","beachesDunes");
            if(!(driver.findElement(By.xpath("//h6[contains(text(),'License Application')]")).getText().contains("Form D"))) {
//            elementUtil.setFocusClick(getWebElement("beachesDunes", formName));
                elementUtil.fnWaitForVisibility(getWebElement("beachesDunes_PermanentImpactHTL", formName), Constants.wait);
                Assert.assertTrue(getWebElement("beachesDunes_PermanentImpactHTL", formName).isDisplayed());
                getWebElement("beachesDunes_PermanentImpactHTL", formName).sendKeys("100");
                Assert.assertTrue(getWebElement("beachesDunes_temporaryImpactHTL", formName).isDisplayed());
                getWebElement("beachesDunes_temporaryImpactHTL", formName).sendKeys("200");
                Assert.assertTrue(getWebElement("beachesDunes-mitigation", formName).isDisplayed());
                getWebElement("beachesDunes-mitigation", formName).sendKeys("300");
                Assert.assertTrue(getWebElement("beachesDunes-describeImpactHTL", formName).isDisplayed());
                getWebElement("beachesDunes-describeImpactHTL", formName).sendKeys("300");
                Assert.assertTrue(getWebElement("beachesDunes-permanentImpactCJL", formName).isDisplayed());
                getWebElement("beachesDunes-permanentImpactCJL", formName).sendKeys("100");
                Assert.assertTrue(getWebElement("beachesDunes-temporaryImpactCJL", formName).isDisplayed());
                getWebElement("beachesDunes-temporaryImpactCJL", formName).sendKeys("150");
                Assert.assertTrue(getWebElement("beachesDunes-migitation", formName).isDisplayed());
                getWebElement("beachesDunes-migitation", formName).sendKeys("150");
                Assert.assertTrue(getWebElement("beachesDunes-describeImpactCJL", formName).isDisplayed());
                getWebElement("beachesDunes-describeImpactCJL", formName).sendKeys("120");
                sleep(2000);
            }else if(driver.findElement(By.xpath("//h6[contains(text(),'License Application')]")).getText().contains("Form D")){
                Assert.assertTrue(getWebElement("beachesDunes_temporaryImpactHTL", formName).isDisplayed());
                getWebElement("beachesDunes_temporaryImpactHTL", formName).sendKeys("200");
//                Assert.assertTrue(getWebElement("beachesDunes-mitigation", formName).isDisplayed());
//                getWebElement("beachesDunes-mitigation", formName).sendKeys("300");
                Assert.assertTrue(getWebElement("beachesDunes-describeImpactHTL", formName).isDisplayed());
                getWebElement("beachesDunes-describeImpactHTL", formName).sendKeys("300");
                Assert.assertTrue(getWebElement("beachesDunes-temporaryImpactCJL", formName).isDisplayed());
                getWebElement("beachesDunes-temporaryImpactCJL", formName).sendKeys("150");
                Assert.assertTrue(getWebElement("beachesDunes-describeImpactCJL", formName).isDisplayed());
                getWebElement("beachesDunes-describeImpactCJL", formName).sendKeys("120");

            }

//            elementUtil.setFocusClick(getWebElement("wildlifecheckbox", formName));
//            getWebElement("wildlifecheckbox",formName).click();
//            elementUtil.fnWaitForVisibility(getWebElement("wildlifeImpacts", formName), Constants.wait);
            jsClick(driver,"id","wildlife");
            Assert.assertTrue(getWebElement("wildlifeImpacts", formName).isDisplayed());
            getWebElement("wildlifeImpacts", formName).sendKeys("200");
            elementUtil.executeExtJsClick(driver, elementUtil.expandPart("part iii"));
        }catch (Exception e){

        }
    }
    public void PartIIIQ2(String zonningoption){
        if(zonningoption.equalsIgnoreCase("Yes")){
            //elementUtil.fnWaitForVisibility(getWebElement("municipalzoningyes",formName),Constants.wait);
            elementUtil.setFocusClick(getWebElement("municipalzoningyes",formName));
        }else if(zonningoption.equalsIgnoreCase("No")){
            elementUtil.setFocusClick(getWebElement("municipalzoningno",formName));
        }


    }
    public void PartIIIQ4(String indianlandoption){
        if(indianlandoption.equalsIgnoreCase("Yes")){
//            elementUtil.setFocusClick(getWebElement("indianLandsyes",formName));
            jsClick(driver,"data-test-id","indianLands-true");
        }else if(indianlandoption.equalsIgnoreCase("No")){
//            elementUtil.setFocusClick(getWebElement("indianLandsno",formName));
            jsClick(driver,"data-test-id","indianLands-no");
        }


    }

    public void PartIIIQ6(String option){
        if(option.equalsIgnoreCase("Yes")){
            sleep(2000);
            try {
                jsClick(driver,"data-test-id","conservationOrPreservation-true");
//                elementUtil.setFocusClick(getWebElement("conservationOrPreservationyes", formName));
                //getWebElement("conservationOrPreservationyes", formName).click();
            }catch (Exception e){

            }
//            WebDriverWait wait = new WebDriverWait(driver, 30);
//            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-test-id='conservationOrPreservation-true']")));
//            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        }else if(option.equalsIgnoreCase("No")){
            elementUtil.setFocusdblClick(getWebElement("conservationOrPreservationno",formName));
        }

    }
    public void PartIIIQ8(String option){
        try {
            sleep(2000);
            if (option.equalsIgnoreCase("Yes")) {
//            elementUtil.fnWaitForVisibility(getWebElement("soilAndGroundwateryes",formName),Constants.wait);
//                elementUtil.setFocusClick(getWebElement("soilAndGroundwateryes", formName));
                jsClick(driver,"data-test-id","soilAndGroundwater-true");
            } else if (option.equalsIgnoreCase("No")) {
//                elementUtil.setFocusClick(getWebElement("soilAndGroundwaterno", formName));
                jsClick(driver,"data-test-id","soilAndGroundwater-false");
            }
        }catch (Exception e){

        }

    }

    public void PartIIIQ9(String option){
        if(option.equalsIgnoreCase("Yes")){
            elementUtil.fnWaitForVisibility(getWebElement("enforcementHistoryyes",formName),Constants.wait);
            elementUtil.waitAndClick(getWebElement("enforcementHistoryyes",formName),Constants.wait);
            elementUtil.fnWaitForVisibility(getWebElement("enforcementHistoryaction",formName),Constants.wait);
            getWebElement("enforcementHistoryaction",formName).sendKeys("Enforcement History Action");
            getWebElement("enforcementHistoryDivisionProgram", formName).sendKeys("enforcementHistoryDivisionProgram");
            getWebElement("enforcementHistoryStaffContact",formName).sendKeys("enforcementHistoryStaffContact");
            getWebElement("enforcementHistoryHistoricalEnforcement",formName).sendKeys("enforcementHistoryHistoricalEnforcement");
        }else if(option.equalsIgnoreCase("No")){
//            elementUtil.setFocusClick(getWebElement("enforcementHistoryno",formName));
            jsClick(driver,"data-test-id","enforcementHistory-false");
        }

    }
    public void PartIII10(String option){
        sleep(1000);
        try {
            if (option.equalsIgnoreCase("Coastal Jurisdiction Line")) {
                sleep(1000);
//                elementUtil.setFocusdblClick(getWebElement("regulatortLimitCJL", formName));
//            getWebElement("regulatortLimitCJL",formName).click();
                jsClick(driver,"data-test-id","regulatoryLimit-CJL");
            } else if (option.equalsIgnoreCase("Mean High Water")) {
                elementUtil.setFocusClick(getWebElement("regulatoryLimitMHW", formName));
            } else if (option.equalsIgnoreCase("Tidal Wetland Boundary")) {
                elementUtil.setFocusClick(getWebElement("regulatoryLimitTWB", formName));
            }
        }catch (Exception e){

        }
    }
    public void PartIII11(){
        getWebElement("tidalElevationcjl",formName).sendKeys("10");
        getWebElement("tidalElevationmhw",formName).sendKeys("20");
        getWebElement("tidalElevationmlw",formName).sendKeys("20");
        getWebElement("tidalElevationhtl",formName).sendKeys("20");
    }
    public void PartIII03(){
        sleep(2000);
//        elementUtil.setFocus(getWebElement("waterbody",formName),"Waterbody");
        getWebElement("waterbody",formName).sendKeys("waterbody");
        sleep(2000);
        getWebElement("waterbody",formName).sendKeys(Keys.ENTER);
//        elementUtil.setFocus(getWebElement("waterbody",formName),"Multivaleselection");
        getWebElement("waterbody",formName).sendKeys("Multivaleselection");
        getWebElement("waterbody",formName).sendKeys(Keys.ENTER);

    }
    public void fnProjectInformation(){
        try{
            elementUtil.fnWaitForVisibility(getWebElement("existStructure",formName),20);

        }catch (Exception e){
           elementUtil. waitAndClick(getWebElement("ProjInfoExpander",formName),Constants.wait);
        }
        try{
            if(elementUtil.fnWaitForVisibility(getWebElement("existStructure",formName),20)){
                //Enter value in existing structure
//                elementUtil.setFocus(getWebElement("existStructure",formName),"Project Info Structure");
                sleep(2000);
                getWebElement("existStructure",formName).sendKeys("Projrct info structure");
                //Enter value in Regulated work & autority seaction a
                sleep(2000);
//                elementUtil.setFocus(getWebElement("regulatedWorkAct_a",formName),"Regulatory work activity");
                getWebElement("regulatedWorkAct_a",formName).sendKeys("Regulatory work activity");
                sleep(1000);
                //Enter value in Regulated work & autority seaction b
//                elementUtil.setFocus(getWebElement("regulatedWorkAct_b",formName),"Regulatory description");
                getWebElement("regulatedWorkAct_b",formName).sendKeys("Regulatory description");
                sleep(1000);
                //Enter value in Regulated work & autority seaction c
//                elementUtil.setFocus(getWebElement("regulatedWorkAct_c",formName),"Regulatory Work and Authority");
                getWebElement("regulatedWorkAct_c",formName).sendKeys("Regulatory Work and Authority");
                //Enter value in Regulated work & autority seaction d
//                elementUtil.setFocus(getWebElement("regulatedWorkAct_d",formName),"RWA Section d");
                getWebElement("regulatedWorkAct_d",formName).sendKeys("RWA Section d");
                sleep(800);
                //Select date
//                elementUtil.setFocus(getWebElement("regulatedWorkAct_date",formName),"09/28/2020");
                sleep(800);
                getWebElement("regulatedWorkAct_date",formName).sendKeys("09/28/2020");
                //Select New structure & activity
//                elementUtil.setFocus(getWebElement("newStructActivity",formName),"Project Structure Activity");
                getWebElement("newStructActivity",formName).sendKeys("Project Structure Activity");
                sleep(2000);
                //check prapose work user as Marine
//                elementUtil.executeExtJsClick(driver,getWebElement("proposalWorkuser_Marine",formName));
                sleep(800);
                //enter Marina or Yatch hub
//                getWebElement("proposalWorkuser_Marine",formName).click();
                jsClick(driver,"name","Marine commercial/industrial use including aquaculture");
                getWebElement("marina_Yachtclub",formName).sendKeys("Marina Club");

                sleep(800);
                //enter Marina sanitations services
//                elementUtil.setFocus(getWebElement("marina_SanitationServices",formName),"Marina sanitation services");
                getWebElement("marina_SanitationServices",formName).sendKeys("Marina sanitation services");
                sleep(800);
                //click on confirmation check box
                elementUtil.setFocusClick(getWebElement("confirmcheckbox",formName));
                sleep(1000);
                //check the applicable services
                elementUtil.executeExtJsClick(driver ,getWebElement("boatRepair",formName));
                sleep(800);
                //Enter requirement costal site plan
                getWebElement("coastalSitePlan",formName).sendKeys("coastalSitePlan");
                sleep(800);
                if(!(driver.findElement(By.xpath("//h6[contains(text(),'License Application')]")).getText().contains("Form D"))) {
                    //Expanded Flood Infrastructure
                    elementUtil.setFocusClick(getWebElement("ExpandedFlood_infrastructuralfacility", formName));
                    sleep(800);
                    //Impact navigation
//                elementUtil.setFocus(getWebElement("Impact_Navigation",formName),"Impact Navigation");
                    getWebElement("Impact_Navigation", formName).sendKeys("Impact Navigation");
                    sleep(800);
//                elementUtil.setFocus(getWebElement("Impact_PublicAccess",formName),"Public Impact Navigation");
                    getWebElement("Impact_PublicAccess", formName).sendKeys("Public Impact Navigation");
                    sleep(800);
                }
                elementUtil.executeExtJsClick(driver,getWebElement("ProjInfoExpander",formName));

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public void fnEnggSupport( ){
        try{
            elementUtil.fnWaitForVisibility(getWebElement("App_EnggStructure",formName),20);
        }catch (Exception e){
            elementUtil.waitAndClick(getWebElement("EnggSupportDoc",formName),Constants.wait);
        }
        try{
        if(elementUtil.fnWaitForVisibility(getWebElement("App_EnggStructure",formName),Constants.wait)){
            //select Yes for Engg Structure
            sleep(1000);
            elementUtil.executeExtJsClick(driver,getWebElement("App_EnggStructure",formName));
            //select FEMA flood zone as YEs
            sleep(1000);
            if(!(driver.findElement(By.xpath("//h6[contains(text(),'License Application')]")).getText().contains("Form D"))) {
                elementUtil.setFocusClick(getWebElement("App_EFema_flood_Yes", formName));
                //Select tye of zone as floodways
                sleep(1000);
                elementUtil.setFocusClick(getWebElement("App_EFemaflood_floodway", formName));
                sleep(1000);
            }else {
                sleep(1000);
                jsClick(driver,"data-test-id","FEMAfloodplain-true");
                sleep(1000);
                jsClick(driver,"data-test-id","FEMAfloodway-true");
            }
            }

        }catch (Exception e){

        }
        elementUtil.executeExtJsClick(driver,getWebElement("EnggSupportDoc",formName));
        sleep(1000);
    }
    public void certificateOfPermission(){
        if((driver.findElement(By.xpath("//h6[contains(text(),'License Application')]")).getText().contains("Form D"))) {
            sleep(1000);
            jsClick(driver,"name","1. Substantial maintenance or repair of existing structures, fill, obstructions or encroachments authorized pursuant to CGS section 22a-33 or 22a-361.");
            elementUtil.executeExtJsClick(driver,getWebElement("CertificateOfPermission",formName));

        }
    }

    public void fnUploadDocumentInDocumentSection(){
        //WebElement element = driver.findElement(By.xpath("(//input[@type='file'])[1]"));
        List<WebElement> element = driver.findElements(By.xpath("//div[contains(@id,'attachment')]//*[local-name() = 'svg'][1]/following::input[@type='file']"));
        for(WebElement e:element) {
            uploadFile(e, "ePermitUpload.pdf");
        }
    }

    public void fillPaymentDetails(){
     elementUtil.fnWaitForVisibility(elementUtil.getH1tag("External Payment"),Constants.documentUploadWait);
     elementUtil.setFocusClick(getWebElement("CreditCard","ExternalApp"));
        elementUtil.setFocusClick(getWebElement("nextButtoninput","ExternalApp"));
     //fill credit card details
        creditCardDetails();
    }

    private void creditCardDetails(){
        elementUtil.fnWaitForVisibility(getWebElement("CreditCardNum","ExternalApp"),Constants.wait);
        getWebElement("CreditCardNum",formName).sendKeys("4111111111111111");
        getWebElement("ConfirmCreditCardNum",formName).sendKeys("4111111111111111");
        getWebElement("SecurityCode",formName).sendKeys("343434");
        selectDropDownValue(getWebElement("ExpMonth",formName),"05");
        getWebElement("ExpYear",formName).sendKeys("2027");
        getWebElement("Name",formName).sendKeys("Jyoti");
        getWebElement("StreetAddress",formName).sendKeys("Elm 24");
        getWebElement("ZipCode",formName).sendKeys("06125");
        elementUtil.waitAndClick(getWebElement("Submitbtn",formName),Constants.wait);
    }

}
