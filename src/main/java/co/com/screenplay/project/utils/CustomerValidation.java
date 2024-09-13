package co.com.screenplay.project.utils;

import co.com.screenplay.project.exceptions.UserAssertionErrors;
import co.com.screenplay.project.models.CustomerContext;
import co.com.screenplay.project.models.CustomerData;
import co.com.screenplay.project.questions.CustomerListResponse;
import co.com.screenplay.project.questions.CustomerResponse;
import co.com.screenplay.project.questions.ResponseStatusCode;
import org.hamcrest.Matchers;

import static co.com.screenplay.project.exceptions.UserAssertionErrors.RESPONSE_BODY_NOT_CORRECT;
import static co.com.screenplay.project.exceptions.UserAssertionErrors.USER_DETAIL_NOT_CORRECT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CustomerValidation {

    public static void validateResponseStatus(int expectedStatus, String errorMessage) {
        theActorInTheSpotlight().should(
                seeThat("Status code should be correct",
                        ResponseStatusCode.was(), Matchers.is(expectedStatus))
                        .orComplainWith(UserAssertionErrors.class, errorMessage)
        );
    }

    public static void validateCustomerDetails() {
        CustomerData expectedData = CustomerContext.getCustomerData();
        theActorInTheSpotlight().should(
                seeThatResponse("User details should be correct",
                        response -> CustomerResponse.matches(expectedData).answeredBy(theActorInTheSpotlight()))
                        .orComplainWith(UserAssertionErrors.class, USER_DETAIL_NOT_CORRECT)
        );
    }

    public static void validateCustomerList() {
        theActorInTheSpotlight().should(
                seeThatResponse("Customer list should be valid",
                        response -> CustomerListResponse.isValid().answeredBy(theActorInTheSpotlight()))
                        .orComplainWith(UserAssertionErrors.class, RESPONSE_BODY_NOT_CORRECT)
        );
    }
}
