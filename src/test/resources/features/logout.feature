@logout
Feature: Logout

  @smoke @regression
  Scenario: User logs out successfully
    Given user is logged into Parabank
    When user clicks logout
    Then user should be logged out successfully
