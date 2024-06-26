package com.example.eventos.stepDefs.evento;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EventoVerStepDef {
    private final WebConnector connector;

    public EventoVerStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit eventoVer page$")
    public void visit_ver_evento_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get(connector.getBaseUrl() + "/eventoVer?eventoId=62dc2a63ec628818203950b9");
    }

    @Then("^Event should display correct data$")
    public void event_should_display_correct_data(){
        WebElement fecha = connector.getDriver().findElement(By.id("eventoFecha"));
        WebElement localidad = connector.getDriver().findElement(By.id("eventoLocalidad"));
        WebElement tipo = connector.getDriver().findElement(By.id("eventoTipo"));
        WebElement horario = connector.getDriver().findElement(By.id("eventoHorario"));
        WebElement sala = connector.getDriver().findElement(By.id("eventoSala"));
        WebElement titulo = connector.getDriver().findElement(By.id("eventoTitulo"));
        WebElement personas = connector.getDriver().findElement(By.id("eventoPersonas"));
        WebElement precioMenu = connector.getDriver().findElement(By.id("eventoPrecioMenu"));
        WebElement ninyos = connector.getDriver().findElement(By.id("eventoNinyos"));
        WebElement precioMenuNinyos = connector.getDriver().findElement(By.id("eventoPrecioMenuNinyos"));
        WebElement confirmado = connector.getDriver().findElement(By.id("eventoConfirmado"));

        assertEquals("2022-07-02", fecha.getText());
        assertEquals("Aielo de Malferit", localidad.getText());
        assertEquals("Boda", tipo.getText());
        assertEquals("Cena", horario.getText());
        assertEquals("Boda-Cena", titulo.getText());
        assertEquals("Sala1", sala.getText());
        assertEquals("153", personas.getText());
        assertEquals("80.0", precioMenu.getText());
        assertEquals("13", ninyos.getText());
        assertEquals("15.0", precioMenuNinyos.getText());
        assertEquals("Si", confirmado.getText());
    }

    @When("^User click action button$")
    public void user_click_action_button() {
        WebElement actionButton = connector.getDriver().findElement(By.id("dropdownMenuButton"));
        actionButton.click();
    }

    @When("^User click modificar action$")
    public void user_click_modificar_action() {
        WebElement modificarButton = connector.getDriver().findElement(By.id("modificarButton"));
        modificarButton.click();
    }

    @When("^User click mesas action$")
    public void user_click_mesas_action() {
        WebElement mesasButton = connector.getDriver().findElement(By.id("mesasButton"));
        mesasButton.click();
    }

    @When("^User click calcular personas action$")
    public void user_click_calcular_personas_action() {
        new WebDriverWait(connector.getDriver(), Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.id("calcularPersonasButton")));

        WebElement calcularPersonasButton = connector.getDriver().findElement(By.id("calcularPersonasButton"));
        calcularPersonasButton.click();
    }

    @When("^User click protagonistas action$")
    public void user_click_protagonistas_action() {
        WebElement protagonistasButton = connector.getDriver().findElement(By.id("protagonistasButton"));
        protagonistasButton.click();
    }

    @When("^User click empleados action$")
    public void user_click_empleados_action() {
        WebElement empleadosButton = connector.getDriver().findElement(By.id("empleadosButton"));
        empleadosButton.click();
    }

    @And("^User click eliminar action$")
    public void user_click_eliminar_action() {
        WebElement eliminarButton = connector.getDriver().findElement(By.id("eliminarButton"));
        eliminarButton.click();
    }

    @Then("^Page should redirect to modificar page with currect evento$")
    public void redirect_to_calendar_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(connector.getBaseUrl() + "/eventoUpdate?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Page should redirect to mesas page with currect evento$")
    public void redirect_to_mesas_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(connector.getBaseUrl() + "/evento/mesas?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Page should redirect to protagonistas page with currect evento$")
    public void redirect_to_protagonistas_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(connector.getBaseUrl() + "/evento/protagonistas?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Page should redirect to empelados page with currect evento$")
    public void redirect_to_empleados_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(connector.getBaseUrl() + "/evento/empleados?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Calcular personas modal should display$")
    public void calcular_personas_modal_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPersonasModal")));
    }

    @Then("^Eliminar evento modal should display$")
    public void eliminar_evento_modal_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmarEliminarModal")));
    }

    @And("^Calcular personas modal should display correct data$")
    public void calcular_personas_modal_should_display_correct_data(){
        WebElement modal_personas_header = connector.getDriver().findElement(By.xpath("(//div[@class='modal-header']//h5)[1]"));
        WebElement modal_personas_content1 = connector.getDriver().findElement(By.xpath("//div[@id='confirmPersonasModal']/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]"));
        WebElement modal_personas_content2 = connector.getDriver().findElement(By.xpath("//div[@id='confirmPersonasModal']/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]"));
        WebElement modal_personas_content3 = connector.getDriver().findElement(By.xpath("//div[@id='confirmPersonasModal']/div[1]/div[1]/div[2]/div[1]/div[3]/span[1]"));
        WebElement modal_personas_button_aceptar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[1]"));
        WebElement modal_personas_button_cancelar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[2]"));

        assertEquals("Resultado", modal_personas_header.getText());
        assertEquals("Personas calculadas: 142", modal_personas_content1.getText());
        assertEquals("Niños calculados: 10", modal_personas_content2.getText());
        assertEquals("¿Quiere actualizar el evento con estas personas?", modal_personas_content3.getText());
        assertEquals("Aceptar", modal_personas_button_aceptar.getText());
        assertEquals("Cancelar", modal_personas_button_cancelar.getText());
    }

    @And("^Eliminar evento modal content should be correct$")
    public void eliminar_modal_content_should_be_correct(){
        WebElement modal_eliminar_header = connector.getDriver().findElement(By.xpath("(//div[@class='modal-header']//h5)[1]"));
        WebElement modal_eliminar_content = connector.getDriver().findElement(By.xpath("(//div[@class='modal-content']//div)[2]"));
        WebElement modal_eliminar_button_cerrar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[1]"));
        WebElement modal_eliminar_button_eliminar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[2]"));

        assertEquals("Confirmar", modal_eliminar_header.getText());
        assertEquals("Está seguro de guardar los cambios?", modal_eliminar_content.getText());
        assertEquals("Cerrar", modal_eliminar_button_cerrar.getText());
        assertEquals("Eliminar", modal_eliminar_button_eliminar.getText());
    }

    @When("^User click aceptar$")
    public void user_click_aceptar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPersonasModal")));

        Thread.sleep(1000);

        WebElement modal_personas_button_aceptar = connector.getDriver().findElement(By.id("modalConfirmarPersonasAceptarButton"));
        modal_personas_button_aceptar.click();
    }

    @When("^User click cancelar")
    public void user_click_cancelar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);

        WebElement modal_personas_button_cancelar = connector.getDriver().findElement(By.id("modalConfirmarPersonasCancelarButton"));
        modal_personas_button_cancelar.click();
    }

    @When("^User click eliminar confirm")
    public void user_click_eliminar_confirm() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmarEliminarModal")));

        connector.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement modal_eliminar_button = connector.getDriver().findElement(By.id("modalEliminarButton"));
        modal_eliminar_button.click();
    }

    @When("^User click cerrar confirm")
    public void user_click_cerrar_confirm() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmarEliminarModal")));

        connector.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement modal_cerrar_button = connector.getDriver().findElement(By.id("modalEliminarCerrarButton"));
        modal_cerrar_button.click();
    }



    @Then("^Modal calcular personas should hide$")
    public void calcular_personas_modal_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
    }

    @Then("^Eliminar modal should hide$")
    public void eliminar_modal_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
    }

    @And("^Personas is asigned$")
    public void event_personas_is_recalculated(){
        WebElement personas = connector.getDriver().findElement(By.id("eventoPersonas"));
        WebElement ninyos = connector.getDriver().findElement(By.id("eventoNinyos"));

        WebDriverWait wait1 = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        WebDriverWait wait2 = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.textToBePresentInElement(personas, "142"));
        wait2.until(ExpectedConditions.textToBePresentInElement(ninyos, "10"));
    }

    @And("^Personas still same$")
    public void event_personas_isnt_recalculated() {
        WebElement personas = connector.getDriver().findElement(By.id("eventoPersonas"));

        connector.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        assertEquals("153", personas.getText());
    }


}
