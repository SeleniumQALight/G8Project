Feature: Login Feature

  Background:

    Given I open Home Page as 'default' user and 'default' password

  @R004
    @deleteAllPostsForDefaultUser
  Scenario Outline:
    Given  I create <numberOfPosts> new posts via API for 'default' user and 'default' password

      | title  | Post by API |
      | body   | body post   |
      | select | One Person  |

    When I click on button MyProfile On Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> posts in Post list on Profile Page



    Examples:
      | numberOfPosts |
      | 2             |