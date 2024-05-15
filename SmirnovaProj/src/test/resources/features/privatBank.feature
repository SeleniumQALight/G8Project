Feature: PrivatBank feature

  Scenario Outline:
    Given I get the exchange rate for '<currency>' from PrivatBank using API
    When I open PrivatBank page
    Then I see the exchange rate for '<currency>' on the page
    And I compare the exchange rate from API with the exchange rate from the page
    Examples:
      | currency |
      | USD      |
      | EUR      |