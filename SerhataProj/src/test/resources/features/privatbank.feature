@PrivatbankFeature
Feature: privatbank feature

  @R005
  Scenario Outline: R004 - Compare currency rates on UI and API
    Given I receive '<currency>' rate via API
    When I open Main Privatbank page
    And I see '<currency>' rate on the page
    Then I compare '<currency>' rates on UI and API


    Examples:
      | currency |
      | EUR      |
      | USD      |