@LoginFeature @Regression
Feature: Login feature

  @R001 @Smoke
  Scenario: R001 Valid login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on the Home page

  @R002
  Scenario Outline: R002 Invalid login
    Given I open Login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input Password in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password       |
      | gaauto          | not_valid_pass |
      | not_valid_login | 123456qwerty   |

