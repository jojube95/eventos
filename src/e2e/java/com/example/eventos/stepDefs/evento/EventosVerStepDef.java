package com.example.eventos.stepDefs.evento;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventosVerStepDef {
    private final WebConnector connector;

    public EventosVerStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit eventosVer page$")
    public void visit_ver_eventos_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get(connector.getBaseUrl() + "/eventosVer");
    }

    @Given("^Set dates to avoid future test fails$")
    public void set_initial_dates() {
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));

        fechaMin.clear();
        fechaMin.sendKeys("2022-01-01");
        connector.getDriver().findElement(By.tagName("html")).click();

        fechaMax.clear();
        fechaMax.sendKeys("2022-12-31");
        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @When("^User enter min date 2022-07-10$")
    public void user_enter_min_date(){
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));

        fechaMin.clear();
        fechaMin.sendKeys("2022-07-10");

        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @When("^User enter max date 2022-07-16$")
    public void user_enter_max_date(){
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));

        fechaMax.clear();
        fechaMax.sendKeys("2022-07-16");

        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @When("^User enter tipo Boda$")
    public void user_enter_tipo_boda(){
        WebElement tipoSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]"));
        WebElement tipoBoda = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]/option[2]"));
        tipoSelect.click();
        tipoBoda.click();
    }

    @When("^User enter horario Comida$")
    public void user_enter_horario_comida(){
        WebElement horarioSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]"));
        WebElement horarioComida = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]/option[3]"));
        horarioSelect.click();
        horarioComida.click();
    }

    @When("^User enter sala Sala1")
    public void user_enter_sala_sala1(){
        WebElement salaSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]"));
        WebElement salaSala1 = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]/option[3]"));
        salaSelect.click();
        salaSala1.click();
    }

    @When("^User enter localidad Aielo de Malferit$")
    public void user_enter_localidad_aielo(){
        WebElement localidadSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[6]/select[1]"));
        WebElement localidadAielo = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[6]/select[1]/option[2]"));
        localidadSelect.click();
        localidadAielo.click();
    }

    @When("^User enter confirmado Si$")
    public void user_enter_confirmado_si(){
        WebElement confirmadoSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[11]/select[1]"));
        WebElement confirmadoBoda = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[11]/select[1]/option[2]"));
        confirmadoSelect.click();
        confirmadoBoda.click();
    }

    @When("^User enter horario Comida and localidad Olleria$")
    public void user_enter_horario_comida_localidad_olleria(){
        WebElement horarioSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]"));
        WebElement horarioComida = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]/option[3]"));
        horarioSelect.click();
        horarioComida.click();

        WebElement localidadSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]"));
        WebElement localidadOlleria = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]/option[4]"));
        localidadSelect.click();
        localidadOlleria.click();
    }

    @When("^User click first event$")
    public void user_click_first_event(){
        WebElement evento1 = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/tbody/tr[1]"));
        evento1.click();
    }

    @Then("^Table display all events with correct data$")
    public void table_display_correct_data() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[2]/td"));
        List<WebElement> evento3 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[3]/td"));
        List<WebElement> evento4 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(4, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("80.0", evento1.get(7).getText());
        assertEquals("13", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("Si", evento1.get(10).getText());
        assertEquals("4041€", evento1.get(11).getText());

        assertEquals("2022-07-10", evento2.get(0).getText());
        assertEquals("Comunión-Comida", evento2.get(1).getText());
        assertEquals("Comunión", evento2.get(2).getText());
        assertEquals("Comida", evento2.get(3).getText());
        assertEquals("Sala2", evento2.get(4).getText());
        assertEquals("Olleria", evento2.get(5).getText());
        assertEquals("43", evento2.get(6).getText());
        assertEquals("50.0", evento2.get(7).getText());
        assertEquals("18", evento2.get(8).getText());
        assertEquals("15.0", evento2.get(9).getText());
        assertEquals("No", evento2.get(10).getText());
        assertEquals("787€", evento2.get(11).getText());

        assertEquals("2022-07-16", evento3.get(0).getText());
        assertEquals("Sopar festes-Cena", evento3.get(1).getText());
        assertEquals("Evento individual", evento3.get(2).getText());
        assertEquals("Cena", evento3.get(3).getText());
        assertEquals("Patio", evento3.get(4).getText());
        assertEquals("Aielo de Malferit", evento3.get(5).getText());
        assertEquals("90", evento3.get(6).getText());
        assertEquals("35.0", evento3.get(7).getText());
        assertEquals("23", evento3.get(8).getText());
        assertEquals("15.0", evento3.get(9).getText());
        assertEquals("Si", evento3.get(10).getText());
        assertEquals("1136€", evento3.get(11).getText());

        assertEquals("2022-07-23", evento4.get(0).getText());
        assertEquals("Dinar empresa-Comida", evento4.get(1).getText());
        assertEquals("Evento comunal", evento4.get(2).getText());
        assertEquals("Comida", evento4.get(3).getText());
        assertEquals("Sala1", evento4.get(4).getText());
        assertEquals("Albaida", evento4.get(5).getText());
        assertEquals("36", evento4.get(6).getText());
        assertEquals("40.0", evento4.get(7).getText());
        assertEquals("0", evento4.get(8).getText());
        assertEquals("0.0", evento4.get(9).getText());
        assertEquals("No", evento4.get(10).getText());
        assertEquals("468€", evento4.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("322", footer.get(1).getText());
        assertEquals("51", footer.get(2).getText());
        assertEquals("54", footer.get(3).getText());
        assertEquals("11", footer.get(4).getText());
        assertEquals("6432€", footer.get(6).getText());

    }

    @Then("^Three events shown$")
    public void three_events_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(3, eventos.size());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("169", footer.get(1).getText());
        assertEquals("42", footer.get(2).getText());
        assertEquals("41", footer.get(3).getText());
        assertEquals("10", footer.get(4).getText());
        assertEquals("2390€", footer.get(6).getText());
    }

    @Then("^Two events shown$")
    public void two_events_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("133", footer.get(1).getText());
        assertEquals("43", footer.get(2).getText());
        assertEquals("41", footer.get(3).getText());
        assertEquals("15", footer.get(4).getText());
        assertEquals("1922€", footer.get(6).getText());
    }

    @And("^Fecha min is void$")
    public void fecha_min_is_void() {
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));

        assertEquals("", fechaMin.getText());
    }

    @Then("^Evento boda is shown$")
    public void evento_boda_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(1, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("80.0", evento1.get(7).getText());
        assertEquals("13", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("Si", evento1.get(10).getText());
        assertEquals("4041€", evento1.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("153", footer.get(1).getText());
        assertEquals("80", footer.get(2).getText());
        assertEquals("13", footer.get(3).getText());
        assertEquals("15", footer.get(4).getText());
        assertEquals("4041€", footer.get(6).getText());
    }

    @Then("^Evento comida is shown$")
    public void evento_comida_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-10", evento1.get(0).getText());
        assertEquals("Comunión-Comida", evento1.get(1).getText());
        assertEquals("Comunión", evento1.get(2).getText());
        assertEquals("Comida", evento1.get(3).getText());
        assertEquals("Sala2", evento1.get(4).getText());
        assertEquals("Olleria", evento1.get(5).getText());
        assertEquals("43", evento1.get(6).getText());
        assertEquals("50.0", evento1.get(7).getText());
        assertEquals("18", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("No", evento1.get(10).getText());
        assertEquals("787€", evento1.get(11).getText());

        assertEquals("2022-07-23", evento2.get(0).getText());
        assertEquals("Dinar empresa-Comida", evento2.get(1).getText());
        assertEquals("Evento comunal", evento2.get(2).getText());
        assertEquals("Comida", evento2.get(3).getText());
        assertEquals("Sala1", evento2.get(4).getText());
        assertEquals("Albaida", evento2.get(5).getText());
        assertEquals("36", evento2.get(6).getText());
        assertEquals("40.0", evento2.get(7).getText());
        assertEquals("0", evento2.get(8).getText());
        assertEquals("0.0", evento2.get(9).getText());
        assertEquals("No", evento2.get(10).getText());
        assertEquals("468€", evento2.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("79", footer.get(1).getText());
        assertEquals("45", footer.get(2).getText());
        assertEquals("18", footer.get(3).getText());
        assertEquals("8", footer.get(4).getText());
        assertEquals("1255€", footer.get(6).getText());
    }

    @Then("^Evento sala1 is shown$")
    public void evento_sala1_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("80.0", evento1.get(7).getText());
        assertEquals("13", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("Si", evento1.get(10).getText());
        assertEquals("4041€", evento1.get(11).getText());

        assertEquals("2022-07-23", evento2.get(0).getText());
        assertEquals("Dinar empresa-Comida", evento2.get(1).getText());
        assertEquals("Evento comunal", evento2.get(2).getText());
        assertEquals("Comida", evento2.get(3).getText());
        assertEquals("Sala1", evento2.get(4).getText());
        assertEquals("Albaida", evento2.get(5).getText());
        assertEquals("36", evento2.get(6).getText());
        assertEquals("40.0", evento2.get(7).getText());
        assertEquals("0", evento2.get(8).getText());
        assertEquals("0.0", evento2.get(9).getText());
        assertEquals("No", evento2.get(10).getText());
        assertEquals("468€", evento2.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("189", footer.get(1).getText());
        assertEquals("60", footer.get(2).getText());
        assertEquals("13", footer.get(3).getText());
        assertEquals("8", footer.get(4).getText());
        assertEquals("4509€", footer.get(6).getText());
    }

    @Then("^Evento Aielo de Malferit is shown$")
    public void evento_aielo_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("80.0", evento1.get(7).getText());
        assertEquals("13", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("Si", evento1.get(10).getText());
        assertEquals("4041€", evento1.get(11).getText());

        assertEquals("2022-07-16", evento2.get(0).getText());
        assertEquals("Sopar festes-Cena", evento2.get(1).getText());
        assertEquals("Evento individual", evento2.get(2).getText());
        assertEquals("Cena", evento2.get(3).getText());
        assertEquals("Patio", evento2.get(4).getText());
        assertEquals("Aielo de Malferit", evento2.get(5).getText());
        assertEquals("90", evento2.get(6).getText());
        assertEquals("35.0", evento2.get(7).getText());
        assertEquals("23", evento2.get(8).getText());
        assertEquals("15.0", evento2.get(9).getText());
        assertEquals("Si", evento2.get(10).getText());
        assertEquals("1136€", evento2.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("243", footer.get(1).getText());
        assertEquals("58", footer.get(2).getText());
        assertEquals("36", footer.get(3).getText());
        assertEquals("15", footer.get(4).getText());
        assertEquals("5177€", footer.get(6).getText());
    }

    @Then("^Evento confirmado is shown$")
    public void evento_confirmado_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("80.0", evento1.get(7).getText());
        assertEquals("13", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("Si", evento1.get(10).getText());
        assertEquals("4041€", evento1.get(11).getText());

        assertEquals("2022-07-16", evento2.get(0).getText());
        assertEquals("Sopar festes-Cena", evento2.get(1).getText());
        assertEquals("Evento individual", evento2.get(2).getText());
        assertEquals("Cena", evento2.get(3).getText());
        assertEquals("Patio", evento2.get(4).getText());
        assertEquals("Aielo de Malferit", evento2.get(5).getText());
        assertEquals("90", evento2.get(6).getText());
        assertEquals("35.0", evento2.get(7).getText());
        assertEquals("23", evento2.get(8).getText());
        assertEquals("15.0", evento2.get(9).getText());
        assertEquals("Si", evento2.get(10).getText());
        assertEquals("1136€", evento2.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("243", footer.get(1).getText());
        assertEquals("58", footer.get(2).getText());
        assertEquals("36", footer.get(3).getText());
        assertEquals("15", footer.get(4).getText());
        assertEquals("5177€", footer.get(6).getText());
    }

    @Then("^Evento Comida and Olleria is shown$")
    public void evento_comida_olleria_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tbody/tr[1]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='eventos']/tfoot/tr/td"));

        assertEquals(1, eventos.size());

        assertEquals("2022-07-10", evento1.get(0).getText());
        assertEquals("Comunión-Comida", evento1.get(1).getText());
        assertEquals("Comunión", evento1.get(2).getText());
        assertEquals("Comida", evento1.get(3).getText());
        assertEquals("Sala2", evento1.get(4).getText());
        assertEquals("Olleria", evento1.get(5).getText());
        assertEquals("43", evento1.get(6).getText());
        assertEquals("50.0", evento1.get(7).getText());
        assertEquals("18", evento1.get(8).getText());
        assertEquals("15.0", evento1.get(9).getText());
        assertEquals("No", evento1.get(10).getText());
        assertEquals("787€", evento1.get(11).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("43", footer.get(1).getText());
        assertEquals("50", footer.get(2).getText());
        assertEquals("18", footer.get(3).getText());
        assertEquals("15", footer.get(4).getText());
        assertEquals("787€", footer.get(6).getText());
    }

    @Then("^Redirect to eventoVer page with currect evento$")
    public void redirect_to_ver_evento(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(connector.getBaseUrl() + "/eventoVer?eventoId=62dc2a63ec628818203950b9"));
    }

    @And("^Fecha max is void$")
    public void fecha_max_is_void() {
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));

        assertEquals("", fechaMax.getText());
    }

    @And("^Search is void$")
    public void search_is_void() {
        WebElement search = connector.getDriver().findElement(By.xpath("//div[@class='dataTables_filter']//input[1]"));

        assertEquals("", search.getText());
    }

    @And("^Tipo not selected")
    public void tipo_not_selected() {
        Select tipo = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]")));
        WebElement optionSelected = tipo.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Horario not selected")
    public void horario_not_selected() {
        Select horario = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]")));
        WebElement optionSelected = horario.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Sala not selected")
    public void sala_not_selected() {
        Select sala = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]")));
        WebElement optionSelected = sala.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Localidad not selected")
    public void localidad_not_selected() {
        Select localidad = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]")));
        WebElement optionSelected = localidad.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Confirmar not selected")
    public void confirmar_not_selected() {
        Select confirmar = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[11]/select[1]")));
        WebElement optionSelected = confirmar.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Tipo options correct")
    public void tipo_options_correct() {
        Select tipo = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]")));
        List<WebElement> options = tipo.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Boda", options.get(1).getText());
        assertEquals("Comunión", options.get(2).getText());
        assertEquals("Evento comunal", options.get(3).getText());
        assertEquals("Evento individual", options.get(4).getText());
    }

    @And("^Horario options correct")
    public void horario_options_correct() {
        Select horario = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]")));
        List<WebElement> options = horario.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Cena", options.get(1).getText());
        assertEquals("Comida", options.get(2).getText());
    }

    @And("^Sala options correct")
    public void sala_options_correct() {
        Select sala = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[5]/select[1]")));
        List<WebElement> options = sala.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Patio", options.get(1).getText());
        assertEquals("Sala1", options.get(2).getText());
        assertEquals("Sala2", options.get(3).getText());
    }

    @And("^Localidad options correct")
    public void localidad_options_correct() {
        Select localidad = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[6]/select[1]")));
        List<WebElement> options = localidad.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Aielo de Malferit", options.get(1).getText());
        assertEquals("Albaida", options.get(2).getText());
        assertEquals("Olleria", options.get(3).getText());
    }

    @And("^Confirmar options correct")
    public void confirmar_options_correct() {
        Select confirmar = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[11]/select[1]")));
        List<WebElement> options = confirmar.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Sí", options.get(1).getText());
        assertEquals("No", options.get(2).getText());
    }

    @Then("^User see correct filter dates")
    public void user_see_correct_filter_dates() {
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date initMinDate = DateUtils.addMonths(new Date(), -1);
        Date initMaxDate = DateUtils.addMonths(new Date(), 1);

        String expectedMinDate = formatter.format(initMinDate);
        String expectedMaxDate = formatter.format(initMaxDate);

        assertEquals(expectedMinDate, fechaMin.getAttribute("value"));
        assertEquals(expectedMaxDate, fechaMax.getAttribute("value"));
    }
}
