@RegistrationFeature @Regression
Feature: Login feature

  @R004 @Smoke
  Scenario Outline: R004 Validation Messages for Login page
    Given I open Login page
    When I enter '<username>' into input Username Register in Login page
    When I enter '<email>' into input Email Register in Login page
    When I enter '<password>' into input Password Register in Login page
    Then I see error message with text '<error_message>'


    Examples:
      | username             | email     | password                                            | error_message                                                                                                                   |
      | TC001testusertime    | tr        | tr                                                  | You must provide a valid email address.;Password must be at least 12 characters.                                                |
      | taras                | tr@tr.com | tr                                                  | Password must be at least 12 characters.                                                                                        |
      | TC001                | no        | abcdefghijklmpqrstuv37776543werqwrtrrrr12===^%^###@ | You must provide a valid email address.;Password cannot exceed 50 characters.                                                   |
      | 1$%&/()=?*<abqrstuv> | @.gmail   | 1234567890                                          | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |