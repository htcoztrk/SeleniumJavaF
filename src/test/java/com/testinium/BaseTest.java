package com.testinium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
* The type Hooks.
* This class sets driver, capabilities and some of options
*/
public class BaseTest {

    public static Logger logger = Logger.getLogger(BaseTest.class);
    protected static WebDriver driver;
    protected static Actions actions;
    /**
    * Selected Browser name and platform, initialize capabilities
    *
    * @param capabilities
    */
    static String browserName = "chrome";
    static DesiredCapabilities capabilities;

    @BeforeAll
    public static void beforeTest() {
        logger.info("************************************  BeforeScenario  ************************************");
        try {
            ChromeOptions options = new ChromeOptions();
            capabilities = DesiredCapabilities.chrome();
            options.setExperimentalOption("w3c", false);
            options.addArguments("disable-translate");
            options.addArguments("--disable-notifications");
            options.addArguments("--start-fullscreen");
            Map<String, Object> prefs = new HashMap<>();
            options.setExperimentalOption("prefs", prefs);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability("key", System.getProperty("key"));
            browserName = System.getenv("browser");
            driver = new RemoteWebDriver(new URL("http://host.docker.internal:4444/wd/hub"), capabilities);
            actions = new Actions(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
    * After test.
    * Quit driver
    */
    @AfterAll
    public static void afterTest() {
        driver.quit();
    }

    /**
    * @return the web driver
    */
    public static WebDriver getWebDriver() {
        return driver;
    }
}
