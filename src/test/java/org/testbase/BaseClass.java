package org.testbase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.apache.commons.io.FileUtils;

public class BaseClass {

    public static WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    // ---------------- Browser Initialization ----------------
    public void initDriver(String browser) {
        switch(browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    public void launchUrl(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        if(driver != null) {
            driver.quit();
        }
    }

    // ---------------- Basic Actions ----------------
    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    public boolean isDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }

    public void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    // ---------------- Waits ----------------
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ---------------- Screenshot ----------------
    public void takeScreenshot(String fileName) {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------- Dropdown ----------------
    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    // ---------------- Alerts ----------------
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    // ---------------- Mouse Actions ----------------
    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    // ---------------- Scroll ----------------
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ---------------- Browser Navigation ----------------
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    // ---------------- Before & After ----------------
//    @BeforeClass
//    public void setUp() {
//        initDriver("chrome"); // Change browser if needed
//        //launchUrl("https://example.com"); // Set base URL
//    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }
    public WebElement waitForVisibility1(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}