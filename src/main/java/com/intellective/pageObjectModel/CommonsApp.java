package com.intellective.pageObjectModel;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.utility.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonsApp extends BaseAction {
    private final String formName= "ExternalAppHCL";
    public WebDriver driver= DriverFactory.getDriver();

    public void fnSelectAddress(){
        try {
            WebElement addressRadioButton = getWebElement("addressRadioButton",formName);
            elementUtil.fnWaitForVisibility(addressRadioButton, Constants.wait);
            if (addressRadioButton.isDisplayed()) {
                elementUtil.waitAndClick(addressRadioButton, 30);
            }
        }catch (Exception e){
            WebElement addnewButton = getWebElement("addnewButton",formName);
            if(addnewButton.isDisplayed()){
                elementUtil.sendKeysEnter(addnewButton);
                elementUtil.fnWaitForVisibility(getWebElement("addressline1",formName),Constants.wait);
                Assert.assertTrue(getWebElement("addressline1",formName).isDisplayed());
                elementUtil.setFocus(getWebElement("addressline1",formName),"AddressLine1");
                //enter city
                elementUtil.setFocus(getWebElement("addressline1City",formName),"AddressLine2");
                //enter postal code
                elementUtil.setFocus(getWebElement("addressline1ZipCode",formName),"07654");
                //enter postal zip 4code
                elementUtil.setFocus(getWebElement("addressline1Zipext",formName),"234");
                //Click on Next button
                Assert.assertTrue(getWebElement("nextButtonNew","ExternalApp").isEnabled());
                elementUtil.setFocusdblClick(getWebElement("nextButtonNew",formName));
                //click on user enter address field
                try{
                    elementUtil.waitAndClick(getWebElement("useSuggestedAddress",formName),30);
                }catch (Exception ie){
                    elementUtil.waitAndClick(getWebElement("PublicComment","userRefinerAddress"),30);

                }

            }
        }
        //Click on Next button
        Assert.assertTrue(getWebElement("nextButtonNew","ExternalApp").isEnabled());
        try {
            elementUtil.waitAndClick(getWebElement("nextButtonNew","ExternalApp"),10);
        }catch (Exception e){
            elementUtil.waitAndClick(getWebElement("nextButtonnew","ExternalApp"), Constants.wait);
        }
    }
    public void fnAddNewPhoneNo(){
        elementUtil.fnWaitForVisibility(getWebElement("phoneNumberHeader",formName),Constants.wait);
        WebElement addnewButton = getWebElement("addnewButton",formName);
        if(addnewButton.isDisplayed()){
            elementUtil.waitAndClick(addnewButton,30);
            elementUtil.fnWaitForVisibility(getWebElement("phoneNumber",formName),Constants.wait);
            Assert.assertTrue(getWebElement("phoneNumber",formName).isDisplayed());
            getWebElement("phoneNumber",formName).sendKeys("95523487887");
            //enter extension
            getWebElement("phoneExtension",formName).sendKeys("23233232");
            //Click on Next button
            Assert.assertTrue(getWebElement("nextButtonNew","ExternalApp").isEnabled());
//            configTestRunner.getCommonMethods().setFocusdblClick(getWebElement("nextButtonNew"));
            elementUtil.waitAndClick(getWebElement("nextButtonNew","ExternalApp"),Constants.wait);

        }
    }
    public void fnselectExistingContactPerson(){
//        try{
//            elementUtil.fnWaitForVisibility(getWebElement("selectPerson",formName),10);
//            WebElement contactPerson = getWebElement("selectPerson",formName);
//            if (contactPerson.isDisplayed()) {
//                elementUtil.waitAndClick(contactPerson, Constants.wait);
//            }
//        }catch (Exception e){
            WebElement addnewButton = getWebElement("addnewButton",formName);
            elementUtil.fnWaitForVisibility(addnewButton,Constants.wait);
            if(addnewButton.isDisplayed()){
                elementUtil.sendKeysEnter(addnewButton);
               // elementUtil.fnWaitForVisibility(getWebElement("name",formName),Constants.wait);
//                Assert.assertTrue(getWebElement("name",formName).isDisplayed());
//                elementUtil.setFocus(getWebElement("name",formName),"JYoti");
                WebElement phoneNum = getWebElement("phoneNumberCon",formName);
                elementUtil.setFocus(phoneNum,"95523487881");
                sleep(300);
                //enter email id
               // elementUtil.setFocus(getWebElement("email",formName),"jyoti.dhage@ct.gov");


           // }
        }
        //Click on Next button
        Assert.assertTrue(getWebElement("nextButtonNew","ExternalApp").isEnabled());
        elementUtil.waitAndClick(getWebElement("nextButtonNew","ExternalApp"),20);
    }
    public void fnSelectaffiliaction(String contact, List<String> ListOfAffiliation)  {
       // for(String name:ListOfAffiliation){
        WebElement billingContact=null;
        boolean isDisplay=false;
        do {
            sleep(3000);
            billingContact = getWebElement("billingContact", formName);
            elementUtil.fnWaitForVisibility(billingContact, Constants.wait);
//            if (billingContact.isDisplayed() && (!name.equalsIgnoreCase("Property Owner"))) {
            if(getWebElement("descriptionOfAffiliation","ExternalAppHCL").getText().contains("add another")) {
                selectCombobox(billingContact, "No");
            }else{
                if (billingContact.isDisplayed()) {
                    selectCombobox(billingContact, contact);
                    try {
//                    fnSameCompanySelect("Applicant - Dhage Jyoti");
                        fnSameCompanySelect();
//                    sleep(1000);
                    } catch (Exception e) {
                        selectCombobox(billingContact, "No");
//                    sleep(2000);
                    }
                }
            }
            try {
                driver.findElement(By.xpath("//select[@id='IsAddAnother']")).isDisplayed();
                isDisplay=true;
            }catch (Exception e){
                isDisplay=false;
            }
        }while (isDisplay);

        elementUtil.sendKeysEnter(getWebElement("nextButtonNewtext","ExternalApp"));
    }
    public void fnSameCompanySelect(){
        WebElement company =getWebElement("SameAffiliationList",formName);
        if(company.isDisplayed()){
            selectComboboxcontainsindivisual(company);
        }
        elementUtil.waitAndClick(getWebElement("nextButtonNew","ExternalApp"),Constants.wait);
        elementUtil.waitAndClick(getWebElement("nextButtonNew","ExternalApp"),Constants.wait);
    }

}
