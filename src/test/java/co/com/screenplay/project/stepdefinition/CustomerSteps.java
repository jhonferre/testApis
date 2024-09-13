package co.com.screenplay.project.stepdefinition;


import co.com.screenplay.project.exceptions.UserAssertionErros;
import co.com.screenplay.project.models.CustomerData;
import co.com.screenplay.project.tasks.CreateCustomer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.util.EnvironmentVariables;

import static co.com.screenplay.project.exceptions.UserAssertionErros.STATUS_CODE_NOT_CORRECT;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CustomerSteps {
    private static final String DEFAULT_ENVIRONMENT = "restapi.baseurl";
    private static final String DEFAULT_PATH = "https://reqres.in/api";
    private EnvironmentVariables environmentVariables;
    private final CustomerData customerData = new CustomerData();

    @Given("I have the following customer data")
    public void iHaveTheFollowingCustomerData() {
        String theRestApiBaseUrl = environmentVariables.optionalProperty(DEFAULT_ENVIRONMENT)
                .orElse(DEFAULT_PATH);
        theActorCalled("actor").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("I send a request to create the customer")
    public void iSendARequestToCreateTheCustomer() {
        theActorInTheSpotlight().attemptsTo(CreateCustomer.withData(customerData));
    }

    @Then("the customer should be created successfully")
    public void theCustomerShouldBeCreatedSuccessfully() {
        theActorInTheSpotlight().should(
                seeThatResponse("Status code should be correct",
                        response -> response.statusCode(201)) // Código de estado 201 (Created)
                        .orComplainWith(UserAssertionErros.class, STATUS_CODE_NOT_CORRECT)
        );
    }

    @When("I send a request to get all customers")
    public void iSendARequestToGetAllCustomers() {
        String theRestApiBaseUrl = environmentVariables.optionalProperty(DEFAULT_ENVIRONMENT)
                .orElse(DEFAULT_PATH);
        theActorCalled("actor").whoCan(CallAnApi.at(theRestApiBaseUrl));
        theActorInTheSpotlight().attemptsTo(Get.resource("/customers"));
    }

    @Then("I should receive a list of customers")
    public void iShouldReceiveAListOfCustomers() {
        theActorInTheSpotlight().should(
                seeThatResponse("Status code should be correct",
                        response -> response.statusCode(200))
                        .orComplainWith(UserAssertionErros.class, STATUS_CODE_NOT_CORRECT)
        );
    }
}
