@PostsFeature @Regression
Feature: Posts feature

  Background:
    Given I open Home page as 'default' user with 'default' password

  @R003 @Smoke
    @deleteAllPostsForDefaultUser
  Scenario Outline: R003 Check number of posts on UI
    Given I create '<numberOfPosts>' new posts via API for 'default' user and 'default' password
      | title  | Post by API |
      | body   | Post by API |
      | select | One Person  |
    When I click on MyProfileButton
    Then I redirect to MyProfilePage page
    And I see '<numberOfPosts>' posts in Posts lists on MyProfile page


    Examples:
      | numberOfPosts |
      | 2             |
