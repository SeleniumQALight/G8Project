@PBFeature @Regression
Feature: PB feature

  @R003
  Scenario Outline: R005 checking the exchange rate
    Given I open page PB
    When I getting a course '<currency>' the site
    And I getting a course '<currency>' via API
    Then I compare course '<currency>' via API and site

    Examples:
      | currency |
      | USD      |
      | EUR      |