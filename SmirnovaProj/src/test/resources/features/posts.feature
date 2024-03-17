@PostsFeature @Regression
Feature: Posts feature
  Background:
    Given I open Home page as 'default' user and 'default' password

  @R003 @Smoke
  Scenario Outline: R003 Check number of posts on UI
    Given I create <numberOfPosts> posts via API for 'default' user and 'default' password
      | title  | Post by API      |
      | body   | Body post by API |
      | select | One Person       |
    When I click on MyProfile button
    Then I redirect to MyProfile page
    And I see <numberOfPosts> posts in Posts lists on MyProfile page
    Examples:
      | numberOfPosts |
      | 2             |

