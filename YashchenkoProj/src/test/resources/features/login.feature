@LoginFeature @Regression
Feature: Login feature

  @R001 @Smoke
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Login with invalid credentials
    Given I open Login page
    When I enter '<login>' into input Login on Login page
    And I enter '<password>' into input Password on Login page
    And I click on button SignIn on Login page
    Then I see invalid alert message with text 'Invalid username/password.'

    Examples:
      | login           | password       |
      | qaauto          | not_valid_pass |
      | not_valid_login | 123456qwerty   |
      | qaauto          | 123456qwerty   |