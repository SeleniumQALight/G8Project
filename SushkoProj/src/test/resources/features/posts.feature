@PostsFeature @Regression
Feature: Posts feature

  Background:
    Given I open Home Page as 'default' user with 'default' password

    @R003 @Smoke
    @deleteAllPostsForDefaultUser
  Scenario Outline: R003 Check number of posts on UI
    Given I create '<numberOfPosts>' new posts via API for 'default' user and 'default' password
    | title  | Post by API - Sushko |
    | body   | Body post            |
    | select | One Person           |
    When I click on MyProfile button
    Then I redirect to MyProfile page
    And I see '<numberOfPosts>' posts in Post list on MyProfile page

    Examples:
    | numberOfPosts |
    | 2             |

