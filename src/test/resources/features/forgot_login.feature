@forgotlogin
Feature: Forgot Login Info

  @regression
  Scenario: Customer finds their login info with name, address and SSN
    Given a registered ParaBank customer
    And user clicks logout
    When user looks up login info with the registered SSN
    Then the message "Your login information was located successfully." should be shown
    And the registered username should be shown

  @regression @negative
  Scenario: Unknown customer details are not found
    Given a registered ParaBank customer
    And user clicks logout
    When user looks up login info with SSN "000-00-0000"
    Then the message "The customer information provided could not be found." should be shown
