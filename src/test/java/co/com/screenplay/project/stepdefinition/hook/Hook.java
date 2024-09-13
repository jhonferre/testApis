package co.com.screenplay.project.stepdefinition.hook;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {
    private static final String DEFAULT_ENVIRONMENT = "restapi.baseurl";
    private static final String DEFAULT_PATH = "https://reqres.in/api";
    private EnvironmentVariables environmentVariables;
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("actor");
        String theRestApiBaseUrl = environmentVariables.optionalProperty(DEFAULT_ENVIRONMENT)
                .orElse(DEFAULT_PATH);
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
    }
}