package com.usbcali.edu.sqa.views;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.usbcali.edu.sqa.utils.AirbnbSearchUtils;
import com.usbcali.edu.sqa.utils.SeleniumUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AirbnbSearchView {

	// business variables
	private boolean expectSuggestion = false;

	// location elements
	private static By inputLocation = By.name("query");
	private static By firstLocationSuggestion = By.id("bigsearch-query-location-suggestion-0");
	// in-out dates elements
	private static By calendarPanel = By.className("_sg9ssc");
	private static By calendarButton = By.className("_1akb2mdw");
	// guests elements
	private static By guestExpand = By.className("_37ivfdq");
	private static By stepperAdultsIncrease = By.cssSelector("button[data-testid='stepper-adults-increase-button']");
	private static By stepperChildrenIncrease = By
			.cssSelector("button[data-testid='stepper-children-increase-button']");
	private static By stepperInfantsIncrease = By.cssSelector("button[data-testid='stepper-infants-increase-button']");
	// submit elements
	private static By submitButton = By.className("_1mzhry13");
	// expect results elements
	private static By resultsList = By.className("_fhph4u");
	private static By filters = By.cssSelector("div[data-plugin-in-point-id='EXPLORE_WIDE_FILTER_BAR']");
	private static By desktopMap = By.cssSelector("div[data-plugin-in-point-id='EXPLORE_MAP']");

	@Given("He ingreasado a www.airbnb.com.co")
	public void heIngresadoA() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		SeleniumUtils.driver = new ChromeDriver();

		SeleniumUtils.driver.manage().window().maximize();
		SeleniumUtils.driver.navigate().to("https://www.airbnb.com.co/");
	}

	@When("^He ingresado la ubicacion ([^\"]*) sobre la barra de busqueda$")
	public void heIngresadoLaUbicacionSobreLaBarraDeBusqueda(String paramLocation) {
		String firstSuggestionTxt = "";
		String tmpParamLocation = paramLocation.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();

		SeleniumUtils.waitElementToLoad(inputLocation, 80).sendKeys(paramLocation);

		do {
			firstSuggestionTxt = AirbnbSearchUtils.checkSuggestion(firstLocationSuggestion, inputLocation,
					paramLocation);

			if (firstSuggestionTxt.equals(tmpParamLocation))
				expectSuggestion = true;
			else
				SeleniumUtils.waitElementToLoad(inputLocation, 5).sendKeys(paramLocation);

		} while (expectSuggestion == false);

		SeleniumUtils.waitElementToVisibility(firstLocationSuggestion, 80).click();
	}

	@When("^He seleccionado las fechas Actual mas (-?\\d+) dias / Actual mas (-?\\d+) dias$")
	public void heSeleccionadoLasFechasActualMasDiasActualMasDias(Integer in, Integer out) {
		//SeleniumUtils.waitElementToVisibility(calendarPanel, 60);
		AirbnbSearchUtils.openDatesPanel(calendarPanel, calendarButton);

		List<WebElement> avalibleDatesFirstMonth = SeleniumUtils.driver
				.findElements(By.xpath("//div[@class='_1foj6yps']//div[2]//div[1]//table[1]//td[@class='_12fun97']"));

		JavascriptExecutor jse = (JavascriptExecutor) SeleniumUtils.driver;

		jse.executeScript("arguments[0].click()", avalibleDatesFirstMonth.get(in));
		jse.executeScript("arguments[0].click()", avalibleDatesFirstMonth.get(out));
	}

	@When("^He establecido el numero de adultos en (-?\\d+)$")
	public void heEstablecidoElNumeroDeAdultosEn(Integer adults) {
		SeleniumUtils.waitElementToLoad(guestExpand, 30).click();

		for (int i = 0; i < adults; i++) {
			SeleniumUtils.waitElementToLoad(stepperAdultsIncrease, 30).click();
		}
	}

	@When("^He establecido el numero de ninnos en (-?\\d+)$")
	public void heEstablecidoElNumeroDeNinnosEn(Integer children) {
		for (int i = 0; i < children; i++) {
			SeleniumUtils.waitElementToLoad(stepperChildrenIncrease, 30).click();
		}
	}

	@When("^He establecido el numero de bebes en (-?\\d+)$")
	public void heEstablecidoElNumeroDeBebesEn(Integer infants) {
		for (int i = 0; i < infants; i++) {
			SeleniumUtils.waitElementToLoad(stepperInfantsIncrease, 30).click();
		}
	}

	@When("Presiono el boton del icono con lupa")
	public void presionoElBotonDelIconoConLupa() {
		SeleniumUtils.waitElementToLoad(submitButton, 30).click();
	}
	
	@When("He dejado vacio el campo de ubicacion")
	public void heDejadoVacioElCampoDeUbicacion() {
	}

	@Then("Soy redirigido a la pagina de resultados")
	public void soyRedirigidoALaPaginaDeResultados() {
		assertEquals(AirbnbSearchUtils.EXPECT_REDIRECT_PAGE_TITLE, SeleniumUtils.driver.getTitle());
	}

	@Then("Se presentan los alojamientos disponibles con su informacion")
	public void sePresentanLosAlojamientosDisponiblesConSuInformacion() {
		assertTrue(SeleniumUtils.waitElementToVisibility(resultsList, 80).isDisplayed());
	}

	@Then("Se presentan las opciones de filtrado")
	public void sePresentanLasOpcionesDeFiltrado() {
		assertTrue(SeleniumUtils.waitElementToVisibility(filters, 80).isDisplayed());
	}

	@Then("Se presenta un mapa con la ubicacion de los alojamientos")
	public void sePresentanUnMapaConLaUbicacionDeLosAlojamientos() {
		assertTrue(SeleniumUtils.waitElementToVisibility(desktopMap, 80).isDisplayed());
		SeleniumUtils.driver.quit();
	}
	
	@Then("El sistema realiza el focus sobre el campo ubicacion para escribir algo")
	public void elSistemaRealizaElFocusSobreElCampoUbicacionParaEscribirAlgo() {
		String inputLocationClases = SeleniumUtils.waitElementToLoad(inputLocation, 10).getAttribute("class");
		
		assertThat(inputLocationClases, containsString(AirbnbSearchUtils.INPUT_FOCUS_CLASS_NAME));
		SeleniumUtils.driver.quit();
	}

}
