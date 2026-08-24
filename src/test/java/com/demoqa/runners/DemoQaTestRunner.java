package com.demoqa.runners;

import com.automation.ai.HealingAuditLogger;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.demoqa.stepdefinitions",
                "com.automation.hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class DemoQaTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite(alwaysRun = true)
    public void cleanAuditReportBeforeSuite() {
        HealingAuditLogger.clearAndCleanReports();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite(alwaysRun = true)
    public void generateReports() {
        HealingAuditLogger.exportJsonReport();
    }
}
