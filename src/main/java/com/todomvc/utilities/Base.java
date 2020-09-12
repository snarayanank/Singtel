
/* Common functions used across tests
# Initiated By: Sathiya
# Date: Feb-2020 */

package com.todomvc.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static Properties prop;
    public static String currDir = System.getProperty("user.dir");
    private static Logger log = LogManager.getLogger(Base.class.getName());

    public WebDriver initializeDriver() throws IOException {
        //Read config properties for generic input
        prop = new Properties();
        String envName = System.getProperty("envname");
        if (envName == null) {
            envName = "sit";
        }
        if (envName == "sit"){
            FileInputStream fIpStr = new FileInputStream(
                    currDir + "//src//main//resources//config.sit.properties");
            prop.load(fIpStr);
        }
        else {
            FileInputStream fIpStr = new FileInputStream(
                    currDir + "//src//main//resources//config.uat.properties");
            prop.load(fIpStr);
        }

        //Get Browser Name
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            browserName = prop.getProperty("defaultBrowser");
        }
        //Run in headless mode?
        boolean headlessMode = Boolean.valueOf(prop.getProperty("headless"));

        //Get timeout value for implicit wait
        int pageLoadTimeOut = Integer.parseInt(prop.getProperty("pageLoadTimeOut"));
        int implicitTimeOut = Integer.parseInt(prop.getProperty("implicitTimeOut"));

        switch (browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOption = new ChromeOptions();
                if (headlessMode) {
                    chOption.addArguments("headless");
                }
                driver = new ChromeDriver(chOption);
                log.info("===>Chrome Browser started<====");
                break;

            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fxOption = new FirefoxOptions();
                if (headlessMode) {
                    fxOption.addArguments("-headless");
                }
                driver = new FirefoxDriver(fxOption);
                log.info("===>Firefox Browser Started<====");
                break;

            case "Edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver();
                log.info("===>Microsoft Edge Started<====");
                break;

            case "Safari":
                System.setProperty("webdriver.safari.noinstall", "true");
                WebDriverManager.edgedriver().setup();
                driver = new SafariDriver();
                log.info("===>Safari Browser Started<====");
                break;

            case "Opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                log.info("===>Opera WebBrowser Started<====");
                break;

        }
        log.info("===>Browser Session Started<====");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
        return driver;
    }

    //Take screenshot for failed tests
    public void getScreenshot(String result) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(currDir + "//ScreenshotsFailedTests//" + result + "screenshot.png"));
        log.info("===>Screen Shot Taken for failed case " + result + "<====");
    }
}