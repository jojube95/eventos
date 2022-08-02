package com.example.eventos.evento;

import com.example.eventos.connectors.WebConnector;
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

    @Given("^Open Chrome and visit verEvento page$")
    public void open_the_chrome_and_visit_ver_evento_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/verEvento?eventoId=62dc2a63ec628818203950b9");
    }

    @Then("^Event should display correct data$")
    public void event_should_display_correct_data(){
        WebElement fecha = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[1]"));
        WebElement localidad = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[2]"));
        WebElement tipo = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[3]"));
        WebElement horario = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[4]"));
        WebElement titulo = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[5]"));
        WebElement personas = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[6]"));
        WebElement precioMenu = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[7]"));
        WebElement ninyos = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[8]"));
        WebElement precioMenuNinyos = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[9]"));
        WebElement confirmado = connector.getDriver().findElement(By.xpath("//input[@checked='checked']/following-sibling::label[1]"));

        assertEquals("2022-07-02", fecha.getText());
        assertEquals("Aielo de Malferit", localidad.getText());
        assertEquals("Boda", tipo.getText());
        assertEquals("Cena", horario.getText());
        assertEquals("Boda-Cena", titulo.getText());
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
        WebElement modificarButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[1]"));
        modificarButton.click();
    }

    @When("^User click mesas action$")
    public void user_click_mesas_action() {
        WebElement mesasButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[2]"));
        mesasButton.click();
    }

    @When("^User click calcular personas action$")
    public void user_click_calcular_personas_action() {
        WebElement calcularPersonasButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[3]"));
        calcularPersonasButton.click();
    }

    @When("^User click protagonistas action$")
    public void user_click_protagonistas_action() {
        WebElement protagonistasButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[4]"));
        protagonistasButton.click();
    }

    @And("^User click eliminar action$")
    public void user_click_eliminar_action() {
        WebElement eliminarButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[5]"));
        eliminarButton.click();
    }

    @Then("^Should display correct action buttons$")
    public void should_display_correct_action_buttons(){
        WebElement dropdownMenuButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']"));
        List<WebElement> options = dropdownMenuButton.findElements(By.tagName("a"));

        assertEquals(5, options.size());
        assertEquals("Modificar", options.get(0).getText());
        assertEquals("Mesas", options.get(1).getText());
        assertEquals("Calcular personas", options.get(2).getText());
        assertEquals("Protagonistas", options.get(3).getText());
        assertEquals("Eliminar", options.get(4).getText());
    }

    @Then("^Page should redirect to modificar page with currect evento$")
    public void redirect_to_calendar_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/updateEvento?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Redirect to verEventos page$")
    public void redirect_to_verEventos_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/verEventos"));
    }

    @Then("^Page should redirect to mesas page with currect evento$")
    public void redirect_to_mesas_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/evento/mesas?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Page should redirect to protagonistas page with currect evento$")
    public void redirect_to_protagonistas_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/evento/protagonistas?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Calcular personas modal should display$")
    public void calcular_personas_modal_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement modal_detail = connector.getDriver().findElement(By.xpath("(//div[@class='modal-content'])[1]"));
        assertTrue(modal_detail.isDisplayed());
    }

    @Then("^Eliminar evento modal should display$")
    public void eliminar_evento_modal_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement modal_detail = connector.getDriver().findElement(By.xpath("(//div[@class='modal-content'])[1]"));
        assertTrue(modal_detail.isDisplayed());
    }

    @And("^Calcular personas modal should display correct data$")
    public void calcular_personas_modal_should_display_correct_data(){
        WebElement modal_personas_header = connector.getDriver().findElement(By.xpath("(//div[@class='modal-header']//h5)[1]"));
        WebElement modal_personas_content1 = connector.getDriver().findElement(By.xpath("//div[@id='confirmPersonasModal']/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]"));
        WebElement modal_personas_content2 = connector.getDriver().findElement(By.xpath("//div[@id='confirmPersonasModal']/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]"));
        WebElement modal_personas_button_aceptar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[1]"));
        WebElement modal_personas_button_cancelar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[2]"));

        assertEquals("Resultado", modal_personas_header.getText());
        assertEquals("Personas calculadas: 148", modal_personas_content1.getText());
        assertEquals("¿Quiere actualizar el evento con estas personas?", modal_personas_content2.getText());
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

    @And("^Deleted event not in list verEventos$")
    public void deleted_event_not_in_list(){
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div/table/tbody/tr"));
        WebElement totalPersonas = connector.getDriver().findElement(By.xpath("//table[@id='eventos']/tfoot[1]/tr[1]/th[2]"));

        assertEquals(3, eventos.size());
        assertEquals("169", totalPersonas.getText());
    }

    @When("^User click aceptar$")
    public void user_click_aceptar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement modal_personas_button_aceptar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[1]"));
        modal_personas_button_aceptar.click();
    }

    @When("^User click cancelar")
    public void user_click_cancelar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement modal_personas_button_cancelar = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[2]"));
        modal_personas_button_cancelar.click();
    }

    @When("^User click eliminar confirm")
    public void user_click_eliminar_confirm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement modal_eliminar_button = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[2]"));
        modal_eliminar_button.click();
    }

    @When("^User click cerrar confirm")
    public void user_click_cerrar_confirm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement modal_cerrar_button = connector.getDriver().findElement(By.xpath("(//div[@class='modal-footer']//button)[1]"));
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
        WebElement personas = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[6]"));

        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(personas, "148"));
    }

    @And("^Personas still same$")
    public void event_personas_isnt_recalculated() throws InterruptedException {
        WebElement personas = connector.getDriver().findElement(By.xpath("(//p[@class='font-weight-bold']/following-sibling::p)[6]"));

        Thread.sleep(5000);

        assertEquals("153", personas.getText());
    }


}
