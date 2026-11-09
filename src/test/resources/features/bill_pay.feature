@billpay
Feature: Bill Payment

  @smoke @regression
  Scenario: Pay a bill to a payee
    Given user is logged into Parabank
    When user pays a bill from the first account with:
      | payeeName     | Electric Company |
      | address       | 1 Power Street   |
      | city          | New York         |
      | state         | NY               |
      | zipCode       | 10001            |
      | phone         | 2125550100       |
      | account       | 54321            |
      | verifyAccount | 54321            |
      | amount        | 50               |
    Then the message "Bill Payment Complete" should be shown
    And the bill payment confirmation should show payee "Electric Company" and amount "$50.00"

  @regression @negative
  Scenario: Bill payment with mismatched account numbers is rejected
    Given user is logged into Parabank
    When user pays a bill from the first account with:
      | payeeName     | Electric Company |
      | address       | 1 Power Street   |
      | city          | New York         |
      | state         | NY               |
      | zipCode       | 10001            |
      | phone         | 2125550100       |
      | account       | 54321            |
      | verifyAccount | 99999            |
      | amount        | 50               |
    Then the message "The account numbers do not match." should be shown
    And the message "Bill Payment Complete" should not be shown

  # ParaBank confirms the payment but never debits the account or records a transaction.
  @known-defect
  Scenario: A bill payment is debited from the account
    Given user is logged into Parabank
    And the account balances are noted
    When user pays a bill from the first account with:
      | payeeName     | Water Company    |
      | address       | 2 River Road     |
      | city          | New York         |
      | state         | NY               |
      | zipCode       | 10001            |
      | phone         | 2125550101       |
      | account       | 65432            |
      | verifyAccount | 65432            |
      | amount        | 40               |
    Then the message "Bill Payment Complete" should be shown
    And the first account's transactions for amount "40" should include "Bill Payment to Water Company"
    And the first account balance should be 40.00 lower
