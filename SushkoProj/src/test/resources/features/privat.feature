Feature: Privat feature

  Scenario Outline: R005 Compare the exchange rate with API and UI
    Given I get the exchange rate for '<currency>' with API
    When I open the main page
    And I get exchange rate for '<currency>' with UI
    Then I compare the received data for '<currency>'

    Examples:
      | currency |
      | EUR      |
      | USD      |
