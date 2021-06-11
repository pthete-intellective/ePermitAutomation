package com.intellective.factory;

import com.intellective.utility.Configuration;
import com.intellective.utility.Constants;
import com.intellective.utility.ElementUtil;
import com.intellective.utility.readExcelDataFile;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

public class BaseAction {
    private final WebDriver driver = DriverFactory.getDriver();
    public Configuration conf =Configuration.read();
    public List<String> ListOfAffiliation = new ArrayList<>();
    public ElementUtil elementUtil = new ElementUtil(driver);
    public String caseId;
    public readExcelDataFile data;
    private final Map<String, String> testCase = new ConcurrentHashMap<>();
    private final Map<String, String> testData = new ConcurrentHashMap<>();
    public String scenarioName;


    public Configuration getConf() {
        return conf;
    }

    public WebElement getWebElement(String name,String formName) {
        return getConf().getWebElement(driver, formName, name);
    }
    public List<WebElement> getWebElements(String name, String formName) {
        return getConf().getWebElementes(driver, formName, name);
    }
    public void sleep(int Timer){
        try{
            Thread.sleep(Timer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void sendKeysSpace(WebElement element){
        element.sendKeys(Keys.SPACE);
    }
    public boolean selectComboboxSendKeys(WebElement element,String Value){
        boolean exist;
        elementUtil.setFocus(element,Value);
        String valueXpath = "//*[contains(text(),'"+Value+"')]";
        try{
            WebElement item = getElement(valueXpath);
            if(item.isDisplayed()) {
                item.click();
                elementUtil.sendKeysEnter(element);
            }
            exist = true;
        }catch (Exception e){
            exist = false;
            e.printStackTrace();
        }
        return exist;
    }
    public WebElement getElement(String xpath){
        return driver.findElement(By.xpath(xpath));
    }
    public boolean selectCombobox(WebElement element,String Value){
        boolean exist;
        elementUtil.setFocusClick(element);
//        elementUtil.sendKeysDownClick(element);
        String valueXpath = "//option[text()='"+Value+"']";
        try{
            WebElement item =  getElement(valueXpath);
            item.click();
            elementUtil.sendKeysEnter(element);
            sleep(500);
            exist = true;
        }catch (Exception e){
            exist = false;
        }
        return exist;
    }
    public void setdataSendKeysEnterkeyDown(WebElement element, String value) {
        element.sendKeys(value);
        try {
            sleep(1000);
        }catch (Exception e){

        }
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.ENTER);

    }

    public void selectComboboxcontainsindivisual(WebElement element) {
        sleep(300);
        elementUtil.setFocusClick(element);
       elementUtil.sendKeysDownClickEnter(element);
    }
    public void selectDropDownValue(WebElement element , String value){
        Select select = new Select(element);
        element.click();
        select.selectByValue(value);
    }

    public void fileUpload(WebElement element){
        sleep(2000);
        //configTestRunner.getCommonMethods().executeExtJsClick(driver,element);
        try {
            element.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        String filePath = System.getProperty("user.dir") ;
        StringSelection fileSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileSelection,null);
        // System.out.println("selection" +fileSelection);
        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void readTestData(String scenarioName) {
        data = new readExcelDataFile(Constants.TestDataPath);
//        data = new readExcelDataFile("TestData.xlsx");

        int RCount = data.RowCount("TestCase");
//        int CCount = data.ColCount("TestCase");
        for (int p = 1; p <= RCount; p++) {

            TestCaseDic(p);
            try {
                if (testCase.get("Scenario_Name").equalsIgnoreCase(scenarioName)) {
                    //String login = testCase.get("UserName");
                    testCase.get("Browser");
//                    String password = testCase.get("Password");
                    int TCRowNo = data.RowNo("TestData", testCase.get("Scenario_Name").trim(), "Scenario_Name");
                    TestDataDic(TCRowNo);
                }
            }catch (Exception e){
                System.out.println("No scenario is present in the excelsheet");
            }
        }
    }
    private  void TestCaseDic(int m) {
        int CCnt = data.ColCount("TestCase");
        for (int q = 0; q < CCnt; q++) {
            String Key = data.getExcelDataint("TestCase", 0, q).trim();

            if (Key.equals("")) {
                testCase.put(Key, " ");
            } else {
                String Value = data.getExcelDataint("TestCase", m, q).trim();
                testCase.put(Key, Value);
            }
        }
    }
    private void TestDataDic(int RowNo) {
        int Ccnt = data.ColCount("TestData");
        try {
            for (int j = 0; j < Ccnt; j++) {
                String key = data.getExcelDataint("TestData", 0, j).trim();
                if (key.equals("")) {
                    testData.put(key, null);
                } else {
                    try {
                        String value = data.getExcelDataint("TestData", RowNo, j).trim();
                        testData.put(key, value);
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
/*

This method is used to upload document
argument is webelement & path of the file
 */
    public void uploadFile(WebElement element, String fileName){
        String js = "arguments[0].style.height='auto';arguments[0].style.visibility='visible';";
        ((JavascriptExecutor)driver).executeScript(js,element);
        //String filePath = Constants.FileUploadPath+fileName;
        ClassLoader classLoader = getClass().getClassLoader();
        File file =new File(classLoader.getResource(Constants.FileUploadPath).getFile());
//        browser.sendKeys(file.getAbsolutePath());

        sleep(300);
        //File file = new File(filePath);
        element.sendKeys(file.getAbsolutePath());
        elementUtil.fnWaitForVisibility(elementUtil.getSpanText(Constants.FileUploadPath),Constants.documentUploadWait);
        sleep(2000);
    }

    public void jsClick(WebDriver driver, String attribute, String value){
        JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
        String jsQuery = "document.querySelector(\"["+attribute+"='"+value+"']\").click()";
        jsExecutor.executeScript(jsQuery);
    }

    public void jScriptSetText(WebDriver driver, String value, String id_value){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('"+id_value+"').value='"+value+"'");

    }

}



