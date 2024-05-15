@LoginFeature @Regression
Feature: Login feature

  @R001 @Smoke
  Scenario: R001 - Valid login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 - Login with invalid cred
    Given I open Login page
    When I enter '<login>' ito input Login in Login page
    And I enter '<password>' into input Password in Login page
    And I click button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password        |
      | qaauto          | not_valid_login |
      | not_valid_login | qaauto          |