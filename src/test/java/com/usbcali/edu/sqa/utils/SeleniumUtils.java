package com.usbcali.edu.sqa.utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	public static WebDriver driver;

	public static WebElement waitElementToLoad(final By by, Integer waitTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(waitTime))
				.pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}

		});

		return element;
	}

	public static WebElement waitElementToVisibility(final By by, Integer waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		return element;
	}
}
