# ⚛️ DemoQA Test Automation Consumer Project (React SPA & Modern UI)

A comprehensive standalone BDD test automation project automating **[DemoQA](https://demoqa.com/)** using the **AI-Powered Test Automation Framework Core SDK** (`com.automation:ai-automation-framework:1.0.0`).

---

## 📋 Table of Contents
1. [Overview & Architecture](#1-overview--architecture)
2. [Complete Automated Test Suites Matrix](#2-complete-automated-test-suites-matrix)
3. [Components Demonstrated](#3-components-demonstrated)
4. [Prerequisites & SDK Installation](#4-prerequisites--sdk-installation)
5. [Running Tests Locally](#5-running-tests-locally)
6. [Docker & Containerized Execution](#6-docker--containerized-execution)
7. [AI Self-Healing & Gemini 3.6 Setup](#7-ai-self-healing--gemini-36-setup)
8. [Centralized Web Dashboard & Offline HTML Reports](#8-centralized-web-dashboard--offline-html-reports)
9. [Git Auto-Patch & Self-Healing PR Generator](#9-git-auto-patch--self-healing-pr-generator)
10. [Configuration Properties Reference](#10-configuration-properties-reference)

---

## 1. Overview & Architecture

This project automates the entire **DemoQA** application across 6 functional domains:
- **Elements**: Text Box, Check Box, Radio Button, Web Tables (CRUD & Pagination), Buttons, Links, Broken Links/Images, Upload & Download, Dynamic Properties.
- **Forms**: Complete Student Registration Practice Form (Complex React DatePicker, Multi-Autocomplete Tags, Dynamic Radios/Checkboxes, State/City Cascading Dropdowns, and Submission Modal).
- **Alerts, Frame & Windows**: Browser Tabs, Multiple Windows, Iframes, Nested Frames, Modal Dialogs (Small & Large), JavaScript Alerts.
- **Widgets**: Accordion Panels, Multi/Single Autocomplete Tags, DatePicker & DateTime, Range Slider, Progress Bar with Reset, Tabbed Navigation, Tooltip Popovers, Multi-Level Nested Hover Menu, Select Menu.
- **Interactions**: Drag and Drop Sortable, Multi-Selectable Lists and Grids, Resizable Box Constraints, Droppable Target Area, Dragabble Position Offset.
- **Book Store Application**: Catalog Book Search, Book Details Inspection, Return to Store Navigation, Login User Authentication.

All driver management, element waiting, pre-flight validation, and AI healing are inherited directly from the Core SDK in `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.automation</groupId>
        <artifactId>ai-automation-framework</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

---

## 2. Complete Automated Test Suites Matrix

| # | Feature File | Target Page URL | Page Object | Tags | Key Capabilities & Scenarios |
| :- | :--- | :--- | :--- | :--- | :--- |
| 1 | **`01_SelectMenu.feature`** | `/select-menu` | `DemoQaSelectMenuPage` | `@SelectMenu`, `@Smoke` | React Select Grouping, Select One Title, Old Style HTML Select, Multi-Select Badges, Standard Cars |
| 2 | **`02_TextBox_Form.feature`** | `/text-box` | `DemoQaTextBoxPage` | `@TextBox`, `@Smoke` | Full Name, Email, Current & Permanent Address JSON form submission & output validation |
| 3 | **`03_RadioAndButtons.feature`** | `/radio-button`, `/buttons` | `DemoQaRadioButtonPage`, `DemoQaButtonsPage` | `@Buttons`, `@Smoke` | Yes / Impressive Radios, Disabled Radio assert, Double Click, Context Right Click, Dynamic Click |
| 4 | **`04_WebTables.feature`** | `/webtables` | `DemoQaWebTablesPage` | `@WebTables`, `@Regression` | Table search, Add new employee modal, Inline Edit record, Delete row, Rows-per-page pagination |
| 5 | **`05_Alerts_Modals.feature`** | `/alerts` | `DemoQaAlertsPage` | `@Alerts`, `@Smoke` | Simple JS Alert, Confirm Box (Accept/Dismiss), Prompt Alert text entry & result assertion |
| 6 | **`06_AI_SelfHealing_Demo.feature`** | `/text-box` | `DemoQaBrokenHealingPage` | `@SelfHealing` | Dual-Tier AI Pre-Flight Self-Healing of broken locators with JSON report generation |
| 7 | **`07_CheckBox.feature`** | `/checkbox` | `DemoQaCheckBoxPage` | `@CheckBox`, `@Smoke` | Expand / Collapse all tree nodes, Toggle Home folder, Desktop / Documents / Downloads assertions |
| 8 | **`08_Links.feature`** | `/links` | `DemoQaLinksPage` | `@Links`, `@Smoke` | Simple & Dynamic tab links, API links (201 Created, 204 No Content, 301, 400, 401, 403, 404) |
| 9 | **`09_BrokenLinksImages.feature`** | `/broken` | `DemoQaBrokenLinksImagesPage` | `@Broken` | `naturalWidth` image rendering validation, broken image detection, 200 vs 500 link navigation |
| 10 | **`10_UploadDownload.feature`** | `/upload-download` | `DemoQaUploadDownloadPage` | `@UploadDownload`, `@Smoke` | File download trigger and file upload path display assertion |
| 11 | **`11_DynamicProperties.feature`** | `/dynamic-properties` | `DemoQaDynamicPropertiesPage` | `@DynamicProperties` | 5-second enable button, color change text (`text-danger`), visible after 5s button |
| 12 | **`12_PracticeForm.feature`** | `/automation-practice-form` | `DemoQaPracticeFormPage` | `@Forms`, `@PracticeForm` | Complete student registration form (Datepicker, Autocomplete, Hobbies, Picture, State/City, Modal) |
| 13 | **`13_BrowserWindows.feature`** | `/browser-windows` | `DemoQaBrowserWindowsPage` | `@AlertsWindows`, `@BrowserWindows` | New Browser Tab switching & heading validation, New Window popup management |
| 14 | **`14_Frames.feature`** | `/frames` | `DemoQaFramesPage` | `@AlertsWindows`, `@Frames` | Switch to iFrame 1 and iFrame 2, assert heading text, return to default content |
| 15 | **`15_NestedFrames.feature`** | `/nestedframes` | `DemoQaNestedFramesPage` | `@AlertsWindows`, `@NestedFrames` | Switch to parent frame -> switch to child iframe inside parent -> default content |
| 16 | **`16_ModalDialogs.feature`** | `/modal-dialogs` | `DemoQaModalDialogsPage` | `@AlertsWindows`, `@ModalDialogs` | Small Modal & Large Modal trigger, header title, body text inspection, and close |
| 17 | **`17_Accordian.feature`** | `/accordian` | `DemoQaAccordianPage` | `@Widgets`, `@Accordian` | Section 1 default expanded, Section 2 & 3 toggle, collapsible animation assertion |
| 18 | **`18_AutoComplete.feature`** | `/auto-complete` | `DemoQaAutoCompletePage` | `@Widgets`, `@AutoComplete` | Multi-value color tags creation and removal, Single color autocomplete input |
| 19 | **`19_DatePicker.feature`** | `/date-picker` | `DemoQaDatePickerPage` | `@Widgets`, `@DatePicker` | Select Date calendar month/year/day picker, Date & Time picker slot selection |
| 20 | **`20_Slider.feature`** | `/slider` | `DemoQaSliderPage` | `@Widgets`, `@Slider` | Interactive range slider dragging (50, 75, 90) and input value box sync |
| 21 | **`21_ProgressBar.feature`** | `/progress-bar` | `DemoQaProgressBarPage` | `@Widgets`, `@ProgressBar` | Start progress bar, wait until 100% completion, assert complete state, reset button |
| 22 | **`22_Tabs.feature`** | `/tabs` | `DemoQaTabsPage` | `@Widgets`, `@Tabs` | Switch between What, Origin, Use tabs, assert panel content, assert disabled More tab |
| 23 | **`23_ToolTips.feature`** | `/tool-tips` | `DemoQaToolTipsPage` | `@Widgets`, `@ToolTips` | Hover action on button and textfield, verify dynamic popover tooltip text |
| 24 | **`24_Menu.feature`** | `/menu` | `DemoQaMenuPage` | `@Widgets`, `@Menu` | Multi-tier nested hover navigation (Main Item 2 -> SUB SUB LIST -> Sub Sub Item 1/2) |
| 25 | **`25_Sortable.feature`** | `/sortable` | `DemoQaSortablePage` | `@Interactions`, `@Sortable` | Drag and drop list reordering and position assertion |
| 26 | **`26_Selectable.feature`** | `/selectable` | `DemoQaSelectablePage` | `@Interactions`, `@Selectable` | Vertical list multi-selection and Grid box item selection with active styling |
| 27 | **`27_Resizable.feature`** | `/resizable` | `DemoQaResizablePage` | `@Interactions`, `@Resizable` | Drag resize handle with min/max box boundary verification |
| 28 | **`28_Droppable.feature`** | `/droppable` | `DemoQaDroppablePage` | `@Interactions`, `@Droppable` | Drag and drop draggable onto target drop area, assert "Dropped!" status |
| 29 | **`29_Dragabble.feature`** | `/dragabble` | `DemoQaDragabblePage` | `@Interactions`, `@Dragabble` | Coordinate offset dragging and element position updates |
| 30 | **`30_BookStore.feature`** | `/books` | `DemoQaBookStorePage` | `@BookStore`, `@Smoke` | Search catalog by title ("Git Pocket Guide"), view Book Details (Author, Publisher), back to store |
| 31 | **`31_Login.feature`** | `/login` | `DemoQaLoginPage` | `@BookStore`, `@Login` | Submit invalid user authentication credentials and assert rejection message |

---

## 3. Components Demonstrated

### 1. `SelectComponent` (Universal React / Angular / HTML Dropdowns)
```java
// Select from React-Select Option Group container
selectValueDropdown.selectByText("Group 2, option 1");

// Multi-select multiple badges
multiselectDropdown.selectMultipleByText(List.of("Green", "Blue"));

// Standard HTML select
oldStyleSelect.selectByVisibleText("Aqua");
```

### 2. `AccordionComponent`
```java
accordion.expandSection("Where does it come from?");
boolean isExpanded = accordion.isSectionExpanded("Where does it come from?");
```

### 3. `TabsComponent`
```java
tabs.selectTab("Origin");
boolean isActive = tabs.isTabActive("Origin");
```

### 4. `ModalComponent`
```java
modal.waitForOpen(5);
String title = modal.getTitle();
modal.close();
```

---

## 4. Prerequisites & SDK Installation

### System Requirements
- **Java JDK**: JDK 17 LTS (`java -version`)
- **Apache Maven**: 3.8.0+ (`mvn -version`)
- **Google Chrome**: Desktop browser (latest version)

### 📦 Install Core SDK JAR into Local Maven Repository

This project depends on the **AI-Powered Automation Framework Core SDK** (`com.automation:ai-automation-framework:1.0.0`). The pre-packaged SDK JAR is provided in the [`core_sdk_jar/`](core_sdk_jar/) directory.

To install this JAR into your local Maven cache (`~/.m2/repository`), run the following command from the project root:

#### On Linux / macOS / Bash:
```bash
mvn install:install-file \
  -Dfile=core_sdk_jar/ai-automation-framework-1.0.0.jar \
  -DgroupId=com.automation \
  -DartifactId=ai-automation-framework \
  -Dversion=1.0.0 \
  -Dpackaging=jar
```

#### On Windows (PowerShell / Command Prompt):
```powershell
mvn install:install-file "-Dfile=core_sdk_jar/ai-automation-framework-1.0.0.jar" "-DgroupId=com.automation" "-DartifactId=ai-automation-framework" "-Dversion=1.0.0" "-Dpackaging=jar"
```

Once installed, Maven will resolve all SDK classes, components, driver utilities, and test helpers seamlessly.

---

## 5. Running Tests Locally

```bash
# 1. Run all DemoQA scenarios
mvn test

# 2. Run specifically the Smoke test suite
mvn test -Dtest=DemoQaTestRunner "-Dcucumber.filter.tags=@Smoke"

# 3. Run in Headless mode (Fast CI/CD execution)
mvn test -Dheadless=true

# 4. Run across multiple threads in parallel
mvn test -Dthread.count=4

# 5. Run specific modules by Cucumber tag
mvn test "-Dcucumber.filter.tags=@Elements"
mvn test "-Dcucumber.filter.tags=@Forms"
mvn test "-Dcucumber.filter.tags=@AlertsWindows"
mvn test "-Dcucumber.filter.tags=@Widgets"
mvn test "-Dcucumber.filter.tags=@Interactions"
mvn test "-Dcucumber.filter.tags=@BookStore"

# 6. Run on Cloud Platforms (BrowserStack, SauceLabs, LambdaTest)
mvn test -Dexecution.mode=remote -Dcloud.provider=browserstack -Dcloud.username=MY_USER -Dcloud.accesskey=MY_KEY
```

---

## 6. Docker & Containerized Execution

```bash
# Spin up isolated Linux container, execute tests in headless Chrome, and map reports to host:
docker-compose run --rm demoqa-test-runner

# Run specific tags in Docker:
docker-compose run --rm demoqa-test-runner "-Dcucumber.filter.tags=@Smoke"
```

---

## 7. AI Self-Healing & Gemini 3.6 Setup

Dual-tier self-healing configuration in `src/test/resources/config/config.properties`:
```properties
ai.healing.enabled=true
ai.healing.provider=heuristic
ai.healing.confidence.threshold=0.70
gemini.api.key=YOUR_API_KEY_HERE
gemini.model.name=gemini-3.6-flash
```

---

## 8. Centralized Web Dashboard & Offline HTML Reports

- **Interactive Standalone HTML Report**: `target/ai-dashboard/index.html`
- **AI Element Healing History**: `target/element-healing-history.json`
- **Step & Failure Screenshots**: `target/screenshots/`
- **Allure Interactive Report**: `mvn allure:serve`
