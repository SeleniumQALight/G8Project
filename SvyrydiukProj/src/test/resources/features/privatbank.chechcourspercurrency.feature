@PrivatbankFeature @Regression
Feature: Privatbank feature


  @R005
  Scenario Outline: R006 Check cours for each currency
    Given I get the cours for "<currency>" from API
    When I get the cours for "<currency>" from UI
    Then I compare the cours from API and UI


    Examples:
      | currency |
      | EUR      |
      | USD      |
