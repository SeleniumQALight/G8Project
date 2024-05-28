Feature: Login Feature

  @R003 @Smoke
  Scenario Outline: R003 Validate registration form
    Given I open Login page
    When I enter '<userName>' into input UserName in RegistrationForm
    And I enter '<email>' into input Email in Login page
    And I enter '<password>' into input Password in RegistrationForm
    Then I see alert '<validationMessage>'



    Examples:
      | userName | email              | password                                                 | validationMessage                                                                |
      | taras    | tr                 | tr                                                       | You must provide a valid email address.;Password must be at least 12 characters. |
      | taras    | rtytrrrr@gmail.com | 1                                                        | Password must be at least 12 characters.                                         |
      | mariia   | rtytrrrr@gmail.com | 11111111111111111111111111111111111111111111111111111111 | Password cannot exceed 50 characters.                                            |
      | мАРИИЯ   | rtytrrrr@gmail.com | VALID_PASSWORD                                           | Username can only contain letters and numbers.                                   |