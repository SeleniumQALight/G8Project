Feature: Currency Exchange Rate Comparison


  Scenario Outline: Compare currency exchange rates for <currency> from API and UI

    Given I save exchange rate for buy and sale for "<currency>" from the PrivatBank API response
    When I open the PrivatBank website
    And I save exchange rate for buy and sale for "<currency>" from UI
    Then I compare the API exchange rate with the UI exchange rate

    Examples:
      | currency |
      | USD      |
      | EUR      |