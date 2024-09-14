package co.com.screenplay.project.stepdefinition;

import co.com.screenplay.project.models.CustomerContext;
import co.com.screenplay.project.models.CustomerData;
import co.com.screenplay.project.models.SharedData;
import co.com.screenplay.project.questions.CustomerResponse;
import co.com.screenplay.project.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.screenplay.project.exceptions.UserAssertionErrors.*;
import static co.com.screenplay.project.models.SharedData.setCustomerId;
import static co.com.screenplay.project.utils.CustomerValidation.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CustomerSteps {

    private CustomerData customerData;
    private String customerId;

    @Given("I generate valid customer data")
    public void iGenerateValidCustomerData() {
        customerData = new CustomerData();
        CustomerContext.setCustomerData(customerData);
    }

    @Given("I have the following customer data")
    public void iHaveTheFollowingCustomerData(DataTable data) {
        customerData = new CustomerData();
        data.asMaps().forEach(row -> {
            customerData.setIdentityDocument(Integer.parseInt(row.get("identityDocument")));
            customerData.setDocumentType(row.get("documentType"));
            customerData.setFullName(row.get("fullName"));
            customerData.setDateOfBirth(row.get("dateOfBirth"));
        });
        CustomerContext.setCustomerData(customerData);
    }

    @When("I send a request to create the customer")
    public void iSendARequestToCreateTheCustomer() {
        theActorInTheSpotlight().attemptsTo(CreateCustomer.withData(customerData));
    }

    @Then("the customer should be created successfully")
    public void theCustomerShouldBeCreatedSuccessfully() {
        validateResponseStatus(201, USER_NOT_CREATED);
        validateCustomerDetails();
        customerId = String.valueOf(CustomerResponse.extractCustomerId());
        setCustomerId(customerId);
    }

    @Then("the response should include an error message {string}")
    public void theResponseShouldIncludeAnErrorMessage(String expectedErrorMessage) {
        validateResponseStatus(400, CUSTOMER_CREATION_FAILED);
        validateErrorMessage(expectedErrorMessage);
    }

    @When("I send a request to get the customer by ID {string}")
    public void iSendARequestToGetCustomerById(String customerId) {
        theActorInTheSpotlight().remember("customerId", customerId);
        theActorInTheSpotlight().attemptsTo(GetCustomerById.withId(customerId));
    }

    @Then("I should receive a 404 NOT FOUND status")
    public void iShouldReceiveA404NotFoundStatus() {
        validateResponseStatus(404, CUSTOMER_NOT_FOUND);
    }

    @Then("I should receive a customer with valid details")
    public void iShouldReceiveACustomerWithValidDetails() {
        validateResponseStatus(200, USER_NOT_SAVED);
        validateCustomerByIdResponse(Integer.parseInt(theActorInTheSpotlight().recall("customerId")));
    }

    @When("I send a request to update the customer")
    public void iSendARequestToUpdateTheCustomer() {
        customerId = SharedData.getCustomerId();
        theActorInTheSpotlight().attemptsTo(UpdateCustomer.withData(customerId, customerData));
    }

    @Then("the customer should be updated successfully")
    public void theCustomerShouldBeUpdatedSuccessfully() {
        validateResponseStatus(200, USER_NOT_UPDATED);
        validateCustomerDetails();
    }
}
