@findtransactions
Feature: Find Transactions

  @regression
  Scenario: A transfer can be found by its amount
    Given user is logged into Parabank
    And user has a second account
    And user transfers "17.25" from the first account to the second account
    When user searches the first account's transactions for amount "17.25"
    Then every result should mention "Funds Transfer Sent"

  @regression
  Scenario: Transactions can be found by date range
    Given user is logged into Parabank
    And user has a second account
    When user searches the first account's transactions from "01-01-2020" to "12-31-2035"
    Then at least 1 transaction should be listed
