@PrivatBankFeature @Regression
Feature: PrivatBank

  @R005 @Smoke
  Scenario Outline: R005 Compare rate of currency witch get by Api and Ui
    Given I save rate from Api for <currency>
    And I save rate from Ui for <currency>
    Then I compare rates for <currency>
    Examples:
      | currency |
      | PLN      |
      | EUR      |
      | USD      |
