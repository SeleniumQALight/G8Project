Feature: Login feature

  Scenario: R001 Valid Login
    Given I open Login pgae
    When I login with valid credentials
    Then I see avatar on Home page
