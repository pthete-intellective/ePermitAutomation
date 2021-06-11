package com.intellective.appHooks;

import com.intellective.factory.BaseAction;
import com.intellective.factory.DriverFactory;
import com.intellective.utility.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks{

    private WebDriver driver;
    private final BaseAction baseAction= new BaseAction();
    Properties prop;
    public String  sc_name;
    public static Log log = LogFactory.getLog(ApplicationHooks.class);

    @Before(order =1)
    public void beforeHook(Scenario scenario){
//        this.scenario = scenario;
        sc_name = scenario.getName();
       baseAction.readTestData(sc_name);
    }
    @Before(order = 0)
    public void getProperty(){
        prop = ConfigReader.readPropertiesFile("config.properties");

    }
    @Before(order = 2)
    public void launch_Browser(){
        String browser = prop.getProperty("browser");
        DriverFactory driverFactory = new DriverFactory();
        driver= driverFactory.startBrowser(browser);

    }

    @After(order=0)
    public void close_browser(){
        driver.quit();
    }
    @After(order=1)
    public void tearDown(Scenario scenario){
        String screenshotname = scenario.getName().replace(" ","-");
        byte []sourcepath=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(sourcepath,"image/png",screenshotname);
        if(scenario.isFailed()){
            log.info(scenario.getName()+" is failed");
        }else
            if(!scenario.isFailed()){
                log.info(scenario.getName()+" is pass");
            }

    }

}

