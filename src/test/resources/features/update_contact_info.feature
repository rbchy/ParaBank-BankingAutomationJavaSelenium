@profile
Feature: Update Contact Info

  @smoke @regression
  Scenario: Update contact information and see it saved
    Given user is logged into Parabank
    When user navigates to Update Contact Info page
    And user updates contact info with:
      | firstName | Ranajit         |
      | lastName  | Chowdhury       |
      | address   | 200 Oak Avenue  |
      | city      | West Norriton   |
      | state     | PA              |
      | zipCode   | 19403           |
      | phone     | 2673425565      |
    And user clicks Update
    Then the message "Profile Updated" should be shown
    And reopening Update Contact Info should show the new values

  @regression @negative
  Scenario: First name is required
    Given user is logged into Parabank
    When user navigates to Update Contact Info page
    And user clears the first name
    And user clicks Update
    Then the message "First name is required." should be shown
    And the message "Profile Updated" should not be shown
