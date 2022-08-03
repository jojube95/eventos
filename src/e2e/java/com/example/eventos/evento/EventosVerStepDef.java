package com.example.eventos.evento;

import com.example.eventos.connectors.WebConnector;
import io.cucumber.java.en.And;
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

import static org.junit.jupiter.api.Assertions.*;

public class EventosVerStepDef {
    private final WebConnector connector;

    public EventosVerStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Open Chrome and visit verEventos page$")
    public void open_the_chrome_and_visit_ver_eventos_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/verEventos");
    }

    @When("^User enter min date 2022-07-10$")
    public void user_enter_min_date(){
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));

        fechaMin.sendKeys("2022-07-10");

        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @When("^User enter max date 2022-07-16$")
    public void user_enter_max_date(){
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));
        fechaMax.sendKeys("2022-07-16");

        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @When("^User enter tipo Boda$")
    public void user_enter_tipo_boda(){
        WebElement tipoSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[2]/select[1]"));
        WebElement tipoBoda = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[2]/select[1]/option[2]"));
        tipoSelect.click();
        tipoBoda.click();
    }

    @When("^User enter horario Comida$")
    public void user_enter_horario_comida(){
        WebElement horarioSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]"));
        WebElement horarioComida = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]/option[3]"));
        horarioSelect.click();
        horarioComida.click();
    }

    @When("^User enter localidad Aielo de Malferit$")
    public void user_enter_localidad_aielo(){
        WebElement localidadSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]"));
        WebElement localidadAielo = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]/option[2]"));
        localidadSelect.click();
        localidadAielo.click();
    }

    @When("^User enter confirmado Si$")
    public void user_enter_confirmado_si(){
        WebElement confirmadoSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[9]/select[1]"));
        WebElement confirmadoBoda = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[9]/select[1]/option[2]"));
        confirmadoSelect.click();
        confirmadoBoda.click();
    }

    @When("^User enter horario Comida and localidad Olleria$")
    public void user_enter_horario_comida_localidad_olleria(){
        WebElement horarioSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]"));
        WebElement horarioComida = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]/option[3]"));
        horarioSelect.click();
        horarioComida.click();

        WebElement localidadSelect = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]"));
        WebElement localidadOlleria = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]/option[4]"));
        localidadSelect.click();
        localidadOlleria.click();
    }

    @When("^User click first event$")
    public void user_click_first_event(){
        WebElement evento1 = connector.getDriver().findElement(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]"));
        evento1.click();
    }

    @Then("^Table display all events with correct data$")
    public void table_display_correct_data() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[2]/td"));
        List<WebElement> evento3 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[3]/td"));
        List<WebElement> evento4 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(4, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda", evento1.get(1).getText());
        assertEquals("Cena", evento1.get(2).getText());
        assertEquals("Aielo de Malferit", evento1.get(3).getText());
        assertEquals("153", evento1.get(4).getText());
        assertEquals("80.0", evento1.get(5).getText());
        assertEquals("13", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("Si", evento1.get(8).getText());

        assertEquals("2022-07-10", evento2.get(0).getText());
        assertEquals("Comunion", evento2.get(1).getText());
        assertEquals("Comida", evento2.get(2).getText());
        assertEquals("Olleria", evento2.get(3).getText());
        assertEquals("43", evento2.get(4).getText());
        assertEquals("50.0", evento2.get(5).getText());
        assertEquals("18", evento2.get(6).getText());
        assertEquals("15.0", evento2.get(7).getText());
        assertEquals("No", evento2.get(8).getText());

        assertEquals("2022-07-16", evento3.get(0).getText());
        assertEquals("Evento individual", evento3.get(1).getText());
        assertEquals("Cena", evento3.get(2).getText());
        assertEquals("Aielo de Malferit", evento3.get(3).getText());
        assertEquals("90", evento3.get(4).getText());
        assertEquals("35.0", evento3.get(5).getText());
        assertEquals("23", evento3.get(6).getText());
        assertEquals("15.0", evento3.get(7).getText());
        assertEquals("Si", evento3.get(8).getText());

        assertEquals("2022-07-23", evento4.get(0).getText());
        assertEquals("Evento comunal", evento4.get(1).getText());
        assertEquals("Comida", evento4.get(2).getText());
        assertEquals("Albaida", evento4.get(3).getText());
        assertEquals("36", evento4.get(4).getText());
        assertEquals("40.0", evento4.get(5).getText());
        assertEquals("0", evento4.get(6).getText());
        assertEquals("0.0", evento4.get(7).getText());
        assertEquals("No", evento4.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("322", footer.get(1).getText());
        assertEquals("51.25", footer.get(2).getText());
        assertEquals("54", footer.get(3).getText());
        assertEquals("11.25", footer.get(4).getText());
        assertEquals("19790€", footer.get(5).getText());

    }

    @Then("^Three events shown$")
    public void three_events_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(3, eventos.size());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("169", footer.get(1).getText());
        assertEquals("41.67", footer.get(2).getText());
        assertEquals("41", footer.get(3).getText());
        assertEquals("10.00", footer.get(4).getText());
        assertEquals("7355€", footer.get(5).getText());
    }

    @Then("^Two events shown$")
    public void two_events_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(2, eventos.size());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("133", footer.get(1).getText());
        assertEquals("42.50", footer.get(2).getText());
        assertEquals("41", footer.get(3).getText());
        assertEquals("15.00", footer.get(4).getText());
        assertEquals("5915€", footer.get(5).getText());
    }

    @And("^Fecha min is void$")
    public void fecha_min_is_void() {
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));

        assertEquals("", fechaMin.getText());
    }

    @Then("^Evento boda is shown$")
    public void evento_boda_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(1, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda", evento1.get(1).getText());
        assertEquals("Cena", evento1.get(2).getText());
        assertEquals("Aielo de Malferit", evento1.get(3).getText());
        assertEquals("153", evento1.get(4).getText());
        assertEquals("80.0", evento1.get(5).getText());
        assertEquals("13", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("Si", evento1.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("153", footer.get(1).getText());
        assertEquals("80.00", footer.get(2).getText());
        assertEquals("13", footer.get(3).getText());
        assertEquals("15.00", footer.get(4).getText());
        assertEquals("12435€", footer.get(5).getText());
    }

    @Then("^Evento comida is shown$")
    public void evento_comida_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-10", evento1.get(0).getText());
        assertEquals("Comunion", evento1.get(1).getText());
        assertEquals("Comida", evento1.get(2).getText());
        assertEquals("Olleria", evento1.get(3).getText());
        assertEquals("43", evento1.get(4).getText());
        assertEquals("50.0", evento1.get(5).getText());
        assertEquals("18", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("No", evento1.get(8).getText());

        assertEquals("2022-07-23", evento2.get(0).getText());
        assertEquals("Evento comunal", evento2.get(1).getText());
        assertEquals("Comida", evento2.get(2).getText());
        assertEquals("Albaida", evento2.get(3).getText());
        assertEquals("36", evento2.get(4).getText());
        assertEquals("40.0", evento2.get(5).getText());
        assertEquals("0", evento2.get(6).getText());
        assertEquals("0.0", evento2.get(7).getText());
        assertEquals("No", evento2.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("79", footer.get(1).getText());
        assertEquals("45.00", footer.get(2).getText());
        assertEquals("18", footer.get(3).getText());
        assertEquals("7.50", footer.get(4).getText());
        assertEquals("3860€", footer.get(5).getText());
    }

    @Then("^Evento Aielo de Malferit is shown$")
    public void evento_aielo_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda", evento1.get(1).getText());
        assertEquals("Cena", evento1.get(2).getText());
        assertEquals("Aielo de Malferit", evento1.get(3).getText());
        assertEquals("153", evento1.get(4).getText());
        assertEquals("80.0", evento1.get(5).getText());
        assertEquals("13", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("Si", evento1.get(8).getText());

        assertEquals("2022-07-16", evento2.get(0).getText());
        assertEquals("Evento individual", evento2.get(1).getText());
        assertEquals("Cena", evento2.get(2).getText());
        assertEquals("Aielo de Malferit", evento2.get(3).getText());
        assertEquals("90", evento2.get(4).getText());
        assertEquals("35.0", evento2.get(5).getText());
        assertEquals("23", evento2.get(6).getText());
        assertEquals("15.0", evento2.get(7).getText());
        assertEquals("Si", evento2.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("243", footer.get(1).getText());
        assertEquals("57.50", footer.get(2).getText());
        assertEquals("36", footer.get(3).getText());
        assertEquals("15.00", footer.get(4).getText());
        assertEquals("15930€", footer.get(5).getText());
    }

    @Then("^Evento confirmado is shown$")
    public void evento_confirmado_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda", evento1.get(1).getText());
        assertEquals("Cena", evento1.get(2).getText());
        assertEquals("Aielo de Malferit", evento1.get(3).getText());
        assertEquals("153", evento1.get(4).getText());
        assertEquals("80.0", evento1.get(5).getText());
        assertEquals("13", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("Si", evento1.get(8).getText());

        assertEquals("2022-07-16", evento2.get(0).getText());
        assertEquals("Evento individual", evento2.get(1).getText());
        assertEquals("Cena", evento2.get(2).getText());
        assertEquals("Aielo de Malferit", evento2.get(3).getText());
        assertEquals("90", evento2.get(4).getText());
        assertEquals("35.0", evento2.get(5).getText());
        assertEquals("23", evento2.get(6).getText());
        assertEquals("15.0", evento2.get(7).getText());
        assertEquals("Si", evento2.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("243", footer.get(1).getText());
        assertEquals("57.50", footer.get(2).getText());
        assertEquals("36", footer.get(3).getText());
        assertEquals("15.00", footer.get(4).getText());
        assertEquals("15930€", footer.get(5).getText());
    }

    @Then("^Evento Comida and Olleria is shown$")
    public void evento_comida_olleria_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(1, eventos.size());

        assertEquals("2022-07-10", evento1.get(0).getText());
        assertEquals("Comunion", evento1.get(1).getText());
        assertEquals("Comida", evento1.get(2).getText());
        assertEquals("Olleria", evento1.get(3).getText());
        assertEquals("43", evento1.get(4).getText());
        assertEquals("50.0", evento1.get(5).getText());
        assertEquals("18", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("No", evento1.get(8).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("43", footer.get(1).getText());
        assertEquals("50.00", footer.get(2).getText());
        assertEquals("18", footer.get(3).getText());
        assertEquals("15.00", footer.get(4).getText());
        assertEquals("2420€", footer.get(5).getText());
    }

    @Then("^Redirect to verEvento page with currect evento$")
    public void redirect_to_ver_evento(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/verEvento?eventoId=62dc2a63ec628818203950b9"));
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
        Select tipo = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[2]/select[1]")));
        WebElement optionSelected = tipo.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Horario not selected")
    public void horario_not_selected() {
        Select horario = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]")));
        WebElement optionSelected = horario.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Localidad not selected")
    public void localidad_not_selected() {
        Select localidad = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]")));
        WebElement optionSelected = localidad.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Confirmar not selected")
    public void confirmar_not_selected() {
        Select confirmar = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[9]/select[1]")));
        WebElement optionSelected = confirmar.getFirstSelectedOption();
        assertEquals("", optionSelected.getText());
    }

    @And("^Tipo options correct")
    public void tipo_options_correct() {
        Select tipo = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[2]/select[1]")));
        List<WebElement> options = tipo.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Boda", options.get(1).getText());
        assertEquals("Comunion", options.get(2).getText());
        assertEquals("Evento comunal", options.get(3).getText());
        assertEquals("Evento individual", options.get(4).getText());
    }

    @And("^Horario options correct")
    public void horario_options_correct() {
        Select horario = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[3]/select[1]")));
        List<WebElement> options = horario.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Cena", options.get(1).getText());
        assertEquals("Comida", options.get(2).getText());
    }

    @And("^Localidad options correct")
    public void localidad_options_correct() {
        Select localidad = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[4]/select[1]")));
        List<WebElement> options = localidad.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Aielo de Malferit", options.get(1).getText());
        assertEquals("Albaida", options.get(2).getText());
        assertEquals("Olleria", options.get(3).getText());
    }

    @And("^Confirmar options correct")
    public void confirmar_options_correct() {
        Select confirmar = new Select(connector.getDriver().findElement(By.xpath("//table[@id='eventos']/thead[1]/tr[1]/th[9]/select[1]")));
        List<WebElement> options = confirmar.getOptions();

        assertEquals("", options.get(0).getText());
        assertEquals("Sí", options.get(1).getText());
        assertEquals("No", options.get(2).getText());
    }

}
