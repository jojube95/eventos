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
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[2]/td"));

        assertEquals(2, empleados.size());

        assertEquals("Antonio", empleado1.get(0).getText());
        assertEquals("Si", empleado1.get(1).getText());
        assertEquals("No", empleado1.get(2).getText());
        assertEquals("0.0", empleado1.get(3).getText());

        assertEquals("Pepe", empleado2.get(0).getText());
        assertEquals("No", empleado2.get(1).getText());
        assertEquals("Si", empleado2.get(2).getText());
        assertEquals("0.5", empleado2.get(3).getText());
    }

    @And("^Evento empleado select list should be correct$")
    public void evento_select_list_should_be_correct() {
        Select empleados = new Select(connector.getDriver().findElement(By.id("empleado")));
        List<WebElement> options = empleados.getOptions();

        assertEquals("Fijos", options.get(0).getText());
        assertEquals("Antonio", options.get(1).getText());
        assertEquals("No fijos", options.get(2).getText());
        assertEquals("Pepe", options.get(3).getText());
    }

    @And("^Progressbar should be correct$")
    public void progressbar_should_be_correct() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarNoConfirmados"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("11.1111%"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("11.1111%"));
    }

    @And("^Progressbar should update new empleado$")
    public void progressbar_should_update_new_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarNoConfirmados"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("11.1111%"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("22.2222%"));
    }

    @And("^Progressbar should update updated empleado$")
    public void progressbar_should_update_updated_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarNoConfirmados"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("22.2222%"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("0%"));
    }

    @And("^Progressbar should update with deleted empleado$")
    public void progressbar_should_update_deleted_empleado() {
        WebElement progresbarConfirmados = connector.getDriver().findElement(By.id("progressbarConfirmados"));
        WebElement progresbarNoConfirmados = connector.getDriver().findElement(By.id("progressbarNoConfirmados"));

        assertThat(progresbarConfirmados.getAttribute("style"), CoreMatchers.containsString("11.1111%"));
        assertThat(progresbarNoConfirmados.getAttribute("style"), CoreMatchers.containsString("0%"));
    }

    @When("^User select empleado$")
    public void user_select_empleado_cena() {
        WebElement empleadoSelect = connector.getDriver().findElement(By.id("empleado"));
        WebElement antonioOption = connector.getDriver().findElement(By.xpath("//option[@value='6310ca7042c98257fb6f349e']"));
        empleadoSelect.click();
        antonioOption.click();
    }

    @When("^User click anyadir event empleado$")
    public void user_click_anyadir_empleado() {
        WebElement buttonAnyadir = connector.getDriver().findElement(By.id("buttonAnyadir"));
        buttonAnyadir.click();
    }

    @When("^User click on modificar evento empleado$")
    public void user_click_modificar_evento_empleado() {
        WebElement buttonModificar = connector.getDriver().findElement(By.xpath("//table[@id='eventoEmpleados']/tbody[1]/tr[1]/td[5]/button[1]"));
        buttonModificar.click();
    }

    @When("^User click on delete evento empleado$")
    public void user_click_delete_evento_empleado() {
        WebElement buttonDelete = connector.getDriver().findElement(By.xpath("//table[@id='eventoEmpleados']/tbody[1]/tr[1]/td[6]/button[1]"));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[3]/td")));

        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[2]/td"));
        List<WebElement> empleado3 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[3]/td"));

        assertEquals(3, empleados.size());

        assertEquals("Antonio", empleado1.get(0).getText());
        assertEquals("Si", empleado1.get(1).getText());
        assertEquals("No", empleado1.get(2).getText());
        assertEquals("0.0", empleado1.get(3).getText());

        assertEquals("Pepe", empleado2.get(0).getText());
        assertEquals("No", empleado2.get(1).getText());
        assertEquals("Si", empleado2.get(2).getText());
        assertEquals("0.5", empleado2.get(3).getText());

        assertEquals("Pepe", empleado3.get(0).getText());
        assertEquals("No", empleado3.get(1).getText());
        assertEquals("No", empleado3.get(2).getText());
        assertEquals("0", empleado3.get(3).getText());
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[2]/td")));

        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[1]/td"));

        assertEquals(1, empleados.size());

        assertEquals("Pepe", empleado1.get(0).getText());
        assertEquals("No", empleado1.get(1).getText());
        assertEquals("Si", empleado1.get(2).getText());
        assertEquals("0.5", empleado1.get(3).getText());
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
        List<WebElement> empleados = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr"));

        List<WebElement> empleado1 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[1]/td"));
        List<WebElement> empleado2 = connector.getDriver().findElements(By.xpath("//table[@id='eventoEmpleados']/tbody/tr[2]/td"));

        assertEquals(2, empleados.size());

        assertEquals("Antonio", empleado1.get(0).getText());
        assertEquals("Si", empleado1.get(1).getText());
        assertEquals("Si", empleado1.get(2).getText());
        assertEquals("1.5", empleado1.get(3).getText());

        assertEquals("Pepe", empleado2.get(0).getText());
        assertEquals("No", empleado2.get(1).getText());
        assertEquals("Si", empleado2.get(2).getText());
        assertEquals("0.5", empleado2.get(3).getText());
    }
}
