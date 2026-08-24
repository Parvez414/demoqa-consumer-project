package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverManager;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DemoQaElementsSteps {

    private DemoQaTextBoxPage textBoxPage;
    private DemoQaCheckBoxPage checkBoxPage;
    private DemoQaRadioButtonPage radioButtonPage;
    private DemoQaButtonsPage buttonsPage;
    private DemoQaWebTablesPage webTablesPage;
    private DemoQaLinksPage linksPage;
    private DemoQaBrokenLinksImagesPage brokenPage;
    private DemoQaUploadDownloadPage uploadDownloadPage;
    private DemoQaDynamicPropertiesPage dynamicPropertiesPage;
    private final ObjectMapper mapper = new ObjectMapper();

    // ==========================================
    // --- TEXT BOX ---
    // ==========================================
    @Given("I open the DemoQA Text Box page")
    public void openTextBoxPage() {
        String url = ConfigReader.get("app.textbox.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        textBoxPage = new DemoQaTextBoxPage();
    }

    @When("I submit the text box form using profile {string} from JSON")
    public void submitTextBoxWithProfile(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> userProfile = data.get(profileKey);

        textBoxPage.fillForm(
                (String) userProfile.get("fullName"),
                (String) userProfile.get("email"),
                (String) userProfile.get("currentAddress"),
                (String) userProfile.get("permanentAddress")
        );
        textBoxPage.submitForm();
    }

    @Then("the output card should contain {string}")
    public void verifyOutputCard(String expectedText) {
        String output = textBoxPage.getOutputText();
        Log.info("Output card details: \n" + output);
        Assert.assertTrue(output.contains(expectedText), "Output missing expected text: " + expectedText);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_TextBox_Output");
    }

    // ==========================================
    // --- CHECK BOX ---
    // ==========================================
    @Given("I open the DemoQA Check Box page")
    public void openCheckBoxPage() {
        String url = ConfigReader.get("app.checkbox.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        checkBoxPage = new DemoQaCheckBoxPage();
    }

    @When("I expand all checkbox tree nodes")
    public void expandAllNodes() {
        checkBoxPage.expandAllNodes();
    }

    @When("I collapse all checkbox tree nodes")
    public void collapseAllNodes() {
        checkBoxPage.collapseAllNodes();
    }

    @When("I toggle the checkbox node {string}")
    public void toggleCheckboxNode(String nodeName) {
        checkBoxPage.toggleNode(nodeName);
    }

    @Then("the checkbox result display should contain {string}")
    public void verifyCheckboxResult(String expectedKeyword) {
        String res = checkBoxPage.getResultText();
        Log.info("Checkbox result text: " + res);
        Assert.assertTrue(res.toLowerCase().contains(expectedKeyword.toLowerCase()),
                "Checkbox result missing keyword: " + expectedKeyword);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Checkbox_Result");
    }

    // ==========================================
    // --- RADIO BUTTON ---
    // ==========================================
    @Given("I open the DemoQA Radio Button page")
    public void openRadioButtonPage() {
        String url = ConfigReader.get("app.radio.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        radioButtonPage = new DemoQaRadioButtonPage();
    }

    @When("I select the {string} radio button")
    public void selectRadioButton(String radioType) {
        if ("Yes".equalsIgnoreCase(radioType)) {
            radioButtonPage.selectYesRadio();
        } else if ("Impressive".equalsIgnoreCase(radioType)) {
            radioButtonPage.selectImpressiveRadio();
        }
    }

    @Then("the radio selection result text should display {string}")
    public void verifyRadioResult(String expectedResult) {
        String actual = radioButtonPage.getSelectedResultText();
        Log.info("Verified radio result: " + actual);
        Assert.assertEquals(actual, expectedResult);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Radio_Selected_" + expectedResult);
    }

    // ==========================================
    // --- BUTTONS ---
    // ==========================================
    @Given("I open the DemoQA Buttons page")
    public void openButtonsPage() {
        String url = ConfigReader.get("app.buttons.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        buttonsPage = new DemoQaButtonsPage();
    }

    @When("I perform a double click on the Double Click button")
    public void performDoubleClick() {
        buttonsPage.performDoubleClick();
    }

    @Then("the double click message should display {string}")
    public void verifyDoubleClickMsg(String expected) {
        String actual = buttonsPage.getDoubleClickMessage();
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_DoubleClick_Success");
    }

    @When("I perform a right click on the Right Click button")
    public void performRightClick() {
        buttonsPage.performRightClick();
    }

    @Then("the right click message should display {string}")
    public void verifyRightClickMsg(String expected) {
        String actual = buttonsPage.getRightClickMessage();
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_RightClick_Success");
    }

    @When("I perform a standard click on the Dynamic Click button")
    public void performDynamicClick() {
        buttonsPage.performDynamicClick();
    }

    @Then("the dynamic click message should display {string}")
    public void verifyDynamicClickMsg(String expected) {
        String actual = buttonsPage.getDynamicClickMessage();
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_DynamicClick_Success");
    }

    // ==========================================
    // --- WEB TABLES ---
    // ==========================================
    @Given("I open the DemoQA Web Tables page")
    public void openWebTablesPage() {
        String url = ConfigReader.get("app.webtables.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        webTablesPage = new DemoQaWebTablesPage();
    }

    @When("I search the table for {string}")
    public void searchWebTable(String query) {
        webTablesPage.searchTable(query);
    }

    @Then("the table rows should contain {string}")
    public void verifyTableRowContains(String expectedText) {
        List<String> rows = webTablesPage.getTableRowsText();
        boolean found = rows.stream().anyMatch(r -> r.contains(expectedText));
        Log.info("Web table rows searched: " + rows);
        Assert.assertTrue(found, "Expected text [" + expectedText + "] not found in table rows.");
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_WebTable_Search_" + expectedText);
    }

    @When("I add a new record using profile {string} from JSON")
    public void addNewRecord(String profileKey) throws Exception {
        Map<String, Map<String, Object>> data = mapper.readValue(
                new File("src/test/resources/data/user_form_data.json"),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
        Map<String, Object> employee = data.get(profileKey);

        webTablesPage.clickAddNewRecord();
        webTablesPage.fillRegistrationForm(
                (String) employee.get("firstName"),
                (String) employee.get("lastName"),
                (String) employee.get("email"),
                (String) employee.get("age"),
                (String) employee.get("salary"),
                (String) employee.get("department")
        );
    }

    @When("I edit the record for {string} with salary {string} and department {string}")
    public void editRecord(String firstName, String salary, String dept) {
        webTablesPage.editRecord(firstName, salary, dept);
    }

    @When("I delete the record for {string}")
    public void deleteRecord(String firstName) {
        webTablesPage.deleteRecord(firstName);
    }

    @When("I select rows per page as {string}")
    public void selectRowsPerPage(String rowsCount) {
        webTablesPage.selectRowsPerPage(rowsCount);
    }

    // ==========================================
    // --- LINKS ---
    // ==========================================
    @Given("I open the DemoQA Links page")
    public void openLinksPage() {
        String url = ConfigReader.get("app.links.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        linksPage = new DemoQaLinksPage();
    }

    @When("I click the Simple link and switch to new tab")
    public void clickSimpleLink() {
        linksPage.clickSimpleLinkAndSwitchTab();
    }

    @Then("the new tab URL should contain {string}")
    public void verifyNewTabUrl(String expectedPart) {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Log.info("Current tab URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains(expectedPart), "URL does not contain " + expectedPart);
    }

    @When("I click the API link {string}")
    public void clickApiLink(String linkName) {
        linksPage.clickApiLink(linkName);
    }

    @Then("the link status response text should contain {string}")
    public void verifyLinkResponse(String expectedStatus) {
        String resp = linksPage.getLinkResponseText();
        Log.info("Link API response: " + resp);
        Assert.assertTrue(resp.contains(expectedStatus), "Response missing status: " + expectedStatus);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Link_API_" + expectedStatus);
    }

    // ==========================================
    // --- BROKEN LINKS & IMAGES ---
    // ==========================================
    @Given("I open the DemoQA Broken Links and Images page")
    public void openBrokenPage() {
        String url = ConfigReader.get("app.broken.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        brokenPage = new DemoQaBrokenLinksImagesPage();
    }

    @Then("the valid image should be rendered successfully")
    public void verifyValidImage() {
        Assert.assertTrue(brokenPage.isValidImageDisplayed(), "Valid image failed to render naturalWidth > 0");
    }

    @Then("the broken image should be detected as broken")
    public void verifyBrokenImage() {
        Assert.assertTrue(brokenPage.isBrokenImageDetected(), "Broken image was not detected as broken");
    }

    @When("I click the valid link on broken page")
    public void clickValidLinkOnBrokenPage() {
        brokenPage.clickValidLink();
    }

    @When("I click the broken link on broken page")
    public void clickBrokenLinkOnBrokenPage() {
        brokenPage.clickBrokenLink();
    }

    // ==========================================
    // --- UPLOAD & DOWNLOAD ---
    // ==========================================
    @Given("I open the DemoQA Upload and Download page")
    public void openUploadDownloadPage() {
        String url = ConfigReader.get("app.uploaddownload.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        uploadDownloadPage = new DemoQaUploadDownloadPage();
    }

    @When("I click the download button")
    public void clickDownloadBtn() {
        uploadDownloadPage.clickDownloadButton();
    }

    @When("I upload the file {string}")
    public void uploadFile(String filePath) {
        uploadDownloadPage.uploadFile(filePath);
    }

    @Then("the uploaded file path display should contain {string}")
    public void verifyUploadedFilePath(String expectedFileName) {
        String actual = uploadDownloadPage.getUploadedFilePathText();
        Log.info("Uploaded path display: " + actual);
        Assert.assertTrue(actual.contains(expectedFileName), "Uploaded path does not contain: " + expectedFileName);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Upload_Success");
    }

    // ==========================================
    // --- DYNAMIC PROPERTIES ---
    // ==========================================
    @Given("I open the DemoQA Dynamic Properties page")
    public void openDynamicPropertiesPage() {
        String url = ConfigReader.get("app.dynamicproperties.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        dynamicPropertiesPage = new DemoQaDynamicPropertiesPage();
    }

    @Then("the enable after button should become clickable within {int} seconds")
    public void verifyEnableAfter(int timeoutSeconds) {
        boolean enabled = dynamicPropertiesPage.waitForButtonToBeEnabled(timeoutSeconds);
        Assert.assertTrue(enabled, "Enable after button did not become clickable within " + timeoutSeconds + "s");
    }

    @Then("the color change button should transition color within {int} seconds")
    public void verifyColorChange(int timeoutSeconds) {
        boolean changed = dynamicPropertiesPage.waitForColorChange(timeoutSeconds);
        Assert.assertTrue(changed, "Color change button did not change class to text-danger");
    }

    @Then("the visible after button should become visible within {int} seconds")
    public void verifyVisibleAfter(int timeoutSeconds) {
        boolean visible = dynamicPropertiesPage.waitForButtonToBeVisible(timeoutSeconds);
        Assert.assertTrue(visible, "Visible after button did not appear within " + timeoutSeconds + "s");
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Dynamic_VisibleAfter");
    }
}
