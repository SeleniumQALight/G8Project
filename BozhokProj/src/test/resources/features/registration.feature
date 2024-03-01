@PostsFeature @Regression
Feature: Registration feature

  @R004
  Scenario Outline: R004 InValidMessageInRegistrationForm
    Given I open Login page
    When I enter '<userName>' in username field and '<email>' in email field and '<password>' in password field on Login page
    And I click on Register button on Login page
    Then I see '<numberOfMessage>' error message on Login page
    Examples:
      | userName | email           | password      | numberOfMessage |
      | tr       | tr              | tr            | 3               |
      | tr       | tr              | 1234567qwerty | 2               |
      | tr       | trt@example.com | tr            | 2               |
      | qaauto   | tr              | tr            | 2               |
      | qaauto   | trt@example.com | tr            | 1               |
      | qaauto   | tr              | 1234567qwerty | 1               |
      | tr       | trt@example.com | 1234567qwerty | 1               |