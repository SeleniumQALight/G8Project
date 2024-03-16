@PrivatBankFeature @Regression
Feature: PrivatBank feature

  Background:
    Given I open Home page PrivatBank

  @R005
  Scenario Outline: R005 Compare currency exchange rates from API with UI
    Given I send request to PrivatBank API for '<currency>' currency
    When I save exchange rates from UI for '<currency>' currency



    Examples:
      | currency |
      | USD      |
      | EUR      |
