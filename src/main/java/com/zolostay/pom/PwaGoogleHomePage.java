package com.zolostay.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PwaGoogleHomePage {

    @FindBy(how = How.XPATH, using = "//input[@type='search']")
    public WebElement searchTextBox;

    @FindBy(how = How.XPATH, using = "//h2[text()='Web results']/following::a")
    public List<WebElement> searchResultLink;


}
