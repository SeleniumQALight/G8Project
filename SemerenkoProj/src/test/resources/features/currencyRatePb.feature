Feature: Login Validation feature

  @R005
  Scenario Outline: R005 Compare Api currency rate with UI
    Given I send request for '<currency>' rates to API
    When I open PrivatBank page
    Then I take currency rate from page by '<currency>' and compare values with API
    Examples:
      | currency |
      | usd      |
      | eur      |

