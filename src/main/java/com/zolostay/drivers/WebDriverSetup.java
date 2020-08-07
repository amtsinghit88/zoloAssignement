package com.zolostay.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverSetup {

    public static final String ESCAPE_PROPERTY="org.uncommons.reportng.escape-output";
    public static WebDriverWait wait;

    public static RemoteWebDriver setUpBrowser(String browser) {
        RemoteWebDriver driver;
        driver = getDriver(browser);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        System.setProperty(ESCAPE_PROPERTY,"false");
        return driver;

    }

    public static RemoteWebDriver getDriver(String browser) {
        RemoteWebDriver driver = null;

        String path;
        switch (browser) {
            case "chrome":
                driver = setUpChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("marionette", true);
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(options);
                break;
        }

        driver.manage().window().maximize();
        return driver;

    }

    public static ChromeDriver setUpChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> images = new HashMap<>();
        images.put("images", 2);
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        return new ChromeDriver(options);

    }
}
