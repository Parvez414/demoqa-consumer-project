package com.demoqa.pages;

import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaCheckBoxPage extends BasePage {

    public DemoQaCheckBoxPage() {
        super("DemoQaCheckBoxPage");
    }

    @Override
    protected void initElements() {
        register("resultContainer", "Checkbox selection result display container", By.id("result"));
    }

    public void expandAllNodes() {
        Log.info("Expanding all checkbox tree nodes");
        try {
            // Expand all closed switchers in rc-tree
            for (int round = 0; round < 6; round++) {
                Long count = (Long) JavaScriptUtils.executeScript(
                        "let closed = document.querySelectorAll('.rc-tree-switcher_close, .rct-node-collapsed .rct-collapse');"
                                +
                                "closed.forEach(el => el.click());" +
                                "return closed.length;");
                if (count == null || count == 0) {
                    break;
                }
                ElementActions.pause(200);
            }
        } catch (Exception e) {
            Log.warn("Checkbox expansion error: " + e.getMessage());
        }
        ElementActions.pause(300);
    }

    public void collapseAllNodes() {
        Log.info("Collapsing all checkbox tree nodes");
        try {
            JavaScriptUtils.executeScript(
                    "let open = document.querySelectorAll('.rc-tree-switcher_open, .rct-node-expanded .rct-collapse');"
                            +
                            "open.forEach(el => el.click());");
        } catch (Exception ignored) {
        }
        ElementActions.pause(300);
    }

    public void toggleNode(String nodeName) {
        Log.info("Toggling checkbox node: [" + nodeName + "]");
        try {
            String lower = nodeName.toLowerCase();
            Object res = JavaScriptUtils.executeScript(
                    "let titles = Array.from(document.querySelectorAll('.rc-tree-title, .rct-title, span'));" +
                            "let target = titles.find(t => t.textContent.trim().toLowerCase() === arguments[0]);" +
                            "if (target) {" +
                            "    let node = target.closest('.rc-tree-treenode, .rct-node, label, li') || target.parentElement;"
                            +
                            "    let cb = node ? (node.querySelector('.rc-tree-checkbox, .rc-tree-checkbox-inner, .rct-checkbox') || node) : target;"
                            +
                            "    cb.click();" +
                            "    return 'Success toggling ' + arguments[0];" +
                            "}" +
                            "let labelOrInput = document.querySelector(\"label[for*='\" + arguments[0] + \"'], input[id*='\" + arguments[0] + \"']\");"
                            +
                            "if (labelOrInput) { labelOrInput.click(); return 'Success via label/input'; }" +
                            "return 'Node not found: ' + arguments[0];",
                    lower);
            Log.info("Toggle node result: " + res);
        } catch (Exception e) {
            Log.warn("Failed to toggle node [" + nodeName + "]: " + e.getMessage());
        }
        ElementActions.pause(500);
    }

    public String getResultText() {
        try {
            By by = By.id("result");
            WebElement res = WaitUtils.waitForPresence(by, 8);
            return res.getText().trim();
        } catch (Exception e) {
            try {
                return (String) JavaScriptUtils.executeScript(
                        "let r = document.getElementById('result') || document.querySelector('.display-result');" +
                                "return r ? r.textContent.trim() : '';");
            } catch (Exception ignored) {
                return "";
            }
        }
    }
}
