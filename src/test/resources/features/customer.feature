Feature: Customer Management


  Scenario: Create a new customer
    Given I have the following customer data
    When I send a request to create the customer
    Then the customer should be created successfully

  Scenario: Get all customers
    When I send a request to get all customers
    Then I should receive a list of customers

  Scenario: Get customers by ID
    When I send a request to get the customer by ID
    Then I should receive a list of customers

  Scenario: Update a customer
    Given I have the following customer data
    When I send a request to update the customer
    Then the customer should be updated successfully

  Scenario: Delete a customer by ID
    When I send a request to delete the customer by ID
    Then the customer should be deleted successfully
