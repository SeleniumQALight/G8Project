@LoginValidationFeature @Regression
Feature: Login Validation feature

  @R004
  Scenario Outline: R004 Check validation messages
    Given I open Login page
    When I enter '<login>' into input Registration Login in Login page
    And I enter '<email>' into input Registration Email in login page
    And I enter '<password>' into input Registration Password in Login page
    Then I see '<validationMessages>' in registration fields
    Examples:
      | login | email  | password     | validationMessages                                                                                                            |
      | 12    | qqqqqq | 123321S      | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | 12    | qqqq   | 123456qwerty | Username must be at least 3 characters.;You must provide a valid email address.                                          |

