package com.hellofresh.selenium.base;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;


public abstract class BaseSeleniumWebPageAction {

    private final WebDriver driver;

    private final WebDriverWait _wait;

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private String browser;

    //protected final String buttonClass = "modal-backdrop";

    /**
     * The constructor
     *
     * @param driver
     */
    public BaseSeleniumWebPageAction(final WebDriver driver, final WebDriverWait wait) {
        this.driver = driver;
        this._wait = wait;
        final String driverClass = driver.getClass().getSimpleName();
        switch (driverClass) {
            case "ChromeDriver":
                this.browser = "chrome";
                break;
            case "FirefoxDriver":
                this.browser = "firefox";
                break;
            default:
               // writeToErrorSummary(null, "Don't know how to handle driver class \"" + driverClass + "\". Not implemented yet");
                break;
        }
    }

   

    public Logger getLog() {
        return log;
    }

    public void setLog(final Logger log) {
        this.log = log;
    }

    public String getBrowser() {
        return browser;
    }

    /**
     * Uses the Selenium Sleeper and catches exceptions.
     *
     * @param amount
     * @param timeUnit
     */
   /* public void driverSleep(final long amount, final TemporalUnit timeUnit) {

        try {
            Sleeper.SYSTEM_SLEEPER.sleep(Duration.of(amount, timeUnit));
            log.info("driversleep " + String.valueOf(amount) + " " + timeUnit.toString());
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Reload a the curent page
     *
     * @param driver the driver
     */
    public void reloadPage(final WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * scroll to bottom of the page
     *
     * @throws InterruptedException
     */
    public void scrollToBottom() throws InterruptedException {
        // scroll to bottom of the page
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(
                "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        //driverSleep(1, ChronoUnit.SECONDS);
    }

    /**
     * scroll to top of the page
     *
     * @throws InterruptedException
     */
    public void scrollUp() throws InterruptedException {
        // scroll to top of the page
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(500, 0)");
        //driverSleep(1, ChronoUnit.SECONDS);
    }

    /**
     * Click on an element to select it
     *
     * @param driver
     * @param buttonLocator
     * @throws InterruptedException
     */
    public void select(final WebDriver driver, final By buttonLocator) throws InterruptedException {
        // if (ExpectedConditions.elementToBeClickable(buttonLocator).apply(driver) != null) {
        if (ExpectedConditions.presenceOfAllElementsLocatedBy(buttonLocator).apply(driver) != null) {
            if (!driver.findElement(buttonLocator).isSelected()) {

                final JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(buttonLocator));
            }
        }
    }

    /**
     * Click on an checkbox to unselect it
     *
     * @param driver
     * @param checkboxLocator
     * @throws InterruptedException
     */
    public void unselect(final WebDriver driver, final By checkboxLocator) throws InterruptedException {
        if (ExpectedConditions.presenceOfAllElementsLocatedBy(checkboxLocator).apply(driver) != null) {
            if (driver.findElement(checkboxLocator).isSelected()) {

                final JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(checkboxLocator));
            }
        }
    }

    /**
     * click on a WebElement By locator using default class
     *
     * @param driver
     * @param elementLocator
     * @throws InterruptedException
     */
    public void click(final By elementLocator) throws InterruptedException {
        click(driver, elementLocator);
    }

   

	/**
     * Click on a button
     *
     * @param driver
     * @param buttonLocator
     * 
     * @throws InterruptedException
     */
    public void click(final WebDriver driver, final By buttonLocator) throws InterruptedException {

        _wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(buttonLocator));
        if (_wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).isSelected()) {
            _wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        } else {

            final WebElement button = _wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));

            final JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", button);

           // driverSleep(500, ChronoUnit.MILLIS);

            if (ExpectedConditions.stalenessOf(button).apply(driver) == null
                    && ExpectedConditions.visibilityOfAllElementsLocatedBy(buttonLocator).apply(driver) != null
                    && ExpectedConditions.alertIsPresent().apply(driver) == null) {

                try {
                    click(driver, buttonLocator);
                } catch (final StaleElementReferenceException e) {
                    return;
                }
            }
        }
    }

    /**
     * Click on a button. This method also takes an accept Condition, which is checked after clicking on the button
     * once. If the condition is fulfilled, the method ends.
     *
     * @param driver
     * @param buttonLocator
     * @param className
     * @param acceptCondition
     * @throws InterruptedException
     */
    public void click(final WebDriver driver, final By buttonLocator, final String className, final ExpectedCondition<?> acceptCondition)
            throws InterruptedException {

        _wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(buttonLocator));

        if (_wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).isSelected()) {
            _wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        } else {

            final WebElement button = _wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
            clickScript(driver, button);

            //driverSleep(500, ChronoUnit.MILLIS);

            if (ExpectedConditions.visibilityOfAllElementsLocatedBy(buttonLocator).apply(driver) != null
                    && ExpectedConditions.alertIsPresent().apply(driver) == null
                    && ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)).apply(driver) == null
                    && acceptCondition.apply(driver) == null) {

                try {
                    click(driver, buttonLocator);
                } catch (final StaleElementReferenceException e) {
                    return;
                }
            }
        }
    }

    /**
     * Click on a button without waiting. This is sometimes required when a button is not visible (e.g. when the screen
     * resolution is not high enough like on Jenkins)
     *
     * @param driver
     * @param button
     * @throws InterruptedException
     */
    public void clickScript(final WebDriver driver, final WebElement button) throws InterruptedException {
        //driverSleep(1, ChronoUnit.SECONDS);
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
    }

    /**
     * click on a option in a drop down menu
     *
     * @param driver
     * @param selector - path to the selector, e.g.: "//select[@class='userlogviewemailfilter']"
     * @param selection - option to choose
     */
    public void selectInDropDownMenu(final WebDriver driver, final By selector, final String selection) {
       // _wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        final Select select = new Select(driver.findElement(selector));
        select.selectByVisibleText(selection);
    }
    
    
    
    /**
     * click on a option in a drop down menu
     *
     * @param driver
     * @param selector - path to the selector, e.g.: "//select[@class='userlogviewemailfilter']"
     * @param selection - option to choose
     */
    public void selectInDropDownMenuByValue(final WebDriver driver, final By selector, final String selection) {
        //_wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        final Select select = new Select(driver.findElement(selector));
        select.selectByValue(selection);
    }

    /**
     * click on a option in a drop down menu. Since a drop box selector may contain more options than can be displayed
     * and it's hard to implement a scroll this is a workaround for selectByVisibleText. In comparison to method
     * selectInDropDownMenu() the selector path must append "/option" to get a list of all options for that selector.
     * Returns false if option was not found, and true if option was found.
     *
     * @param driver
     * @param selector - path to the options of a selector, e.g.: "//select[@class='userlogviewemailfilter']/option"
     * @param selection - option to choose
     * @return true if option was found, false if not
     */
    public boolean selectInDropDownMenuInvisibleOption(final WebDriver driver, final By selector, final String selection) {
        _wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selector));

        log.info("selecting option \"" + selection + "\" ...");
        boolean optionfound = false;

        final List<WebElement> options = driver.findElements(selector);
        for (final WebElement option : options) {

            if (!option.getText().isEmpty() && selection.equals(option.getText().trim())) {
                option.click();
                optionfound = true;
            }
        }

        return optionfound;
    }

   /* protected void compareTextElement(final TestCase testCase, final By locator, final String expected) {
        _wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        final WebElement element = driver.findElement(locator);
        if (ExpectedConditions.textToBePresentInElement(element, expected).apply(driver)) {
            log.info("Checked \"" + expected + "\"");
        } else {
            final Locale locale = new Locale("de");
            writeToErrorSummary(testCase,
                    String.format(locale, "Comparation error: expected '%s' but found '%s'", expected, String.format(locale, "%s", element.getText())));
        }
    }

    protected void compareValueElement(final TestCase testCase, final By locator, final String expected) {
        _wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        final String element = driver.findElement(locator).getAttribute("value");
        compareObjectValue(testCase, "Value of Element", expected, element);
    }*/

    /**
     * Enters a text to a field and hits ENTER after entered text.
     *
     * @param fieldName Name of the field to enter (only for info purpose).
     * @param inputField Input element.
     * @param value Value to enter.
     * @throws InterruptedException Exception.
     */
    public void enterValueToInputField(final String fieldName, final WebElement inputField, final String value) throws InterruptedException {
        enterValueToInputField(fieldName, inputField, value, true);
    }

    /**
     * Enters a text to a field.
     *
     * @param fieldName Name of the field to enter (only for info purpose).
     * @param inputField Input element.
     * @param value Value to enter.
     * @param sendReturn If true additionally hits ENTER after entered text.
     * @throws InterruptedException Exception.
     */
    public void enterValueToInputField(final String fieldName, final WebElement inputField, final String value, final Boolean sendReturn)
            throws InterruptedException {
        _wait.until(ExpectedConditions.visibilityOf(inputField));
        log.info("Field " + fieldName + " -> entering: \"" + value + "\"");

        if (StringUtils.isNotBlank(inputField.getAttribute("value"))) {
            final String oldvalue = inputField.getAttribute("value");

            for (@SuppressWarnings("unused")
            final char ch : oldvalue.toCharArray()) {
                inputField.sendKeys(Keys.BACK_SPACE);
            }
        }
        inputField.sendKeys(value);

        if (sendReturn) {
            // sending return in dialog boxes can close dialog...
            inputField.sendKeys(Keys.RETURN);
        }
        //driverSleep(500, ChronoUnit.MILLIS);
    }

    public void enterValueToPasswordInputField(final WebElement inputField, final String value) throws InterruptedException {
        _wait.until(ExpectedConditions.visibilityOf(inputField));

        final StringBuilder blankedOut = new StringBuilder();
        for (@SuppressWarnings("unused")
        final char ch : value.toCharArray()) {
            blankedOut.append("*");
        }

        log.info("Field Password -> entering: " + blankedOut.toString());

        if (StringUtils.isNotBlank(inputField.getAttribute("value"))) {
            final String oldvalue = inputField.getAttribute("value");

            for (@SuppressWarnings("unused")
            final char ch : oldvalue.toCharArray()) {
                inputField.sendKeys(Keys.BACK_SPACE);
            }
        }
        inputField.sendKeys(value);

        //driverSleep(500, ChronoUnit.MILLIS);
    }

    /**
     * Splits up valueString and inserts elements into inputField
     *
     * @param inputField Web element of text type.
     * @param valueString Value to insert.
     * @throws InterruptedException Exception.
     */
    public void enterMultipleValuesIntoInputField(final WebElement inputField, final String valueString) throws InterruptedException {
        enterMultipleValuesIntoInputField(inputField, valueString, false);
    }

    /**
     * Splits up valueString and inserts elements into inputField
     *
     * @param inputField Web element of text type.
     * @param valueString Value to insert.
     * @param clear If set to true the text element will be cleared.
     * @throws InterruptedException Exception.
     */
    public void enterMultipleValuesIntoInputField(final WebElement inputField, final String valueString, final boolean clear) throws InterruptedException {
        if (clear) {
            inputField.clear();
        }

        final String[] values = StringUtils.split(valueString, ",");
        for (final String value : values) {
            log.info("entering : " + value);
            inputField.sendKeys(value);
            inputField.sendKeys(Keys.RETURN);
            //driverSleep(1, ChronoUnit.SECONDS);
        }
    }

    /**
     * splits list of webelements and appends texts into a comma separated string
     *
     * @param webElementList
     * @return
     */
    public String splitMultipleEntries(final List<WebElement> webElementList) {
        final StringBuilder entryAppender = new StringBuilder();
        for (final WebElement webElement : webElementList) {
            if (entryAppender.length() > 0) {
                entryAppender.append(",");
            }
            entryAppender.append(webElement.getText());
        }

        return entryAppender.toString();
    }

    /**
     * Because in xpath, texts with single quotes (') can not be used in the expression as they stand for beginning and
     * end of strings this method splits the given string and converts it to a xpath compatible format For example: "I
     * don't know" will be converted to "concat('I don', "'", 't know')"
     *
     * @param input a string that may need to be converted to xpath compatible format
     * @return a string with sort of escaped ' characters
     */
    public String makeStringWithSingleQuotesUsable(final String input) {

        final String[] substrings = input.split("\\'");
        String output = "concat(";

        if (substrings.length == 1) {
            return "'".concat(input).concat("'");
        }

        for (int i = 0; i < substrings.length; i++) {
            if (i == substrings.length - 1) {
                // last substring does not need a ' at the end
                // concat(..,'substring')
                output = output.concat("'" + substrings[i] + "'");
            } else {
                // every substring has to be concatenated with the ' character
                // concat(..,'substring',"'",..)
                output = output.concat("'" + substrings[i] + "',\"'\",");
            }
        }

        return output.concat(")");

    }

    /**
     * enters a filename (use global path) into a FileChooser dialog
     *
     * @param filename (full path to the file)
     * @return true or false
     */
    public boolean enterFilenameInFileChooser(final String filename) {
        log.info("choosing: " + filename);
        final StringSelection stringSelection = new StringSelection(filename);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            final Robot robot = new Robot();
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(750);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(50);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * enters a filename (use global path) into a FileChooser dialog
     *
     * @param filename (full path to the file)
     * @return true or false
     */
    public boolean enterFilenameInFileChooserPressingEnterBefore(final String filename) throws InterruptedException {
        log.info("choosing: " + filename);

        final StringSelection stringSelection = new StringSelection(filename);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            final Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(50);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(750);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(50);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Click on a button even if it is not visible through display size issues
     *
     * @param webDriver
     * @param buttonLocator
     */
    public void clickOnButtonRegardlessOfVisibilty(final WebDriver webDriver, final By buttonLocator) {

        final WebElement button = webDriver.findElement(buttonLocator);

        if (button.isDisplayed()) {
            button.click();
        } else {
            final JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].click();", button);
        }

    }

   /* *//**
     * get the current date time as string
     *
     * @return time string in format yyyy-MM-dd HH:mm
     *//*
    protected String getCurrentDateTimeString() {
        final DateTime datetime = new DateTime(DateTimeZone.UTC);
        final String timeformatter = "yyyy-MM-dd HH:mm";

        return datetime.toString(timeformatter);
    }
*/
    /**
     * Finds, clicks and select item on drop down menu or listbox.
     *
     * @param listboxToClick Locator of listbox or dropdow menu.
     * @param item Item to select.
     * @throws InterruptedException Exception.
     *//*
    public void selectDropDownMenu(final By listboxToClick, final String item) throws InterruptedException {
        final By select = By.xpath("//select[@class='form-control input-sm']");
        click(driver, listboxToClick, buttonClass);
        log.info("selecting: " + item);
        selectInDropDownMenu(driver, select, item);
        driver.findElement(select).sendKeys(Keys.ENTER);
    }*/

    /**
     * Get text of a WebElement, depending on the used browser
     *
     * @param webElement - the element to get text from
     * @return String text of the element.
     */
    public String getElementText(final WebElement webElement) {

        final String theBrowser = getBrowser();
        String returnText = "Don't know how to handle browser " + theBrowser + ". Not implemented yet.";

        switch (theBrowser) {
            case "firefox":
                returnText = webElement.getText();
                break;
            case "chrome":
                returnText = webElement.getAttribute("innerText");
                break;
            default:
                break;
        }

        return returnText;
    }

    /**
     * select an option in a SelectBox by text
     *
     * @param selectLocator - locator of the select box
     * @param value - value to choose
     * @param alias - name for log output
     */
    public void chooseOptionInSelectBoxByVisibleText(final By selectLocator, final String value, final String alias) {
        final Select selectBox = new Select(_wait.until(ExpectedConditions.presenceOfElementLocated(selectLocator)));
        log.info("Selecting " + alias + " -> " + value);
        selectBox.selectByVisibleText(value);
    }

    /**
     * move an element by drag and drop
     *
     * @param webDriver
     * @param elementLocator
     * @param xOffset movement in x direction
     * @param yOffset movement in y direction
     */
    public void dragAndDropElement(final WebDriver webDriver, final By elementLocator, final int xOffset, final int yOffset) {
        log.info("drag and drop element...");
        final Actions builder = new Actions(webDriver);

        _wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(elementLocator)));
        final WebElement elementPanel = webDriver.findElement(elementLocator);

        builder.moveToElement(elementPanel).build().perform();
        _wait.until(ExpectedConditions.visibilityOf(elementPanel));
        builder.moveToElement(elementPanel).dragAndDropBy(elementPanel, xOffset, yOffset).perform();
        builder.release();
    }

    /**
     * click on button and copy output to clipboard
     *
     * @param webDriver
     * @param buttonLocator
     * @return content of clipboard as string
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws IOException
     */
    public String copyToClipboard(final WebDriver webDriver, final By buttonLocator) throws InterruptedException, UnsupportedFlavorException, IOException {
        final Actions builder = new Actions(webDriver);
        final WebElement copyToClipboardButton = webDriver.findElement(buttonLocator);
        _wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(buttonLocator)));

        log.info("copying to clipboard...");
        Action mouseOver = null;
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //driverSleep(250, ChronoUnit.MILLIS);
        clipboard.getContents(mouseOver = builder.moveToElement(copyToClipboardButton, 2, 2).click().build());
        //driverSleep(250, ChronoUnit.MILLIS);
        mouseOver.perform();
        //driverSleep(250, ChronoUnit.MILLIS);

        return clipboard.getData(DataFlavor.stringFlavor).toString();

    }

    /**
     * toggle a checkbox if the value is not matching the current status
     *
     * @param webDriver
     * @param displayName
     * @param checkBoxLocator
     * @param value
     */
    public void toggleCheckbox(final WebDriver webDriver, final String displayName, final By checkBoxLocator, final boolean value) {
        _wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(checkBoxLocator));
        final WebElement checkbox = webDriver.findElement(checkBoxLocator);
        if (!checkbox.isSelected() && value || checkbox.isSelected() && !value) {
            log.info("toggle checkbox " + displayName);
            checkbox.click();
        } else {
            log.info("nothing to toggle. Checkbox " + displayName + " is " + (checkbox.isSelected() ? " selected" : " not selected"));
        }
    }

    /**
     * find element by elementLocator and scroll element into view
     *
     * @param elementLocator
     * @throws InterruptedException
     */
    public void scrollIntoView(final By elementLocator) throws InterruptedException {
        _wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
        final WebElement element = driver.findElements(elementLocator).get(0);
        scrollIntoView(element);
    }

    /**
     * scroll element into view
     *
     * @param element
     */
    public void scrollIntoView(final WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //driverSleep(250, ChronoUnit.MILLIS);
    }

}
