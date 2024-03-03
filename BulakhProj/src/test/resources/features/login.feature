@LoginFeature @Regression
Feature:  Login feature


  @R001 @smoke
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on Home page

    @R002
  Scenario Outline: R002 Login with invalid cred
    Given I open Login page
    When I enter '<login>' into input login in Login page
    When I enter '<password>' into input Password in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'



      Examples:
        | login           | password       |
        | qaauto          | not_valid_pass |
        | not_valid_login | 123456qwerty   |






