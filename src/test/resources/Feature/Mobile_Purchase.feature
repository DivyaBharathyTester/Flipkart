#Author: your.email@your.domain.com
Feature: Mobile Purchase using flipkart

  Scenario: Buy Mobile
    Given User launches flipkart application
    And User login using credentials
    When Navigates into the page and choses the mobile
    And Proceed to payment
    Then Message to be displayed as Order has been placed.
