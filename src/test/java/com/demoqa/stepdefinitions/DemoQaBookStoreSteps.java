package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.DemoQaBookStorePage;
import com.demoqa.pages.DemoQaLoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DemoQaBookStoreSteps {

    private DemoQaBookStorePage bookStorePage;
    private DemoQaLoginPage loginPage;
    private final ObjectMapper mapper = new ObjectMapper();

    // --- BOOK STORE ---
    @Given("I open the DemoQA Book Store page")
    public void openBookStorePage() {
        String url = ConfigReader.get("app.books.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        bookStorePage = new DemoQaBookStorePage();
    }

    @When("I search for books with query {string}")
    public void searchBooks(String query) {
        bookStorePage.searchBook(query);
    }

    @Then("the book search results should contain {string}")
    public void verifyBookResults(String expectedTitle) {
        List<String> titles = bookStorePage.getBookTitles();
        Log.info("Found book titles: " + titles);
        Assert.assertTrue(titles.stream().anyMatch(t -> t.contains(expectedTitle)),
                "Expected book not found: " + expectedTitle);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_BookStore_Search_" + expectedTitle.replace(" ", "_"));
    }

    @When("I click the book title {string}")
    public void clickBookTitle(String title) {
        bookStorePage.clickBookByTitle(title);
    }

    @Then("the book {string} field should be {string}")
    public void verifyBookDetail(String fieldName, String expectedValue) {
        String actual = bookStorePage.getBookDetailValue(fieldName);
        Log.info("Book detail [" + fieldName + "]: " + actual);
        Assert.assertEquals(actual, expectedValue);
    }

    @When("I click Back To Book Store button")
    public void clickBackToStore() {
        bookStorePage.clickBackToBookStore();
    }

    // --- LOGIN ---
    @Given("I open the DemoQA Login page")
    public void openLoginPage() {
        String url = ConfigReader.get("app.login.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        loginPage = new DemoQaLoginPage();
    }

    @When("I attempt login using profile {string} from JSON")
    public void attemptLogin(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> credentials = data.get(profileKey);

        loginPage.login((String) credentials.get("username"), (String) credentials.get("password"));
    }

    @Then("the login error message should display {string}")
    public void verifyLoginError(String expectedMsg) {
        String actual = loginPage.getErrorMessageText();
        Log.info("Login error message: " + actual);
        Assert.assertEquals(actual, expectedMsg);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Login_Error");
    }
}
