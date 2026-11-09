@billpay
Feature: Bill Payment

  @smoke @regression
  Scenario: Pay a bill to a payee
    Given user is logged into Parabank
    And the account balances are noted
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
    And the first account balance should be 50.00 lower

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
