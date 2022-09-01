package com.example.eventos.stepDefs.empleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EmpleadoModificarStepDef {
    private final WebConnector connector;

    public EmpleadoModificarStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit modificar empleado page$")
    public void visit_modificar_empleado_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/updateEmpleado?empleadoId=6310ca5942c98257fb6f349d");
    }

    @When("^User click modificar empleado button$")
    public void user_click_add_button() {
        WebElement modifyButton = connector.getDriver().findElement(By.id("buttonModificar"));
        modifyButton.click();
    }
}
