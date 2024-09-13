package co.com.screenplay.project.stepdefinition;

import co.com.screenplay.project.exceptions.UserAssertionErrors;
import co.com.screenplay.project.models.CustomerData;
import co.com.screenplay.project.models.SharedData;
import co.com.screenplay.project.questions.CustomerResponse;
import co.com.screenplay.project.questions.ResponseStatusCode;
import co.com.screenplay.project.tasks.CreateCustomer;
import co.com.screenplay.project.tasks.DeleteCustomer;
import co.com.screenplay.project.tasks.UpdateCustomer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.screenplay.project.exceptions.UserAssertionErrors.*;
import static co.com.screenplay.project.models.SharedData.setCustomerId;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

public class CustomerSteps {
    private CustomerData customerData;

    @Given("I have the following customer data")
    public void iHaveTheFollowingCustomerData() {
        customerData = new CustomerData();
    }

    @When("I send a request to create the customer")
    public void iSendARequestToCreateTheCustomer() {
        theActorInTheSpotlight().attemptsTo(CreateCustomer.withData(customerData));
    }

    @Then("the customer should be created successfully")
    public void theCustomerShouldBeCreatedSuccessfully() {
        theActorInTheSpotlight().should(seeThat("Status code should be correct",
                        ResponseStatusCode.was(), is(201))
                .orComplainWith(UserAssertionErrors.class, USER_NOT_CREATED));
        theActorInTheSpotlight().should(seeThatResponse("User details should be correct",
                response -> CustomerResponse.matches(customerData).answeredBy(theActorInTheSpotlight()))
                .orComplainWith(UserAssertionErrors.class, USER_DETAIL_NOT_CORRECT));

        setCustomerId(CustomerResponse.extractCustomerId());
    }

    @When("I send a request to get all customers")
    public void iSendARequestToGetAllCustomers() {
        theActorInTheSpotlight().recall("customerId");
        theActorInTheSpotlight().attemptsTo(Get.resource("/customers"));
    }

    @When("I send a request to get the customer by ID")
    public void iSendARequestToGetCustomerById() {
        String customerId = SharedData.getCustomerId().toString();
        theActorInTheSpotlight().attemptsTo(
                Get.resource("/customers/" + customerId)
        );
    }

    @Then("I should receive a list of customers")
    public void iShouldReceiveAListOfCustomers() {
        theActorInTheSpotlight().should(seeThat("Status code should be correct",
                        ResponseStatusCode.was(), is(200))
                        .orComplainWith(UserAssertionErrors.class, USER_NOT_SAVED));
    }

    @When("I send a request to update the customer")
    public void iSendARequestToUpdateTheCustomer() {
        String customerId = SharedData.getCustomerId().toString();
        theActorInTheSpotlight().attemptsTo(
                UpdateCustomer.withData(customerId, customerData));
    }

    @Then("the customer should be updated successfully")
    public void theCustomerShouldBeUpdatedSuccessfully() {
        theActorInTheSpotlight().should(seeThat("Status code should be correct",
                        ResponseStatusCode.was(), is(200))
                        .orComplainWith(UserAssertionErrors.class, USER_NOT_UPDATED));
        theActorInTheSpotlight().should(seeThatResponse("User details should be correct",
                response -> CustomerResponse.matches(customerData).answeredBy(theActorInTheSpotlight()))
                .orComplainWith(UserAssertionErrors.class, USER_DETAIL_NOT_CORRECT));
    }

    @When("I send a request to delete the customer by ID")
    public void iSendARequestToDeleteTheCustomerByID() {
        String customerId = SharedData.getCustomerId().toString();
        theActorInTheSpotlight().attemptsTo(
                DeleteCustomer.byId(customerId));
    }

    @Then("the customer should be deleted successfully")
    public void theCustomerShouldBeDeletedSuccessfully() {
        theActorInTheSpotlight().should(seeThat("Status code should be correct",
                        ResponseStatusCode.was(), is(200))
                        .orComplainWith(UserAssertionErrors.class, USER_NOT_DELETED));
    }

}
