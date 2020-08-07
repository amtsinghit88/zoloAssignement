package com.zolostay.ui;

import com.zolostay.Groups;
import com.zolostay.TestBase;
import com.zolostay.core.CommonMethod;
import com.zolostay.data.QueryPool;
import com.zolostay.drivers.WebDriverSetup;
import com.zolostay.pom.GoogleHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.util.HashMap;

public class ZoloGoogleSearchResult extends TestBase {


    RemoteWebDriver driver;
    CommonMethod commonMethod = new CommonMethod();
    String env = "www";

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browser) throws InterruptedException {
        driver = WebDriverSetup.setUpBrowser(browser);
        driver.get("https://"+env+".google.co.in");
    }


    @Test(groups = { Groups.SMOKE, Groups.REGRESSION}, priority = 1,
            dataProviderClass = QueryPool.class,dataProvider = "feedData")
    @Description("Search content at google search page")
    @Story("Search content at google search page")
    public void searchContentAtGoogle(HashMap<String, String> inputs) throws InterruptedException {
        commonMethod.searchContent(driver,inputs);


    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();

    }

}
