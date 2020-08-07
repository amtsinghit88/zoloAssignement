package com.zolostay.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GoogleSearchPage {

    @FindBy(how = How.XPATH, using = "//h2[text()='Web results']/following::div[@class='r']/a")
    public List<WebElement> searchResultLink;

    @FindBy(how = How.XPATH, using = "//a[text()='Sign in']")
    public WebElement signBtn;




    //dynamic locator

    public static String pageNavigator = "//h1[text()='Page navigation']/following::td/a[text()='nameLbl']";

}
