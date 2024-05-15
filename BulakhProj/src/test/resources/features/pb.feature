@PrivatbankFeature @Regression
Feature: PrivatBank feature


  @R005
  Scenario Outline: R005 Check course for each currency
    Given I get course for "<currency>" from Api
    When I open main web page PrivateBank
    Then I check course for "<currency>" from web page
    And I compare the course from API and web page


    Examples:
      | currency |
      | EUR      |
      | USD      |
