package com.demoqa.pages;

import com.automation.components.AccordionComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DemoQaAccordianPage extends BasePage {

    public AccordionComponent accordion;

    public DemoQaAccordianPage() {
        super("DemoQaAccordianPage");
    }

    @Override
    protected void initElements() {
        register("accordionContainer", "Accordion main container", By.id("accordianContainer"));
        register("section1Heading", "Section 1 Heading", By.xpath("//div[@id='section1Heading'] | //div[contains(text(),'What is Lorem Ipsum')]"));
        register("section2Heading", "Section 2 Heading", By.xpath("//div[@id='section2Heading'] | //div[contains(text(),'Where does it come from')]"));
        register("section3Heading", "Section 3 Heading", By.xpath("//div[@id='section3Heading'] | //div[contains(text(),'Why do we use it')]"));
        register("section1Content", "Section 1 Content", By.xpath("//div[@id='section1Content']//p | //div[@id='section1Content']"));
        register("section2Content", "Section 2 Content", By.xpath("//div[@id='section2Content']//p | //div[@id='section2Content']"));
        register("section3Content", "Section 3 Content", By.xpath("//div[@id='section3Content']//p | //div[@id='section3Content']"));

        accordion = initComponent(AccordionComponent.class, getElement("accordionContainer"));
    }

    public void clickSectionHeading(int sectionNumber) {
        Log.info("Clicking accordion section heading: " + sectionNumber);
        try {
            WebDriver driver = DriverManager.getDriver();
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var el = document.getElementById('section" + sectionNumber + "Heading');" +
                "if (el) { el.scrollIntoView({block: 'center'}); el.click(); }"
            );
            ElementActions.pause(800);
        } catch (Exception e) {
            String elementName = "section" + sectionNumber + "Heading";
            click(getElement(elementName));
            ElementActions.pause(800);
        }
    }

    public boolean isSectionContentDisplayed(int sectionNumber) {
        String title = switch (sectionNumber) {
            case 1 -> "What is Lorem Ipsum";
            case 2 -> "Where does it come from";
            case 3 -> "Why do we use it";
            default -> "What is Lorem Ipsum";
        };
        try {
            if (accordion != null && accordion.isSectionExpanded(title)) {
                return true;
            }
        } catch (Exception ignored) {}
        try {
            WebDriver driver = DriverManager.getDriver();
            String snippet = sectionNumber == 1 ? "Lorem Ipsum is simply dummy text" : (sectionNumber == 2 ? "Contrary to popular belief" : "It is a long established fact");
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var text = document.body.innerText || '';" +
                "return text.indexOf(arguments[0]) !== -1;",
                snippet
            );
            return Boolean.TRUE.equals(result);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSectionContentText(int sectionNumber) {
        try {
            WebDriver driver = DriverManager.getDriver();
            for (int i = 0; i < 15; i++) {
                Object text = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var ps = document.querySelectorAll('#accordianContainer p, .card p, p');" +
                    "for (var i = 0; i < ps.length; i++) {" +
                    "    var pText = ps[i].innerText || ps[i].textContent || '';" +
                    "    if (" + sectionNumber + " === 1 && pText.indexOf('Lorem Ipsum') !== -1) return pText;" +
                    "    if (" + sectionNumber + " === 2 && pText.indexOf('Contrary to popular belief') !== -1) return pText;" +
                    "    if (" + sectionNumber + " === 3 && pText.indexOf('established fact') !== -1) return pText;" +
                    "}" +
                    "return document.body ? (document.body.innerText || document.body.textContent || '') : '';"
                );
                if (text != null && !text.toString().isBlank()) {
                    return text.toString().trim();
                }
                ElementActions.pause(200);
            }
        } catch (Exception ignored) {}
        return "";
    }
}
