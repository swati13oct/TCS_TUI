package stepdefinition;



import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Pages.HotelPages;
import base.base;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefiniton extends base{
	
	
	
	@Given("I have launched the browser")
	public void i_have_launched_the_browser() throws IOException {
		
		//webdriver launched from base class based on browser selected in config.properties file
		 driver = initializeDriver();

	}
	
	
	@Given("I have navigated to TUI home page")
	public void i_have_navigated_to_tui_home_page() throws IOException {
		
		 //Navigate to stay.tui.com
		launchUrl();
			
	}

	@Given("I have selected country {string}")
	public void i_have_selected_brasil_as_my_country(String country) throws InterruptedException {
		HotelPages hotel = new HotelPages(driver);
		hotel.clickOnAcceptCookies();
		hotel.clickOnCountry(country);
			
	}

	@When("I enter city name as {string} in Find a destination text box to search for hotel")
	public void i_enter_city_name_as_in_find_a_destination_text_box_to_search_for_hotel(String city) throws InterruptedException {
		
		HotelPages hotel = new HotelPages(driver);
		hotel.enterCityName(city);
		
	}

	@When("I select the from {string} and to dates {string} in the calendar")
	public void i_select_the_from_and_to_dates_in_the_calendar(String fromDate, String toDate) throws InterruptedException {
		HotelPages hotel = new HotelPages(driver);
		hotel.selectDates(fromDate, toDate);
		
	}

	@When("I select the number of guests")
	public void i_select_the_number_of_guests() {
		HotelPages hotel = new HotelPages(driver);
		hotel.selectNumberOfGuests();
		
	}

	@When("I clicked on Search button")
	public void i_clicked_on_search_button() {
		HotelPages hotel = new HotelPages(driver);
		hotel.clickOnSearchButton();

	}

	@Then("site redirects to available hotels for the chosen dates")
	public void site_redirects_to_available_hotels_for_the_chosen_dates() {
		HotelPages hotel = new HotelPages(driver);
		hotel.verifySearchResults();
				
	}

	@Then("I check atleast one hotel is displayed in search results")
	public void i_check_atleast_one_hotel_is_displayed_in_search_results() {
		HotelPages hotel = new HotelPages(driver);
		hotel.verifyAtLeastOneSearchResultDisplayed();
	}

	@When("I select a hotel from the search results and click on its Reservation button")
	public void i_select_a_hotel_from_the_search_results_and_click_on_its_reservation_button() {
		HotelPages hotel = new HotelPages(driver);
		hotel.clickOnHotelFromSearchResults();
		
	}

	@Then("site redirects to the Hotel offer details page")
	public void site_redirects_to_the_hotel_offer_details_page() {
		HotelPages hotel = new HotelPages(driver);
        hotel.verifySiteRedirectsToOfferDetailsInNewTab();
	
	}

	@Then("I check atleast one hotel offer is displayed on the page")
	public void i_check_atleast_one_hotel_offer_is_displayed_on_the_page() {
		HotelPages hotel = new HotelPages(driver);
        hotel.veriftAtLeastOneHotelOfferIsDisplayed();
	}

	@After
    public void teardown() {
        driver.quit();
    }

}
