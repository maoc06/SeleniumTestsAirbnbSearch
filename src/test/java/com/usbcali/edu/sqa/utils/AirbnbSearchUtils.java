package com.usbcali.edu.sqa.utils;

import org.openqa.selenium.By;

public class AirbnbSearchUtils {
	public static final String EXPECT_REDIRECT_PAGE_TITLE = "Airbnb: alojamientos para vacaciones, cabañas, casas en la playa, espacios únicos y experiencias";
	public static final String INPUT_FOCUS_CLASS_NAME = "focus-visible";
	public static String suggestionTxt = "";

	private static String getSuggestionTxt(By suggestion) {
		return SeleniumUtils.waitElementToVisibility(suggestion, 5).getText().replaceAll("[^a-zA-Z0-9]", "")
				.toLowerCase().trim();
	}

	private static boolean isSuggestionDisplayed(By suggestionPanel, By inputLocation, String paramLocation) {
		boolean isDisplayed = true;

		try {
			suggestionTxt = getSuggestionTxt(suggestionPanel);
		} catch (Exception e) {
			isDisplayed = false;
		}

		return isDisplayed;
	}

	public static String checkSuggestion(By suggestionPanel, By inputLocation, String paramLocation) {
		if (!isSuggestionDisplayed(suggestionPanel, inputLocation, paramLocation))
			SeleniumUtils.waitElementToLoad(inputLocation, 5).clear();

		return suggestionTxt;
	}
	
	public static void openDatesPanel(By calendarPanel, By calendarButton) {
		try {
			SeleniumUtils.waitElementToVisibility(calendarPanel, 5);
		} catch (Exception e) {
			SeleniumUtils.waitElementToLoad(calendarButton, 30).click();
		}		
	}
}
