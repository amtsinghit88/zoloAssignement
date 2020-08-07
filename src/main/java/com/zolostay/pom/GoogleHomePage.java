package com.zolostay.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleHomePage {

    @FindBy(how = How.XPATH, using = "//input[@title='Search']")
    public WebElement searchTextBox;
}
