@RegistryFeature @Regression
Feature: Registry feature

  @R004 @Smoke
  Scenario Outline: R004 Registry with invalid cred
    Given I open Login page
    When I enter'<username>' into input Username in Login page
    And I enter '<password>' into input Password in Login page for registry
    And I enter '<email>' into input Email in Login page
    And I click on button SignUp in Login page
    Then I see error messages with text '<error_message>'

    Examples:
      | username                                                  | email     | password                                                  | error_message                                                                                                                   |
      | taras                                                     | tr        | tr                                                        | You must provide a valid email address.;Password must be at least 12 characters.                                                |
      | taras                                                     | tr@tr.com | tr                                                        | Password must be at least 12 characters.                                                                                        |
      | тест                                                      | тест      | тест                                                      | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |
      | test11111111111111111111111111111111155555555111112222222 | tr@tr.com | test11111111111111111111111111111111155555555111112222222 | Username cannot exceed 30 characters.;Password cannot exceed 50 characters.                                                     |
