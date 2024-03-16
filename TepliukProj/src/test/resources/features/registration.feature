@RegistrationFeature @Regression
Feature: Registration feature

  @R004 @Smoke
  Scenario Outline: R004 Validation messages on registration form
    Given I open Login page
    When I enter '<username>' into input Username of Registration form on Login page
    And I enter '<email>' into input Email of Registration form on Login page
    And I enter '<password>' into input Password of Registration form on Login page
    Then I see '<error_message>' error message

    Examples:
      | username                        | email            | password                                            | error_message                                                                                                                   |
      | atepliuk                        | bd               | 123456qwerty                                        | You must provide a valid email address.                                                                                         |
      | atepliuk                        | qaauto@gmail.com | aa                                                  | Password must be at least 12 characters.                                                                                        |
      | atepliuk                        | qaauto@gmail.com | 111111111111111111111111111111111111111111111111111 | Password cannot exceed 50 characters.                                                                                           |
      | Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa | qaauto@gmail.com | 123456qwerty                                        | Username cannot exceed 30 characters.                                                                                           |
      | проба                           | проба            | проба                                               | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |