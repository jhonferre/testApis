package co.com.screenplay.project.questions;

import co.com.screenplay.project.models.CustomerData;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.hamcrest.Matchers.equalTo;

public class CustomerResponse implements Question<ValidatableResponse> {

    private final CustomerData expectedCustomerData;

    public CustomerResponse(CustomerData customerData) {
        this.expectedCustomerData = customerData;
    }

    public static CustomerResponse matches(CustomerData customerData) {
        return new CustomerResponse(customerData);
    }

    @Override
    public ValidatableResponse answeredBy(Actor actor) {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        response.body("identityDocument", equalTo(expectedCustomerData.getData().get("identityDocument")))
                .body("documentType", equalTo(expectedCustomerData.getData().get("documentType")))
                .body("fullName", equalTo(expectedCustomerData.getData().get("fullName")))
                .body("dateOfBirth", equalTo(expectedCustomerData.getData().get("dateOfBirth")));
        return response;
    }

    public static int extractCustomerId() {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        return response.extract().path("id");
    }
}
