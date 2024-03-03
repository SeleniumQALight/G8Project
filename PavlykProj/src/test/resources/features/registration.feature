@PostsFeature @Regression
Feature: Registration feature

  @R004
  Scenario Outline: R005 Validation messages in Registration form
    Given I open Login page
    When I enter '<userName>' into input Username in Registration form in Login page
    And I enter '<email>' into input Email in Registration form in Login page
    And I enter '<password>' into input Password in Registration form in Login page
    Then I see warning messages '<expectedMessage>' in the  fields.


    Examples:
      | userName | email              | password     | expectedMessage                                                                  |
      | myName   | tr                 | tr           | You must provide a valid email address.;Password must be at least 12 characters. |
      | myName   | tr@tr.cm           | tr           | Password must be at least 12 characters.                                         |
      | myName   | rtytrrrr@gmail.com | tr_x30       | Password cannot exceed 50 characters.                                            |
      | майName  | rtytrrrr@gmail.com | 123456qwerty | Username can only contain letters and numbers.                                   |