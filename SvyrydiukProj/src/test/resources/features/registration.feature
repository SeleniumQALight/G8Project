@RegistrationFeature @Regression
Feature: Registration feature


  @R004
  Scenario Outline: R004 Registration with invalid data
    Given I open Login page
    When I enter '<username>' into input UsernameRegistration in Login page
    And I enter '<email>' into input EmailRegistration in Login page
    And I enter '<password>' into input PasswordRegistration in Login page
    And I click on button SignUpForOurApp in Login page
    Then I see warning message with text '<Error message>'


    Examples:
      | username  | password     | email         | Error message                                                                                                            |
      | qa        | 123456qwerty | test@test.com | Username must be at least 3 characters.                                                                                  |
      | qaautonew | 123456qwerty | test          | You must provide a valid email address.                                                                                  |
      | qaautonew | 1            | test@test.com | Password must be at least 12 characters.                                                                                 |
      | qa        | 1            | te            | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
