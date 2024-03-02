@PostsFeature @Regression
Feature:  Posts feature

Background:
    Given I open Home page as 'default' user with 'default' password


  @R003 @Smoke
  Scenario Outline: R003 Check number of posts on UI
    Given I create <numberOfPosts> new posts via API 'default' user and 'default' password
      | title  | post by API |
      | body   | Body post   |
      | select | One person  |
    When I click on MyProfile button
    Then I redirect to MyProfile page
    Then I see '<numberOfPosts>' posts in Posts lists on MyProfile page



    Examples:
      | numberOfPosts |  |
      | 2             |  |








