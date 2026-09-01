package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;

public class DemoQaLoginPage extends BasePage {

    public ButtonComponent loginBtn;

    public DemoQaLoginPage() {
        super("DemoQaLoginPage");
    }

    @Override
    protected void initElements() {
        register("userNameInput", "Login username input", By.id("userName"));
        register("passwordInput", "Login password input", By.id("password"));
        register("loginButton", "Login button", By.id("login"));
        register("newUserButton", "New user button", By.id("newUser"));
        register("errorMessage", "Login error message label", By.id("name"));

        loginBtn = initComponent(ButtonComponent.class, getElement("loginButton"));
    }

    public void login(String username, String password) {
        Log.info("Attempting login with username: " + username);
        sendKeys(getElement("userNameInput"), username);
        sendKeys(getElement("passwordInput"), password);
        click(getElement("loginButton"));
        ElementActions.pause(500);
    }

    public String getErrorMessageText() {
        return getText(getElement("errorMessage")).trim();
    }
}
