@openaccount
Feature: Open New Account

  @smoke @regression
  Scenario Outline: Open a new <type> account
    Given user is logged into Parabank
    When user opens a new "<type>" account funded from the first account
    Then the message "Account Opened!" should be shown
    And the new account should be listed in Accounts Overview

    Examples:
      | type     |
      | SAVINGS  |
      | CHECKING |
