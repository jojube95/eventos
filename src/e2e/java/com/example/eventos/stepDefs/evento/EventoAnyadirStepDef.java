package com.example.eventos.stepDefs.evento;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventoAnyadirStepDef {

    private final WebConnector connector;

    public EventoAnyadirStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit anyadirEvento page$")
    public void visit_calendar_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/anyadirEvento");
    }

    @When("^User fill all fields$")
    public void user_fill_all_fields() {
        WebElement localidadInput = connector.getDriver().findElement(By.id("localidad"));

        ((JavascriptExecutor) connector.getDriver()).executeScript("$('#fecha').datepicker('update', new Date(2022, 06, 06));");

        localidadInput.sendKeys("Benetuser");
    }

    @When("^User click tipo field$")
    public void user_click_tipo_field() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        tipoSelect.click();
    }

    @When("^User click horario field$")
    public void user_click_horario_field() {
        WebElement horarioSelect = connector.getDriver().findElement(By.id("horario"));
        horarioSelect.click();
    }

    @When("^Use click tipo option$")
    public void user_click_tipo_option() {
        WebElement comunionOption = connector.getDriver().findElement(By.id("optionComunion"));
        comunionOption.click();
    }

    @When("^Use click horario option$")
    public void user_click_horario_option() {
        WebElement cenaOption = connector.getDriver().findElement(By.id("optionCena"));
        cenaOption.click();
    }

    @When("^User click add evento$")
    public void user_click_add_button() {
        WebElement addButton = connector.getDriver().findElement(By.id("buttonAnyadir"));
        addButton.click();
    }

    @When("^User select tipo to comunion$")
    public void user_select_tipo_comunion() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        WebElement comunionOption = connector.getDriver().findElement(By.id("optionComunion"));
        tipoSelect.click();
        comunionOption.click();
    }

    @When("^User select horario to cena$")
    public void user_select_horario_cena() {
        WebElement horarioSelect = connector.getDriver().findElement(By.id("horario"));
        WebElement cenaOption = connector.getDriver().findElement(By.id("optionCena"));
        horarioSelect.click();
        cenaOption.click();
    }

    @When("^User select tipo to Evento individual$")
    public void user_select_tipo_evento_individual() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        WebElement eventoIndividualOption = connector.getDriver().findElement(By.id("optionEventoIndividual"));
        tipoSelect.click();
        eventoIndividualOption.click();

    }

    @When("^User select tipo to Evento comunal")
    public void user_select_tipo_evento_comunal() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        WebElement eventoComunalOption = connector.getDriver().findElement(By.id("optionEventoComunal"));
        tipoSelect.click();
        eventoComunalOption.click();

    }

    @When("^User click fecha field$")
    public void user_click_fecha_field() {
        WebElement fechaInput = connector.getDriver().findElement(By.id("fecha"));
        fechaInput.click();
    }

    @And("^User fill localidad input$")
    public void user_fill_localidad_input(){
        WebElement localidadInput = connector.getDriver().findElement(By.id("localidad"));
        localidadInput.sendKeys("Benetuser");
    }

    @And("^User fill fecha input$")
    public void user_fill_fecha_input(){
        WebElement fechaInput = connector.getDriver().findElement(By.id("fecha"));
        fechaInput.click();

        WebElement datepickerDayButton = connector.getDriver().findElement(By.xpath("(//td[text()='6'])[1]"));
        datepickerDayButton.click();
    }

    @And("^User fill titulo input$")
    public void user_fill_titulo_input(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        tituloInput.sendKeys("Sopar nadal");
    }

    @And("^User click off titulo input$")
    public void user_click_off_titulo_input(){
        WebElement offTitulo = connector.getDriver().findElement(By.xpath("html[1]/body[1]/div[2]/form[1]/div[4]/div[3]"));
        offTitulo.click();
    }

    @Then("^Redirect to calendar page$")
    public void redirect_to_calendar_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/calendario"));
        ((JavascriptExecutor) connector.getDriver()).executeScript("goToDate(new Date(2022, 06, 01));");
    }

    @Then("^Datepicker should display$")
    public void datepicker_should_display(){
        WebElement datepicker = connector.getDriver().findElement(By.xpath("//div[contains(@class,'datepicker datepicker-dropdown')]"));
        assertTrue(datepicker.isDisplayed());
    }

    @Then("^Option tipo should be selected$")
    public void option_tipo_should_be_selected(){
        Select tituloSelect = new Select(connector.getDriver().findElement(By.id("tipo")));
        WebElement optionSelected = tituloSelect.getFirstSelectedOption();
        assertEquals("Comunión", optionSelected.getText());
    }

    @Then("^Option horario should be selected$")
    public void option_horario_should_be_selected(){
        Select horarioInput = new Select(connector.getDriver().findElement(By.id("horario")));
        WebElement optionSelected = horarioInput.getFirstSelectedOption();
        assertEquals("Cena", optionSelected.getText());
    }

    @Then("^Titulo should be Comunion-Comida$")
    public void titulo_should_be_comunion_comida(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Comunion-Comida", tituloInput.getAttribute("value"));
    }

    @Then("^Titulo should be Boda-Cena$")
    public void titulo_should_be_boda_cena(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Boda-Cena", tituloInput.getAttribute("value"));
    }

    @Then("^Titulo should be editable$")
    public void titulo_should_be_editable(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertNull(null, tituloInput.getAttribute("readOnly"));
    }

    @Then("^Titulo fill with selected horario$")
    public void titulo_fill_with_selected_horario(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Sopar nadal-Comida", tituloInput.getAttribute("value"));
    }

    @And("^Created event is shown$")
    public void created_event_is_shown(){
        WebElement createdEvento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title-container']//div)[2]"));
        assertEquals("Boda-Comida", createdEvento.getText());
    }

    @And("^Select event show created data$")
    public void select_event_show_created_data(){
        WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[2]"));
        evento.click();

        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventDetailModal")));

        String actualFecha = connector.getDriver().findElement(By.id("modalEventoFecha")).getText();
        String actualTipo = connector.getDriver().findElement(By.id("modalEventoTipo")).getText();
        String actualHorario = connector.getDriver().findElement(By.id("modalEventoHorario")).getText();
        String actualLocalidad = connector.getDriver().findElement(By.id("modalEventoLocalidad")).getText();
        String actualPersonas = connector.getDriver().findElement(By.id("modalEventoPersonas")).getText();
        String actualNinyos = connector.getDriver().findElement(By.id("modalEventoNinyos")).getText();
        String actualConfirmado = connector.getDriver().findElement(By.id("modalEventoConfirmado")).getText();

        String expectedFecha = "2022-07-06";
        String expectedTipo = "Boda";
        String expectedHorario = "Comida";
        String expectedLocalidad = "Benetuser";
        String expectedPersonas = "0";
        String expectedNinyos = "0";
        String expectedConfirmado   = "false";

        assertEquals(expectedFecha, actualFecha);
        assertEquals(expectedTipo, actualTipo);
        assertEquals(expectedHorario, actualHorario);
        assertEquals(expectedLocalidad, actualLocalidad);
        assertEquals(expectedPersonas, actualPersonas);
        assertEquals(expectedNinyos, actualNinyos);
        assertEquals(expectedConfirmado, actualConfirmado);
    }

    @And("^First day should be monday$")
    public void first_day_should_be_monday(){
        WebElement firstDay = connector.getDriver().findElement(By.xpath("//table[@class='table-condensed']/thead[1]/tr[3]/th[1]"));
        assertEquals("Lu", firstDay.getText());
    }

    @And("^Keep on anyadirEvento page$")
    public void keep_on_anyadirEvento_page() {
        connector.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/anyadirEvento"));
    }

    @And("^Titulo should be readonly$")
    public void titulo_should_be_readonly(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("true", tituloInput.getAttribute("readOnly"));
    }
}
