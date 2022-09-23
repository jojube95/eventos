package com.example.eventos.stepDefs.empleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoAnyadirStepDef {
    private final WebConnector connector;

    public EmpleadoAnyadirStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit empleadoAnyadir page$")
    public void visit_calendar_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/empleadoAnyadir");
    }

    @When("^User fill all empleado fields$")
    public void user_fill_all_empleado_fields() {
        WebElement nombre = connector.getDriver().findElement(By.id("nombre"));
        WebElement telefono = connector.getDriver().findElement(By.id("telefono"));


        nombre.sendKeys("Jesus");
        telefono.sendKeys("678678678");
    }

    @When("^User click add empleado$")
    public void user_click_add_button() {
        WebElement addButton = connector.getDriver().findElement(By.id("buttonAnyadir"));
        addButton.click();
    }

    @When("^User click empleado tipo field$")
    public void user_click_tipo_field() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        tipoSelect.click();
    }

    @When("^User click empleado fijo field$")
    public void user_click_fijo_field() {
        WebElement fijoSelect = connector.getDriver().findElement(By.id("fijo"));
        fijoSelect.click();
    }

    @When("^User click empleado tipo option$")
    public void user_click_empleado_tipo_option() {
        WebElement optionCocinera = connector.getDriver().findElement(By.id("optionCocinera"));
        optionCocinera.click();
    }

    @When("^User click empleado fijo option$")
    public void user_click_fijo_option() {
        WebElement optionSi = connector.getDriver().findElement(By.id("optionSi"));
        optionSi.click();
    }

    @Then("^Redirect to empleados page$")
    public void redirect_to_calendar_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/empleados"));
    }

    @Then("^Created empleado is shown$")
    public void created_empleado_is_shown() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[2]/td"));
        List<WebElement> empleado3 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[3]/td"));
        List<WebElement> empleado4 = connector.getDriver().findElements(By.xpath("//table[@id='empleados']/tbody/tr[4]/td"));

        assertEquals(4, empleados.size());

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

        assertEquals("Camarero/a", empleado4.get(0).getText());
        assertEquals("Jesus", empleado4.get(1).getText());
        assertEquals("678678678", empleado4.get(2).getText());
        assertEquals("No", empleado4.get(3).getText());
    }

    @Then("^Option empelado tipo should be selected$")
    public void option_empleado_tipo_should_be_selected(){
        Select tipoSelect = new Select(connector.getDriver().findElement(By.id("tipo")));
        WebElement optionSelected = tipoSelect.getFirstSelectedOption();
        assertEquals("Cocinero/a", optionSelected.getText());
    }

    @Then("^Option empelado fijo should be selected$")
    public void option_empleado_fijo_should_be_selected(){
        Select fijoSelect = new Select(connector.getDriver().findElement(By.id("fijo")));
        WebElement optionSelected = fijoSelect.getFirstSelectedOption();
        assertEquals("Sí", optionSelected.getText());
    }
}
