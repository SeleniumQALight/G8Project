@RegistrationFeature @Regression
Feature: Registration feature

  @R004 @smoke
    Scenario Outline: R004 Validation messages on registration form
    Given I open Login page
    When I enter '<username>' into input Username on Register in Login page
    When I enter '<email>' into input Email on Register in Login page
    When I enter '<password>' into input Password on Register in Login page
    Then I see error message with text '<error_message>'

    Examples:

      | username                        | email          | password                                             | error_message                                                                                                                   |
      | tanyatest01                     | ttest@test.com | tb                                                   | Password must be at least 12 characters.                                                                                        |
      | tanyatest01                     | test           | Tanya123@12345                                       | You must provide a valid email address.                                                                                         |
      | test1234567891234567testtanya12 | ttest@test.com | Tanya123@12345                                       | Username cannot exceed 30 characters.                                                                                           |
      | tanyatest01                     | ttest@test.com | test1234567891234567testtanya12124565gfjfk45545gfhfd | Password cannot exceed 50 characters.                                                                                           |
      | tanyatest@#!                    | tanya          | test                                                 | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |



