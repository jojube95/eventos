package com.example.eventos.stepDefs.empleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadosVerStepDef {
    private final WebConnector connector;

    public EmpleadosVerStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit empleados page$")
    public void visit_ver_eventos_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/empleados");
    }

    @When("^User click on modificar empleado$")
    public void user_click_modificar_empleado() {
        WebElement modificarButton = connector.getDriver().findElement(By.xpath("//table[@id='empleados']/tbody[1]/tr[1]/td[5]/button[1]"));
        modificarButton.click();
    }

    @When("^User click on historial empleado$")
    public void user_click_historial() {
        WebElement historialButton = connector.getDriver().findElement(By.xpath("//table[@id='empleados']/tbody[1]/tr[1]/td[6]/button[1]"));
        historialButton.click();
    }

    @When("^User click on eliminar empleado$")
    public void user_click_eliminar_empleado() {
        WebElement deleteButton = connector.getDriver().findElement(By.xpath("//table[@id='empleados']/tbody[1]/tr[1]/td[7]/button[1]"));
        deleteButton.click();
    }

    @Then("^Table should show three empleados$")
    public void table_display_correct_data() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[2]/td"));
        List<WebElement> empleado3 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[3]/td"));

        assertEquals(3, empleados.size());

        assertEquals("Camarero/a", empleado1.get(0).getText());
        assertEquals("Antonio", empleado1.get(1).getText());
        assertEquals("666777888", empleado1.get(2).getText());
        assertEquals("Si", empleado1.get(3).getText());

        assertEquals("Camarero/a", empleado2.get(0).getText());
        assertEquals("Pepe", empleado2.get(1).getText());
        assertEquals("666777999", empleado2.get(2).getText());
        assertEquals("No", empleado2.get(3).getText());

        assertEquals("Cocinero/a", empleado3.get(0).getText());
        assertEquals("Jose", empleado3.get(1).getText());
        assertEquals("666888999", empleado3.get(2).getText());
        assertEquals("Si", empleado3.get(3).getText());
    }

    @Then("^Page should redirect to modificar empleado page$")
    public void redirect_to_ver_modificar_empleado(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/empleadoUpdate?empleadoId=6310ca5942c98257fb6f349d"));
    }

    @Then("^Page should redirect to historial empleado page$")
    public void redirect_to_historial_empleado(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/empleadoHistorial?empleadoId=6310ca5942c98257fb6f349d"));
    }

    @Then("^Eliminar empleado confirm modal should show$")
    public void eliminar_empleado_modal_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmarEliminarModal")));
    }

    @Then("^Eliminar empleado confirm modal should be hide$")
    public void eliminar_empleado_modal_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirmarEliminarModal")));
    }

    @When("^User click on eliminar confirm button")
    public void user_click_eliminar_empleado_confirm() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmarEliminarModal")));

        connector.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement modal_eliminar_button = connector.getDriver().findElement(By.id("modalEliminarButton"));
        modal_eliminar_button.click();
    }

    @Then("^Empleado should be deleted$")
    public void empleado_should_be_deleted() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[2]/td"));

        assertEquals(2, empleados.size());

        assertEquals("Camarero/a", empleado1.get(0).getText());
        assertEquals("Pepe", empleado1.get(1).getText());
        assertEquals("666777999", empleado1.get(2).getText());
        assertEquals("No", empleado1.get(3).getText());

        assertEquals("Cocinero/a", empleado2.get(0).getText());
        assertEquals("Jose", empleado2.get(1).getText());
        assertEquals("666888999", empleado2.get(2).getText());
        assertEquals("Si", empleado2.get(3).getText());
    }
}
