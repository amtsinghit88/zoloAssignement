package com.zolostay.core;

import com.zolostay.pom.GoogleHomePage;
import com.zolostay.pom.GoogleSearchPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.zolostay.utils.SeleniumUtility.*;

import java.util.HashMap;
import java.util.List;

public class CommonMethod {

    public void searchContent(RemoteWebDriver driver, HashMap<String,String> inputs) throws InterruptedException {
        GoogleHomePage googleHomePage = PageFactory.initElements(driver,GoogleHomePage.class);
        GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        seleniumClick(googleHomePage.searchTextBox);
        seleniumType(googleHomePage.searchTextBox,inputs.get("param1"));
        Thread.sleep(500);
        int count = Integer.parseInt(inputs.get("param2"));
        for(int i=0;i<count;i++){
            Thread.sleep(500);
            googleHomePage.searchTextBox.sendKeys(Keys.DOWN);
        }

        googleHomePage.searchTextBox.sendKeys(Keys.ENTER);
        waitForElementVisibility(driver,googleSearchPage.signBtn,20);
        Thread.sleep(500);
        screenshot(driver);
        List<WebElement> linkUrl = googleSearchPage.searchResultLink;
        count = Integer.parseInt(inputs.get("param3"));
        String url = linkUrl.get(count).getAttribute("href");
        driver.get(url);
        screenshot(driver);
        driver.navigate().back();
        if(isElementPresent(driver,GoogleSearchPage.pageNavigator,inputs.get("param4"))){
            scrollToViewElement(driver,GoogleSearchPage.pageNavigator,inputs.get("param4"));
            seleniumClick(driver,GoogleSearchPage.pageNavigator,inputs.get("param4"));
        }
        screenshot(driver);
        List<WebElement> linkUrl1 = googleSearchPage.searchResultLink;
        count = Integer.parseInt(inputs.get("param5"));
        String url1 = linkUrl.get(count).getAttribute("href");
        driver.get(url1);
        screenshot(driver);

    }
}
