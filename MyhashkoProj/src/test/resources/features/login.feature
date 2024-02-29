@LoginFeature @Regression
Feature: Login feature

  @R001 @Smoke
  Scenario: R001 Valid login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on Home page

    @R002
  Scenario Outline: R002 Login with invalid cred
    Given I open Login page
    When I enter '<login>' into input login in Login page
    And I enter '<password>' into input password in Login page
    And I click on Button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'


    Examples:
      | login           | password       |
      | qaauto          | not_valid_pass |
      | not_valid_login | 123456qwerty   |
