package com.intellective.pageObjectModel;

import com.intellective.factory.BaseAction;
import com.intellective.utility.Constants;
import com.intellective.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ExternalAppHomePage extends BaseAction {
    private final WebDriver driver;
    private final By ThreeDot1stRow = By.xpath("(//table//tbody//tr[1]//td/following::button[contains(@data-test-id,'actions')])");
    private final By properties_menu = By.xpath("//li[text()='Properties']");
    private final By editBtn = By.xpath("//span[text()='Edit']");
    private final By documentTab = By.xpath("//div[contains(@data-rbd-droppable-id,'Case_Docs_Search_P8')]//h6[contains(text(),'Document')]");

    public ExternalAppHomePage(WebDriver driver , ElementUtil elementUtil){
        this.driver = driver;
        this.elementUtil=elementUtil;
    }

    public WebElement checkTabOpened(String openedtabname){

        return driver.findElement(By.xpath("(//h6[contains(text(),'"+openedtabname+"')])[1]"));
    }
    public void clickOnMyAppTab(String openedtabname){
        elementUtil.waitAndClick(getTab(openedtabname),Constants.wait);
        if(openedtabname.equalsIgnoreCase("My Applications"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[contains(@aria-labelledby,'Case_EP_ePermit')]//h6[contains(text(),'My Applications')]")),Constants.wait);
        else if(openedtabname.equalsIgnoreCase("My Permits"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[@aria-labelledby='ST_Case_EP_ePermit_Permits']//h6")),Constants.wait);
        else if(openedtabname.equalsIgnoreCase("My Drafts"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[@aria-labelledby='ST_Case_EP_ePermit_Drafts']//h6")),Constants.wait);

    }
    public WebElement getTab(String tabName){
        return driver.findElement(By.xpath("//button[@aria-label='"+tabName+"']"));
    }

    public void clickOnTab(String tabName){
        elementUtil.waitAndClick(getTab(tabName), Constants.wait);
        if(tabName.equalsIgnoreCase("Application Types"))
            tabName = "Application Search";
        if(tabName.equalsIgnoreCase("My Applications"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[contains(@aria-labelledby,'Case_EP_ePermit')]//h6[contains(text(),'My Applications')]")),Constants.wait);
        else if(tabName.equalsIgnoreCase("My Permits"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[@aria-labelledby='ST_Case_EP_ePermit_Permits']//h6")),Constants.wait);
        else if(tabName.equalsIgnoreCase("Applications for Review"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[contains(@aria-labelledby,'EP_ePermit_Review')]//h6[contains(text(),'Applications for Review')]")),Constants.wait);
        else if(tabName.equalsIgnoreCase("Application Search"))
            elementUtil.fnWaitForVisibility(driver.findElement(By.xpath("//div[contains(@aria-labelledby,'EP_Application')]//h6[contains(text(),'Application Search')]")),Constants.wait);
        else if(tabName.equalsIgnoreCase("Applications"))
            getWebElement("ApplicationTabVerification","ExternalApp").isDisplayed();
        else if(tabName.equalsIgnoreCase("Attachments"))
            getWebElement("AttachmentTabVerfication","ExternalApp").isDisplayed();
        else
            elementUtil.fnWaitForVisibility(checkTabOpened(tabName),Constants.wait);
    }

    public int checkRenderSuccessful(String colName){
        return elementUtil.fnGetNoOfRecordPresentnDashboard(colName);
    }
    public synchronized void ContextClickOn1stRow() {

//        elementUtil.fnWaitForVisibility(driver.findElement(ThreeDot1stRow),Constants.wait);
        sleep(1000);
        List<WebElement> elements = driver.findElements(ThreeDot1stRow);
        int start=0;
        if(elements.size()>10){
            start=11;
        }else{
            start=1;
        }
        for(int i=start;i<elements.size();i++) {
            elementUtil.waitAndClick(elements.get(i), Constants.wait);
            elementUtil.waitAndClick(driver.findElement(properties_menu), Constants.wait);
            break;
        }
        //elementUtil.fnWaitForVisibility(elementUtil.getSpanText("Common Properties"),Constants.wait);

    }
    public WebElement existingtabName(String tabName){
        return elementUtil.getSpanText(tabName);
    }
    public void clickOnExistingCaseTab(String tabName){
        elementUtil.waitAndClick(elementUtil.getSpanText(tabName),Constants.wait);
        if(tabName.equalsIgnoreCase("Common Properties"))
            elementUtil.fnWaitForVisibility(getWebElement("Email","ExternalApp"),Constants.wait);
        else if(tabName.equalsIgnoreCase("Documents"))
//            elementUtil.fnWaitForVisibility(driver.findElement(documentTab),Constants.wait);
            elementUtil.fnWaitForVisibility(getWebElement("ApplicationFolder","ExternalApp"),Constants.wait);
        else if(tabName.equalsIgnoreCase("History/Comments"))
            elementUtil.fnWaitForVisibility(elementUtil.getSpanText("Comment"),Constants.wait);
        else if(tabName.equalsIgnoreCase("Attachments"))
            System.out.println("Unable to open attachments tab");
        else if(tabName.equalsIgnoreCase("Application"))
            System.out.println("Unable to open applications tab");

    }
    public void setClickActions(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.build().perform();
    }

}
