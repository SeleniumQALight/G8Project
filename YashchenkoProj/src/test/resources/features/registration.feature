@RegistrationFeature @Regression
Feature: Registration feature

  //123123
  @R004 @Smoke
  Scenario Outline: R004 Check registration form error messages
    Given I open Login page
    When I enter '<login>' into registration input Login on Login page
    And I enter '<password>' into registration input Password on Login page
    And I enter '<email>' into registration input Email on Login page
    Then I see expected registration form '<errors>' messages

    Examples:
      | login          | password       | email | errors                                                                                  |
      | tim            | 123            | 123   | You must provide a valid email address.;Password must be at least 12 characters.        |
      | tim            | tim@gmail.com  | 123   | Password must be at least 12 characters.                                                |
      | test@gmail.com | test@gmail.com | 123   | Username can only contain letters and numbers.;Password must be at least 12 characters. |