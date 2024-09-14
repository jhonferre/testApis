Feature: Customer Management


  Scenario: Create a new customer
    Given I generate valid customer data
    When I send a request to create the customer
    Then the customer should be created successfully

  Scenario Outline: Fail to create a customer with invalid or incomplete data
    Given I have the following customer data
      | identityDocument   | documentType   | fullName   | dateOfBirth   |
      | <identityDocument> | <documentType> | <fullName> | <dateOfBirth> |
    When I send a request to create the customer
    And the response should include an error message "<errorMessage>"

    Examples:
      | identityDocument | documentType | fullName      | dateOfBirth | errorMessage                                                      |
      | 123456789        | NATIONAL_ID  |               | 1980-01-01  | El número de documento ya existe en otro cliente. Debe ser único. |
      | 1000000000       | NATIONAL_ID  | Juan Perez123 | 1980-01-01  | El número de documento debe ser un número entre 1 y 999999999.    |


  Scenario: Get customers by ID
    When I send a request to get the customer by ID "6"
    Then I should receive a customer with valid details

  Scenario: Get customers by ID not found
    When I send a request to get the customer by ID "9999"
    Then I should receive a 404 NOT FOUND status

  Scenario: Update a customer
    Given I generate valid customer data
    When I send a request to update the customer
    Then the customer should be updated successfully
