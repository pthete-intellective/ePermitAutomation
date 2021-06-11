package com.intellective.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

    @Test
    @CucumberOptions(
            plugin = {"pretty",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    "timeline:test-output-thread/"
            },
            monochrome = true,
            glue = {"com/intellective/Stepdefinations", "com/intellective/appHooks"},
            features = {"resources\\com\\features\\"},
            dryRun =false
    )

    public class testNGReport extends AbstractTestNGCucumberTests {
        private WebDriver driver;

        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
            return super.scenarios();
        }

    }

