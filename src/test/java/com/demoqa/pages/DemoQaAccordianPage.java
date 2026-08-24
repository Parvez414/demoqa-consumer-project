package com.demoqa.pages;

import com.automation.components.AccordionComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DemoQaAccordianPage extends BasePage {

    public AccordionComponent accordion;

    public DemoQaAccordianPage() {
        super("DemoQaAccordianPage");
    }

    @Override
    protected void initElements() {
        register("accordionContainer", "Accordion main container", By.id("broken_accordian_container_9999"));
        register("section1Heading", "Section 1 Heading", By.id("broken_section1_heading_8888"));
        register("section2Heading", "Section 2 Heading", By.id("broken_section2_heading_7777"));
        register("section3Heading", "Section 3 Heading", By.id("broken_section3_heading_6666"));
        register("section1Content", "Section 1 Content", By.id("broken_section1_content_5555"));
        register("section2Content", "Section 2 Content", By.id("broken_section2_content_4444"));
        register("section3Content", "Section 3 Content", By.id("broken_section3_content_3333"));

        accordion = initComponent(AccordionComponent.class, getElement("accordionContainer"));
    }

    public void clickSectionHeading(int sectionNumber) {
        Log.info("Clicking accordion section heading: " + sectionNumber);
        String elementName = "section" + sectionNumber + "Heading";
        try {
            WebElement heading = waitForVisibility(getElement(elementName), 10);
            JavaScriptUtils.scrollIntoView(heading);
            click(getElement(elementName));
        } catch (Exception e) {
            String textSnippet = switch (sectionNumber) {
                case 1 -> "Lorem Ipsum";
                case 2 -> "Where does it come from";
                case 3 -> "Why do we use it";
                default -> "Heading";
            };
            By by = By.xpath(
                    "//*[contains(text(),'" + textSnippet + "')] | " +
                    "(//div[@id='accordianContainer']//div[contains(@class,'card-header')])[" + sectionNumber + "] | " +
                    "(//div[contains(@class,'card-header')])[" + sectionNumber + "] | " +
                    "//div[@id='section" + sectionNumber + "Heading']"
            );
            WebElement heading = WaitUtils.waitForVisibility(by, 10);
            JavaScriptUtils.scrollIntoView(heading);
            JavaScriptUtils.clickElement(heading);
        }
        ElementActions.pause(600);
    }

    public boolean isSectionContentDisplayed(int sectionNumber) {
        try {
            String elementName = "section" + sectionNumber + "Content";
            if (isDisplayed(getElement(elementName))) {
                return true;
            }
        } catch (Exception ignored) {}

        try {
            String textSnippet = switch (sectionNumber) {
                case 1 -> "Lorem Ipsum is simply dummy text";
                case 2 -> "Contrary to popular belief";
                case 3 -> "It is a long established fact";
                default -> "";
            };
            By by = By.xpath(
                    "//p[contains(text(),'" + textSnippet + "')] | " +
                    "(//div[@id='accordianContainer']//div[contains(@class,'collapse')])[" + sectionNumber + "] | " +
                    "//div[@id='section" + sectionNumber + "Content']"
            );
            List<WebElement> els = DriverManager.getDriver().findElements(by);
            if (!els.isEmpty()) {
                for (WebElement el : els) {
                    if (el.isDisplayed()) return true;
                    String classes = el.getAttribute("class");
                    if (classes != null && classes.contains("show")) return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSectionContentText(int sectionNumber) {
        try {
            String elementName = "section" + sectionNumber + "Content";
            String txt = getText(getElement(elementName));
            if (txt != null && !txt.isBlank()) {
                return txt.trim();
            }
        } catch (Exception ignored) {}

        String textSnippet = switch (sectionNumber) {
            case 1 -> "Lorem Ipsum is simply dummy text";
            case 2 -> "Contrary to popular belief";
            case 3 -> "It is a long established fact";
            default -> "";
        };
        By by = By.xpath(
                "//p[contains(text(),'" + textSnippet + "')] | " +
                "(//div[@id='accordianContainer']//div[contains(@class,'card-body')])[" + sectionNumber + "] | " +
                "//div[@id='section" + sectionNumber + "Content']"
        );
        WebElement content = WaitUtils.waitForVisibility(by, 10);
        return content.getText().trim();
    }
}
