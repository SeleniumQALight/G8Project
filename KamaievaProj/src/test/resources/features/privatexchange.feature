Feature: PrivatBank feature


  @R007
  Scenario Outline: R007 Compare API and Website Exchange Rates for <currency>
    Given I get exchange rates for "<currency>" currency from API
    When I open PrivateBank main web page
    Then I see exchange rates for "<currency>" currency on PrivateBank main web page
    And I compare exchange rates from API with exchange rates from web page

    Examples:
      | currency |
      | USD      |
      | EUR      |
