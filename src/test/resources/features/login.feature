@login
Feature: Login
  A registered customer can log in; wrong or missing credentials are rejected.

  @smoke @regression
  Scenario: Successful login with valid credentials
    Given a registered ParaBank customer
    And user clicks logout
    And user is on the ParaBank home page
    When user logs in with the registered credentials
    Then user should be logged in and see "Accounts Overview"

  @regression @negative
  Scenario Outline: Login is rejected - <case>
    Given user is on the ParaBank home page
    When user logs in with username "<username>" and password "<password>"
    Then the login error "<message>" should be shown
    And user should not be logged in

    Examples:
      | case               | username        | password    | message                                         |
      | unknown user       | nobody_rb_9x7q  | wrongPass1  | The username and password could not be verified. |
      | empty credentials  |                 |             | Please enter a username and password.           |
