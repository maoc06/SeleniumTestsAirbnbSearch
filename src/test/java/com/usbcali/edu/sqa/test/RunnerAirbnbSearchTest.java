package com.usbcali.edu.sqa.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		snippets = CucumberOptions.SnippetType.CAMELCASE, 
		features = "src/test/resources", 
		glue = {"com.usbcali.edu.sqa.views", "com.usbcali.edu.sqa.configuration" }, 
		plugin = { "pretty", "html:target/airbnb-search-report.html" },
		tags = "@searchInColombiaCity or @searchWithEmptyLocation"
		)
public class RunnerAirbnbSearchTest {

}
