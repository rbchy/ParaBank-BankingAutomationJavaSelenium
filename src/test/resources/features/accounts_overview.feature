@overview
Feature: Accounts Overview

  @smoke @regression
  Scenario: Accounts overview lists every account and the right total
    Given user is logged into Parabank
    When user navigates to Accounts Overview page
    Then user should see at least 1 account with a balance
    And the Total row should equal the sum of the account balances
