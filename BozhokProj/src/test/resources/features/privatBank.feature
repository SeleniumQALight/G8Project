@PrivatBankFeature @Regression
Feature: PrivatBank feature

  Background:
    Given I open Home page PrivatBank
    When I save exchange rates from UI

  @R005
  Scenario: R005 Compare currency exchange rates from API with UI
    Given I send request to PrivatBank API


    Examples:
      | EUR |
      | USD |
