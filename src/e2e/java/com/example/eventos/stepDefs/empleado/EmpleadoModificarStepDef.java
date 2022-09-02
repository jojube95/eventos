package com.example.eventos.stepDefs.empleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Then("^Empleado data should be correct$")
    public void empleado_data_should_be_correct(){
        String actualTipo = connector.getDriver().findElement(By.id("tipo")).getAttribute("value");
        String actualNombre = connector.getDriver().findElement(By.id("nombre")).getAttribute("value");
        String actualTelefono = connector.getDriver().findElement(By.id("telefono")).getAttribute("value");
        String actualFijo = connector.getDriver().findElement(By.id("fijo")).getAttribute("value");

        assertEquals("Camarero/a", actualTipo);
        assertEquals("Antonio", actualNombre);
        assertEquals("666777888", actualTelefono);
        assertEquals("true", actualFijo);
    }

    @When("^User edit empleado fields$")
    public void user_edit_form_inputs() {
        WebElement tipo = connector.getDriver().findElement(By.id("tipo"));
        WebElement optionCocinero = connector.getDriver().findElement(By.id("optionCocinera"));
        WebElement nombre = connector.getDriver().findElement(By.id("nombre"));
        WebElement telefono = connector.getDriver().findElement(By.id("telefono"));
        WebElement fijo = connector.getDriver().findElement(By.id("fijo"));
        WebElement optionNo = connector.getDriver().findElement(By.id("optionNo"));


        tipo.click();
        optionCocinero.click();

        nombre.clear();
        nombre.sendKeys("AntonioJose");

        telefono.clear();
        telefono.sendKeys("699999999");

        fijo.click();
        optionNo.click();
    }

    @Then("^Modificado empleado is shown$")
    public void modificar_empleado_is_shown() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[2]/td"));
        List<WebElement> empleado3 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[3]/td"));

        assertEquals(3, empleados.size());

        assertEquals("Cocinero/a", empleado1.get(0).getText());
        assertEquals("AntonioJose", empleado1.get(1).getText());
        assertEquals("699999999", empleado1.get(2).getText());
        assertEquals("No", empleado1.get(3).getText());

        assertEquals("Camarero/a", empleado2.get(0).getText());
        assertEquals("Pepe", empleado2.get(1).getText());
        assertEquals("666777999", empleado2.get(2).getText());
        assertEquals("No", empleado2.get(3).getText());

        assertEquals("Cocinero/a", empleado3.get(0).getText());
        assertEquals("Jose", empleado3.get(1).getText());
        assertEquals("666888999", empleado3.get(2).getText());
        assertEquals("Si", empleado3.get(3).getText());
    }
}
