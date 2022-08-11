package com.example.eventos.evento;

import com.example.eventos.connectors.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoModificarStepDef {
    private final WebConnector connector;

    public EventoModificarStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Open Chrome and visit updateEvento page$")
    public void open_the_chrome_and_visit_update_evento_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/updateEvento?eventoId=62dc2a63ec628818203950b9");
    }

    @When("^User click modificar evento$")
    public void user_click_modificar_button() {
        WebElement modificarButton = connector.getDriver().findElement(By.xpath("//button[@type='submit']"));
        modificarButton.click();
    }

    @When("^User remove localidad input$")
    public void user_remove_localidad_input() {
        WebElement localidadInput = connector.getDriver().findElement(By.id("localidad"));
        localidadInput.clear();
    }

    @When("^User remove fecha input$")
    public void user_remove_fecha_input() {
        WebElement fechaInput = connector.getDriver().findElement(By.id("fecha"));
        fechaInput.clear();
    }

    @When("^User select horario to comida")
    public void user_select_horario_comida() {
        WebElement horarioSelect = connector.getDriver().findElement(By.id("horario"));
        WebElement comidaOption = connector.getDriver().findElement(By.xpath("(//select[@id='horario']//option)[1]"));
        horarioSelect.click();
        comidaOption.click();
    }

    @When("^User edit inputs$")
    public void user_edit_form_inputs() {
        WebElement fecha = connector.getDriver().findElement(By.id("fecha"));
        WebElement localidad = connector.getDriver().findElement(By.id("localidad"));
        WebElement tipo = connector.getDriver().findElement(By.id("tipo"));
        WebElement optionComunion = connector.getDriver().findElement(By.xpath("(//select[@id='tipo']//option)[2]"));
        WebElement horario = connector.getDriver().findElement(By.id("horario"));
        WebElement optionComida = connector.getDriver().findElement(By.xpath("//select[@id='horario']//option[1]"));
        WebElement personas = connector.getDriver().findElement(By.id("personas"));
        WebElement precioMenu = connector.getDriver().findElement(By.id("precioMenu"));
        WebElement ninyos = connector.getDriver().findElement(By.id("ninyos"));
        WebElement precioMenuNinyos = connector.getDriver().findElement(By.id("precioMenuNinyos"));
        WebElement confirmado = connector.getDriver().findElement(By.id("confirmado"));
        WebElement optionNo = connector.getDriver().findElement(By.xpath("(//select[@id='confirmado']//option)[2]"));
        WebElement offTitulo = connector.getDriver().findElement(By.xpath("html[1]/body[1]/div[2]/form[1]/div[5]/div[3]"));


        fecha.clear();
        fecha.sendKeys("2022-07-18");

        offTitulo.click();

        localidad.clear();
        localidad.sendKeys("Bufali");

        tipo.click();
        optionComunion.click();

        horario.click();
        optionComida.click();

        personas.clear();
        personas.sendKeys("155");

        precioMenu.clear();
        precioMenu.sendKeys("85.0");

        ninyos.clear();
        ninyos.sendKeys("17");

        precioMenuNinyos.clear();
        precioMenuNinyos.sendKeys("15.5");

        confirmado.click();
        optionNo.click();
    }

    @Then("^Event data should be correct$")
    public void event_data_should_be_correct(){
        String actualFecha = connector.getDriver().findElement(By.id("fecha")).getAttribute("value");
        String actualLocalidad = connector.getDriver().findElement(By.id("localidad")).getAttribute("value");
        String actualTipo = connector.getDriver().findElement(By.id("tipo")).getAttribute("value");
        String actualHorario = connector.getDriver().findElement(By.id("horario")).getAttribute("value");
        String actualTitulo = connector.getDriver().findElement(By.id("titulo")).getAttribute("value");
        String actualPersonas = connector.getDriver().findElement(By.id("personas")).getAttribute("value");
        String actualPrecioMenu = connector.getDriver().findElement(By.id("precioMenu")).getAttribute("value");
        String actualNinyos = connector.getDriver().findElement(By.id("ninyos")).getAttribute("value");
        String actualPrecioMenuNinyos = connector.getDriver().findElement(By.id("precioMenuNinyos")).getAttribute("value");
        String actualConfirmado = connector.getDriver().findElement(By.id("confirmado")).getAttribute("value");

        String expectedFecha = "2022-07-02";
        String expectedLocalidad = "Aielo de Malferit";
        String expectedTipo = "Boda";
        String expectedHorario = "Cena";
        String expectedTitulo = "Boda-Cena";
        String expectedPersonas = "153";
        String expectedPrecioMenu = "80.0";
        String expectedNinyos = "13";
        String expectedPrecioMenuNinyos = "15.0";
        String expectedConfirmado = "true";

        assertEquals(expectedFecha, actualFecha);
        assertEquals(expectedLocalidad, actualLocalidad);
        assertEquals(expectedTipo, actualTipo);
        assertEquals(expectedHorario, actualHorario);
        assertEquals(expectedTitulo, actualTitulo);
        assertEquals(expectedPersonas, actualPersonas);
        assertEquals(expectedPrecioMenu, actualPrecioMenu);
        assertEquals(expectedNinyos, actualNinyos);
        assertEquals(expectedPrecioMenuNinyos, actualPrecioMenuNinyos);
        assertEquals(expectedConfirmado, actualConfirmado);
    }

    @Then("^Titulo should be Comunion-Cena$")
    public void titulo_should_be_comunion_cena(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Comunion-Cena", tituloInput.getAttribute("value"));
    }

    @Then("^Titulo should be Boda-Comida$")
    public void titulo_should_be_boda_comida(){
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Boda-Comida", tituloInput.getAttribute("value"));
    }

    @And("^Keep on updateEvento page$")
    public void keep_on_updateEvento_page() throws InterruptedException {
        Thread.sleep(500);
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/updateEvento?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Titulo fill with selected horario Sopar nadal-Cena$")
    public void titulo_fill_with_selected_horario_sopar_nada_cena() {
        WebElement tituloInput = connector.getDriver().findElement(By.id("titulo"));
        assertEquals("Sopar nadal-Cena", tituloInput.getAttribute("value"));
    }

    @And("^User click outside titulo input$")
    public void user_click_off_titulo_input(){
        WebElement offTitulo = connector.getDriver().findElement(By.xpath("html[1]/body[1]/div[2]/form[1]/div[5]/div[3]"));
        offTitulo.click();
    }

    @And("^Edited content should be correct$")
    public void edited_evento_should_be_correct() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[2]/td"));
        List<WebElement> evento3 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[3]/td"));
        List<WebElement> evento4 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tfoot/tr/th"));

        assertEquals(4, eventos.size());

        assertEquals("2022-07-10", evento1.get(0).getText());
        assertEquals("Comunion", evento1.get(1).getText());
        assertEquals("Comida", evento1.get(2).getText());
        assertEquals("Olleria", evento1.get(3).getText());
        assertEquals("43", evento1.get(4).getText());
        assertEquals("50.0", evento1.get(5).getText());
        assertEquals("18", evento1.get(6).getText());
        assertEquals("15.0", evento1.get(7).getText());
        assertEquals("No", evento1.get(8).getText());

        assertEquals("2022-07-16", evento2.get(0).getText());
        assertEquals("Evento individual", evento2.get(1).getText());
        assertEquals("Cena", evento2.get(2).getText());
        assertEquals("Aielo de Malferit", evento2.get(3).getText());
        assertEquals("90", evento2.get(4).getText());
        assertEquals("35.0", evento2.get(5).getText());
        assertEquals("23", evento2.get(6).getText());
        assertEquals("15.0", evento2.get(7).getText());
        assertEquals("Si", evento2.get(8).getText());

        assertEquals("2022-07-18", evento3.get(0).getText());
        assertEquals("Comunion", evento3.get(1).getText());
        assertEquals("Comida", evento3.get(2).getText());
        assertEquals("Bufali", evento3.get(3).getText());
        assertEquals("155", evento3.get(4).getText());
        assertEquals("85.0", evento3.get(5).getText());
        assertEquals("17", evento3.get(6).getText());
        assertEquals("15.5", evento3.get(7).getText());
        assertEquals("No", evento3.get(8).getText());

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
        assertEquals("324", footer.get(1).getText());
        assertEquals("52.50", footer.get(2).getText());
        assertEquals("58", footer.get(3).getText());
        assertEquals("11.38", footer.get(4).getText());
        assertEquals("20793.5€", footer.get(5).getText());
    }
}
