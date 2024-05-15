@PostsFeature @Regression
Feature: Registration feature


  @R004 @Smoke
  Scenario Outline: R004 Check errors messages for invalid data when user tries to register
    Given I open Login page
    When I enter '<userName>' into input UserNameRegistration in Login page
    And I enter '<email>' into input EmailRegistration in Login page
    And I enter '<password>' into input PasswordRegistration in Login page
    And I click on button Register in Login page
    Then I see error message '<errorMessage>' in Login page




    Examples:

      | userName | email    | password     | errorMessage                                                                                                             |
      | 12       | 12@12.ua | 123456qwerty | Username must be at least 3 characters.                                                                                  |
      | 123      | 12.ua    | 123456qwerty | You must provide a valid email address.                                                                                  |
      | 123      | 12@12.ua | 12           | Password must be at least 12 characters.                                                                                 |
      | 12       | 12       | 12           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
