@loan
Feature: Request Loan

  @smoke @regression
  Scenario: A loan the customer can afford is approved
    Given user is logged into Parabank
    When user requests a loan of "100" with down payment "50" from the first account
    Then the loan status should be "Approved"
    And a new loan account number should be shown

  @regression @negative
  Scenario: A down payment larger than the balance is denied
    Given user is logged into Parabank
    When user requests a loan of "100000" with down payment "99999" from the first account
    Then the loan status should be "Denied"
    And the message "You do not have sufficient funds for the given down payment." should be shown
