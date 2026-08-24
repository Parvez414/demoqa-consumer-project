@DemoQa @Elements @UploadDownload
Feature: DemoQA File Upload and Download Automation

  Background:
    Given I open the DemoQA Upload and Download page

  @Smoke
  Scenario: Trigger file download
    When I click the download button

  @Smoke
  Scenario: Upload file and verify displayed path
    When I upload the file "src/test/resources/data/user_form_data.json"
    Then the uploaded file path display should contain "user_form_data.json"
