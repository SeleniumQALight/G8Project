@LoginFeature @Regression
Feature: Registration feature

@R004
Scenario Outline: R004 Check validation massages for Registration form
  Given  I open LoginPage
  When I enter '<userName>' into input UserName in Registration form
  When I enter '<email>' into input Email in Registration form
  And I enter '<password>' into input Password in Registration form
  And I click on button Sign Up in Registration form
  Then I see alert message with text '<expectedMessages>' in Registration form

  Examples:
    | userName     | password     | email            | expectedMessages                               |
    | vladyslava03 | sv           | test03@gmail.com | Password must be at least 12 characters.       |
    | sv           | 123456qwerty | test03@gmail.com | Username must be at least 3 characters.        |
    | 36@)*@^      | 123456qwerty | test03@gmail.com | Username can only contain letters and numbers. |
    | vladyslava03 | 123456qwerty | test 03          | You must provide a valid email address.        |
