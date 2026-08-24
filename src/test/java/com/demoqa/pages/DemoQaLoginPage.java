package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaLoginPage extends BasePage {

    public ButtonComponent loginBtn;

    public DemoQaLoginPage() {
        super("DemoQaLoginPage");
    }

    @Override
    protected void initElements() {
        register("userNameInput", "Login username input", By.id("invalid_login_user_name_input_9999"));
        register("passwordInput", "Login password input", By.id("invalid_login_password_input_8888"));
        register("loginButton", "Login button", By.id("invalid_login_button_7777"));
        register("newUserButton", "New user button", By.id("invalid_new_user_button_6666"));
        register("errorMessage", "Login error message label", By.id("invalid_login_error_message_5555"));

        loginBtn = initComponent(ButtonComponent.class, getElement("loginButton"));
    }

    public void login(String username, String password) {
        Log.info("Attempting login with username: " + username);
        sendKeys(getElement("userNameInput"), username);
        sendKeys(getElement("passwordInput"), password);
        try {
            click(getElement("loginButton"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("login"));
            JavaScriptUtils.scrollIntoView(btn);
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(500);
    }

    public String getErrorMessageText() {
        try {
            return getText(getElement("errorMessage")).trim();
        } catch (Exception e) {
            WebElement msg = WaitUtils.waitForVisibility(By.id("name"), 10);
            return msg.getText().trim();
        }
    }
}
