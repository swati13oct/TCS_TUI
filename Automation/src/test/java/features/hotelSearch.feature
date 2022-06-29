Feature: To search for hotels on TUI site

  @Offers
  Scenario: To perform hotel search in Brasil and check if offers are displayed
    Given I have launched the browser
    And I have navigated to TUI home page
    And I have selected country "Brasil"
    When I enter city name as "Rio" in Find a destination text box to search for hotel
    And I select the from "2022-07-26" and to dates "2022-07-28" in the calendar
    And I select the number of guests
    And I clicked on Search button
    Then site redirects to available hotels for the chosen dates
    And I check atleast one hotel is displayed in search results
    When I select a hotel from the search results and click on its Reservation button
    Then site redirects to the Hotel offer details page
    And I check atleast one hotel offer is displayed on the page
