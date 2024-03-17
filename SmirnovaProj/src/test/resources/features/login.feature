@LoginFeature @Regression
Feature: Login feature


  @R001 @Smoke
  Scenario: R001 Valid login
    Given I am on the login page
    When I enter valid credentials
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Invalid login
    Given I am on the login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input Password in Login page
    And I click on button Login in Login page
    Then I see error message with text 'Invalid username/password.'

    Examples:
      | login           | password       |
      | qaauto          | not_valid_pass |
      | not_valid_login | 123456qwerty   |
