package com.zolostay.utils;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class SeleniumUtility {

	public static LocalDate currentDate = LocalDate.now();

	public static void seleniumClick(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			waitForElementClickable(driver, driver.findElement(By.xpath(val)),10);
			driver.findElement(By.xpath(val)).click();
		} catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + loc);
		}

	}

	public static void seleniumClick(WebElement we) {
		try {
			if(we.isDisplayed())
				we.click();
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc) {
			Reporter.log("No such element found::locator value " + we, true);
			Assert.fail("No such element found::locator value " + we);
		}
	}

	public static void javaExecutorClick(RemoteWebDriver driver, By by) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
	}

	public static void jsClick(RemoteWebDriver driver, String loc, String replaceVal) {
		String val;
		try {
			if (!replaceVal.isEmpty()) {
				val = getXpathByReplace(loc, replaceVal);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(val)));
			} else {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(loc)));
			}
		} catch (NoSuchElementException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			Assert.fail("No such element found::locator value" + loc);
		}
	}

	public static void seleniumType(RemoteWebDriver driver, String loc, String replaceVal, String type) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			if (seleniumGetText(driver, loc, replaceVal).length() > 0) {
				driver.findElement(By.xpath(val)).clear();
			}
			driver.findElement(By.xpath(val)).sendKeys(type);
		} catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + loc);
		}
	}

	public static void seleniumType(WebElement we, String type) {
		try {
			we.clear();
			Thread.sleep(100);
			we.sendKeys(type);
			/*
			 * for (int i = 0; i < type.length(); i++){
			 * we.sendKeys(Character.toString(type.charAt(i)));
			 * wait.until(attributeContains(we, "value", type.substring(0, i))); }
			 */
		} catch (NoSuchElementException | StaleElementReferenceException | InterruptedException | InvalidElementStateException exc) {
			Reporter.log("No such element found::locator value" + we, true);
			Assert.fail("No such element found::locator value" + we);
		}
	}

	public static String seleniumGetText(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			return driver.findElement(By.xpath(val)).getText();
		} catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + loc);
		}
		return null;
	}

	public static StringBuffer seleniumGetText(RemoteWebDriver driver, List<WebElement> list) {
		StringBuffer sb = new StringBuffer();
		try {
			for(WebElement we : list) {
				sb.append(seleniumGetText(we));
				sb.append(" ");

			}

		} catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + list, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + list);
		}
		return sb;
	}

	public static String seleniumGetText(WebElement we) {
		try {
			return we.getText();
		}catch(NoSuchElementException | StaleElementReferenceException exc){
			Reporter.log("No such element found::locator value" + we.toString(), true);
			//Assert.fail();
		}
		return null;
	}

	public static String getXpathByReplace(String loc, String replacedVal) {
		return loc.replaceAll("(?i)" + "nameLbl", replacedVal);
	}

	public static void scrollToViewElement(RemoteWebDriver driver, WebElement scroll) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
		}catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + scroll, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + scroll);
		}
	}

	public static void scrollToViewElement(RemoteWebDriver driver, String loc, String replacedVal) {
		WebElement scroll = null;
		try {
			String str = loc.replaceAll("(?i)" + "nameLbl", replacedVal);
			scroll = driver.findElement(By.xpath(str));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
		}catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + scroll, true);
			screenshot(driver);
			Assert.fail("No such element found::locator value" + scroll);
		}
	}

	public static String getEleByMultiReplace(String loc, String replacedVal, String replaced2ndVal) {
		String xpath;
		xpath = loc.replaceAll("(?i)" + "nameLbl", replacedVal);
		xpath = xpath.replaceAll("(?i)" + "valueLbl", replaced2ndVal);
		return xpath;
	}

	public static boolean isElementPresent(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			driver.findElement(By.xpath(val)).isDisplayed();
			return true;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isElementPresent(WebElement we) {
		try {
			we.isDisplayed();
			return true;
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			return false;
		}
	}

	public static boolean textPresentAtPageSource(RemoteWebDriver driver, String text) {
		if (driver.getPageSource().contains(text))
			return true;
		else
			return false;
	}

	public static boolean isElementVisible(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			driver.findElement(By.xpath(val));
			return true;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static String getAttributeValue(RemoteWebDriver driver, String loc, String replaceVal, String attVal) {
		String val = getXpathByReplace(loc, replaceVal);
		return driver.findElement(By.xpath(val)).getAttribute(attVal);
	}

	public static String getAttributeValue(WebElement we, String attVal) {
		String attribute = we.getAttribute(attVal);
		if(attribute==null) {
			return "false";
		}else
			return attribute;
	}


	public static void vListItem(WebElement we , String text) {
		boolean eleAvailable = false;
		Select select = new Select(we);
		List<WebElement> list = select.getOptions();
		for (WebElement e : list) {
			if (e.getText().contains(text)) {
				Reporter.log(text + " found into list", true);
				eleAvailable = true;
				return;
			}
		}

		if (eleAvailable == false) {
			Reporter.log(text + " isn't found into list", true);
			AssertJUnit.assertTrue(false);
		}

	}

	public static void selectItemFromList(WebElement we, String text) {
		try {
			Select select = new Select(we);
			List<WebElement> list = select.getOptions();
			for (WebElement e : list) {
				if (e.getText().contains(text)) {
					select.selectByVisibleText(e.getText());
					return;
				}
			}
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			Reporter.log("No such element found::locator value" + we, true);
			Assert.fail("No such element found::locator value" + we);
		}
	}

	public static void selectItemFromList(RemoteWebDriver driver,String loc, String replaceVal, String text) {
		WebElement we = null;
		try {
			String val = getXpathByReplace(loc, replaceVal);
			waitForElementClickable(driver, driver.findElement(By.xpath(val)),10);
			we = driver.findElement(By.xpath(val));
			Select select = new Select(we);
			List<WebElement> list = select.getOptions();
			for (WebElement e : list) {
				if (e.getText().contains(text)) {
					select.selectByVisibleText(e.getText());
					return;
				}
			}
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			Reporter.log("No such element found::locator value" + we, true);
			Assert.fail("No such element found::locator value" + we);
		}
	}


	public static void waitForElement(RemoteWebDriver driver, By loc, String waitType) {
		WebDriverWait wait = null;
		switch (waitType) {

			case "clickable":
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(loc)));
				break;
			case "visible":
				wait.until(ExpectedConditions.presenceOfElementLocated(loc));
				break;

		}
	}

	public static boolean waitForElementVisibility(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element:"+" is not visible after waitng after " + Time + " seconds.");
			screenshot(driver);
			return false;
		}

	}

	public static boolean waitForElementInVisibility(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.invisibilityOf(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element:"+" is visible after waitng after " + Time + " seconds.");
			screenshot(driver);
			Reporter.log(""+we);
			return false;
		}

	}

	public static boolean waitForElementClickable(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element: "+" isnot clickable after waitng after " + Time + " seconds.</p>");
			return false;
		}

	}


	@Attachment(type = "image/png")
	public static byte[] screenshot(RemoteWebDriver driver) {
		try {
			File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			return Files.toByteArray(screen);
		} catch (IOException e) {
			return null;
		}
	}

}
