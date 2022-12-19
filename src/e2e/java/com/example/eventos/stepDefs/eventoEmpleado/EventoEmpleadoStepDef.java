package com.example.eventos.stepDefs.eventoEmpleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoEmpleadoStepDef {
    private final WebConnector connector;

    public EventoEmpleadoStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit evento empleado page$")
    public void visit_evento_empleado_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/evento/empleados?eventoId=62dc2a63ec628818203950b9");
    }

    @Then("^Evento empleado data should be correct$")
    public void evento_empleados_correct_data() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tfoot/tr/td"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[2]/td"));

        assertEquals(2, empleados.size());

        assertEquals("Antonio", empleado1.get(4).getText());
        assertEquals("Si", empleado1.get(5).getText());
        assertEquals("No", empleado1.get(6).getText());
        assertEquals("No", empleado1.get(7).getText());
        assertEquals("0.0", empleado1.get(8).getText());

        assertEquals("Pepe", empleado2.get(4).getText());
        assertEquals("No", empleado2.get(5).getText());
        assertEquals("Si", empleado2.get(6).getText());
        assertEquals("Si", empleado2.get(7).getText());
        assertEquals("0.5", empleado2.get(8).getText());

        assertEquals("Total: 2", footer.get(4).getText());
    }

    @And("^Evento empleado select list should be correct$")
    public void evento_select_list_should_be_correct() {
        Select empleados = new Select(connector.getDriver().findElement(By.id("empleadoCamarero")));
        List<WebElement> options = empleados.getOptions();

        assertEquals("Fijos", options.get(0).getText());
        assertEquals("Juan", options.get(1).getText());
        assertEquals("No fijos", options.get(2).getText());
    }

    @And("^Progressbar should be correct$")
    public void progressbar_should_be_correct() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosNoConfirmados"));

        assertEquals("rgba(0, 128, 0, 1)", progresbarConfirmados.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 0, 1)", progresbarNoConfirmados.getCssValue("background-color"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 10%;"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 10%;"));
    }

    @And("^Progressbar should update new empleado$")
    public void progressbar_should_update_new_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosNoConfirmados"));

        assertEquals("rgba(0, 128, 0, 1)", progresbarConfirmados.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 0, 1)", progresbarNoConfirmados.getCssValue("background-color"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 10%;"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 20%;"));
    }

    @And("^Progressbar should update updated empleado$")
    public void progressbar_should_update_updated_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosNoConfirmados"));

        assertEquals("rgba(0, 128, 0, 1)", progresbarConfirmados.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 0, 1)", progresbarNoConfirmados.getCssValue("background-color"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 20%;"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 0%;"));
    }

    @And("^Progressbar should update with deleted empleado$")
    public void progressbar_should_update_deleted_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarCamarerosNoConfirmados"));

        assertEquals("rgba(0, 128, 0, 1)", progresbarConfirmados.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 0, 1)", progresbarNoConfirmados.getCssValue("background-color"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 10%;"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("width: 0%;"));
    }

    @When("^User select empleado$")
    public void user_select_empleado_cena() {
        WebElement empleadoSelect = connector.getDriver().findElement(By.id("empleadoCamarero"));
        WebElement juanOption = connector.getDriver().findElement(By.xpath("//option[@value='639b19b90331fa55c5d161eb']"));
        empleadoSelect.click();
        juanOption.click();
    }

    @When("^User click anyadir event empleado$")
    public void user_click_anyadir_empleado() {
        WebElement buttonAnyadir = connector.getDriver().findElement(By.id("buttonAnyadirCamarero"));
        buttonAnyadir.click();
    }

    @When("^User click on modificar evento empleado$")
    public void user_click_modificar_evento_empleado() {
        WebElement buttonModificar = connector.getDriver().findElement(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody[1]/tr[1]/td[10]/button[1]"));
        buttonModificar.click();
    }

    @When("^User click on delete evento empleado$")
    public void user_click_delete_evento_empleado() {
        WebElement buttonDelete = connector.getDriver().findElement(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody[1]/tr[1]/td[11]/button[1]"));
        buttonDelete.click();
    }

    @When("^User click evento empleado modificar button$")
    public void user_click_evento_empleado_modificar_button() {
        WebElement buttonModificar = connector.getDriver().findElement(By.id("modalEventoModificarButton"));
        buttonModificar.click();
    }

    @When("^User modify evento empelado$")
    public void user_modify_evento_empleado() {
        WebElement confirmado = connector.getDriver().findElement(By.id("confirmado"));
        WebElement optionSi = connector.getDriver().findElement(By.id("optionSi"));
        WebElement horasExtra = connector.getDriver().findElement(By.id("horasExtras"));

        confirmado.click();
        optionSi.click();

        horasExtra.clear();
        horasExtra.sendKeys("1.5");
    }

    @Then("^Evento empleado is added to list$")
    public void evento_empleado_is_added_to_list() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"), 3));

        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tfoot/tr/td"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[2]/td"));
        List<WebElement> empleado3 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[3]/td"));

        assertEquals(3, empleados.size());

        assertEquals("Antonio", empleado1.get(4).getText());
        assertEquals("Si", empleado1.get(5).getText());
        assertEquals("No", empleado1.get(6).getText());
        assertEquals("No", empleado1.get(7).getText());
        assertEquals("0.0", empleado1.get(8).getText());

        assertEquals("Pepe", empleado2.get(4).getText());
        assertEquals("No", empleado2.get(5).getText());
        assertEquals("Si", empleado2.get(6).getText());
        assertEquals("Si", empleado2.get(7).getText());
        assertEquals("0.5", empleado2.get(8).getText());

        assertEquals("Juan", empleado3.get(4).getText());
        assertEquals("Si", empleado3.get(5).getText());
        assertEquals("No", empleado3.get(6).getText());
        assertEquals("No", empleado3.get(7).getText());
        assertEquals("0", empleado3.get(8).getText());

        assertEquals("Total: 3", footer.get(4).getText());
    }

    @Then("^Evento empleado update modal should display$")
    public void evento_empleado_update_modal_should_display() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventoEmpleadoModificarModal")));
    }

    @Then("^Evento empleado update modal should hide$")
    public void evento_empleado_update_modal_should_hide() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("eventoEmpleadoModificarModal")));
    }

    @Then("^Then empleado is deleted on list$")
    public void evento_empleados_deleted_from_list() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"), 1));

        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tfoot/tr/td"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[1]/td"));

        assertEquals(1, empleados.size());

        assertEquals("Pepe", empleado1.get(4).getText());
        assertEquals("No", empleado1.get(5).getText());
        assertEquals("Si", empleado1.get(6).getText());
        assertEquals("Si", empleado1.get(7).getText());
        assertEquals("0.5", empleado1.get(8).getText());

        assertEquals("Total: 1", footer.get(4).getText());
    }

    @Then("^Evento empleado update modal should have correct data$")
    public void evento_empleado_update_modal_should_have_correct_data(){
        String actualConfirmado = connector.getDriver().findElement(By.id("confirmado")).getAttribute("value");
        String actualHorasExtra = connector.getDriver().findElement(By.id("horasExtras")).getAttribute("value");

        assertEquals("false", actualConfirmado);
        assertEquals("0.0", actualHorasExtra);
    }

    @Then("^Evento empleado is updated on list$")
    public void evento_empleados_is_updated_on_list() {
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tfoot/tr/td"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleadosCamareros']/tbody/tr[2]/td"));

        assertEquals(2, empleados.size());

        assertEquals("Antonio", empleado1.get(4).getText());
        assertEquals("Si", empleado1.get(5).getText());
        assertEquals("Si", empleado1.get(6).getText());
        assertEquals("No", empleado1.get(7).getText());
        assertEquals("1.5", empleado1.get(8).getText());

        assertEquals("Pepe", empleado2.get(4).getText());
        assertEquals("No", empleado2.get(5).getText());
        assertEquals("Si", empleado2.get(6).getText());
        assertEquals("Si", empleado2.get(7).getText());
        assertEquals("0.5", empleado2.get(8).getText());

        assertEquals("Total: 2", footer.get(4).getText());
    }
}
