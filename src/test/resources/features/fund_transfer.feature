@transfer
Feature: Fund Transfer

  @smoke @regression
  Scenario: Transfer money between two own accounts
    Given user is logged into Parabank
    And user has a second account
    And the account balances are noted
    When user transfers "25" from the first account to the second account
    Then the message "Transfer Complete!" should be shown
    And the confirmation should show "$25.00" from the first account to the second account
    And the first account balance should be 25.00 lower
    And the second account balance should be 25.00 higher
