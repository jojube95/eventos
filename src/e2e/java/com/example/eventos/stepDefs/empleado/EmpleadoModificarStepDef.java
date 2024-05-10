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
        connector.getDriver().get(connector.getBaseUrl() + "/empleadoUpdate?empleadoId=6310ca5942c98257fb6f349d");
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

        assertEquals("camarero", actualTipo);
        assertEquals("Antonio", actualNombre);
        assertEquals("666777888", actualTelefono);
        assertEquals("true", actualFijo);
    }

    @When("^User edit empleado fields$")
    public void user_edit_form_inputs() {
        WebElement tipo = connector.getDriver().findElement(By.id("tipo"));
        WebElement optionCocinero = connector.getDriver().findElement(By.id("cocinero"));
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
        List<WebElement> empleadosCamareros = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCamareros']/tbody/tr"));

        List<WebElement> empleadoCamarero1 = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCamareros']/tbody/tr[1]/td"));
        List<WebElement> empleadoCamarero2 = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCamareros']/tbody/tr[2]/td"));

        List<WebElement> empleadosCocineros = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCocineros']/tbody/tr"));

        List<WebElement> empleadoCocinero1 = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCocineros']/tbody/tr[1]/td"));
        List<WebElement> empleadoCocinero2 = connector.getDriver().findElements(By.xpath("//table[@id='empleadosCocineros']/tbody/tr[2]/td"));

        assertEquals(2, empleadosCamareros.size());

        assertEquals("Camarero/a", empleadoCamarero1.get(0).getText());
        assertEquals("Pepe", empleadoCamarero1.get(1).getText());
        assertEquals("666777999", empleadoCamarero1.get(2).getText());
        assertEquals("No", empleadoCamarero1.get(3).getText());
        assertEquals("Si", empleadoCamarero1.get(4).getText());

        assertEquals("Camarero/a", empleadoCamarero2.get(0).getText());
        assertEquals("Juan", empleadoCamarero2.get(1).getText());
        assertEquals("686868686", empleadoCamarero2.get(2).getText());
        assertEquals("Si", empleadoCamarero2.get(3).getText());
        assertEquals("No", empleadoCamarero2.get(4).getText());

        assertEquals(2, empleadosCocineros.size());

        assertEquals("Cocinero/a", empleadoCocinero1.get(0).getText());
        assertEquals("AntonioJose", empleadoCocinero1.get(1).getText());
        assertEquals("699999999", empleadoCocinero1.get(2).getText());
        assertEquals("No", empleadoCocinero1.get(3).getText());

        assertEquals("Cocinero/a", empleadoCocinero2.get(0).getText());
        assertEquals("Jose", empleadoCocinero2.get(1).getText());
        assertEquals("666888999", empleadoCocinero2.get(2).getText());
        assertEquals("Si", empleadoCocinero2.get(3).getText());
    }
}
