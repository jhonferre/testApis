package co.com.screenplay.project.stepdefinition;

import co.com.screenplay.project.models.CustomerContext;
import co.com.screenplay.project.models.CustomerData;
import co.com.screenplay.project.models.SharedData;
import co.com.screenplay.project.questions.CustomerResponse;
import co.com.screenplay.project.tasks.*;
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

    @Given("I have the following customer data")
    public void iHaveTheFollowingCustomerData() {
        customerData = new CustomerData();
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

    @When("I send a request to get all customers")
    public void iSendARequestToGetAllCustomers() {
        theActorInTheSpotlight().attemptsTo(GetAllCustomers.fromApi());
    }

    @When("I send a request to get the customer by ID")
    public void iSendARequestToGetCustomerById() {
        String customerId = SharedData.getCustomerId();
        theActorInTheSpotlight().attemptsTo(GetCustomerById.withId(customerId));
    }

    @Then("I should receive a list of customers")
    public void iShouldReceiveAListOfCustomers() {
        validateResponseStatus(200, USER_NOT_SAVED);
        validateCustomerList();
    }

    @Then("I should receive a customer with valid details")
    public void iShouldReceiveACustomerWithValidDetails() {
        validateResponseStatus(200, USER_NOT_SAVED);
        validateCustomerDetails();
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

    @When("I send a request to delete the customer by ID")
    public void iSendARequestToDeleteTheCustomerByID() {
        customerId = SharedData.getCustomerId();
        theActorInTheSpotlight().attemptsTo(DeleteCustomer.byId(customerId));
    }

    @Then("the customer should be deleted successfully")
    public void theCustomerShouldBeDeletedSuccessfully() {
        validateResponseStatus(200, USER_NOT_DELETED);
    }
}
