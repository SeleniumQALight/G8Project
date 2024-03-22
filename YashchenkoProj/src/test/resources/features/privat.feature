@PrivatBankFeature
Feature: PrivatBank feature

  @R008
  Scenario Outline: R008 PrivatBank currencies test
    Given I receive current exchange rate from API for needed '<currency>'
    When I open PrivatBank home page
    Then I see correct rate for '<currency>' on UI

    Examples:
      | currency |
      | USD      |
      | EUR      |