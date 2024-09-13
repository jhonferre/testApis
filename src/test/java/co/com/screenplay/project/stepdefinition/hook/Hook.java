package co.com.screenplay.project.stepdefinition.hook;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hook {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("actor");
    }
}