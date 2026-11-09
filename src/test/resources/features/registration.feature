@registration
Feature: Registration

  @smoke @regression
  Scenario: Register a new customer
    Given user is on the registration page
    When user registers a new customer with a unique username
    Then the welcome message for the new username should be shown
    And the message "Your account was created successfully. You are now logged in." should be shown

  @regression @negative
  Scenario: Passwords that do not match are rejected
    Given user is on the registration page
    When user registers a new customer with confirm password "SomethingElse1"
    Then the message "Passwords did not match." should be shown
    And user should not be logged in

  @regression @negative
  Scenario: A username that already exists is rejected
    Given a registered ParaBank customer
    And user clicks logout
    And user is on the registration page
    When user registers again with the same username
    Then the message "This username already exists." should be shown
