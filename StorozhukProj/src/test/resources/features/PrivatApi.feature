@ExchangeFeature
Feature: Exchange Rate feature

  @R004 @Smoke
  Scenario Outline: Compare exchange rate API to UI
    Given I have '<currency>' currency code
    When  I open Privat24 website
    Then  I take a buy and sell exchange rates for the currency from website
    And   I take a buy and sell exchange rates for all currencies from API
    Then  The exchange rates are the same

    Examples:
      | currency |
      | EUR      |
      | USD      |

