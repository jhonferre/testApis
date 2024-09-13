Feature: Customer Management

  Scenario: Create a new customer
    Given I have the following customer data
    When I send a request to create the customer
    Then the customer should be created successfully

  Scenario: Get all customers
    When I send a request to get all customers
    Then I should receive a list of customers
