Feature: Login feature


  Scenario: R001 Valid login
    Given I am on the login page
    When I enter valid credentials
    Then I see avatar on Home page