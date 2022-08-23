package com.example.eventos.stepDefs.protagonista;

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

public class ProtagonistaStepDef {
    private final WebConnector connector;

    public ProtagonistaStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit protagonistas page$")
    public void visit_ver_eventos_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/evento/protagonistas?eventoId=62dc2a63ec628818203950b9");
    }

    @When("^User click add button$")
    public void user_click_add_button() {
        WebElement addButton = connector.getDriver().findElement(By.id("botonAnyadir"));
        addButton.click();
    }

    @When("^User click delete button$")
    public void user_click_delete_button() {
        WebElement deleteButton = connector.getDriver().findElement(By.xpath("(//button[@eventoid='62dc2a63ec628818203950b9'])[2]"));
        deleteButton.click();
    }

    @When("^User fill all protagonista fields$")
    public void user_fill_all_protagonista_fields() {
        WebElement tipoSelect = connector.getDriver().findElement(By.id("tipo"));
        WebElement tipoPadreMadre = connector.getDriver().findElement(By.xpath("(//select[@id='tipo']//option)[3]"));

        WebElement nombre = connector.getDriver().findElement(By.id("nombre"));
        WebElement telefono = connector.getDriver().findElement(By.id("telefono"));
        WebElement correo = connector.getDriver().findElement(By.id("correo"));

        tipoSelect.click();
        tipoPadreMadre.click();
        nombre.sendKeys("Abelardo");
        telefono.sendKeys("678678678");
        correo.sendKeys("abelardo@gmail.com");

    }

    @And("^User click add protagonista button$")
    public void user_click_add_protagonista_button() {
        WebElement addProtagonistaButton = connector.getDriver().findElement(By.xpath("//button[@type='submit']"));
        addProtagonistaButton.click();
    }

    @Then("^Table should show two protagonists$")
    public void table_should_show_two_protagonists() {
        List<WebElement> protagonistas = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr"));

        List<WebElement> protagonista1 = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr[1]/td"));
        List<WebElement> protagonista2 = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr[2]/td"));

        assertEquals(2, protagonistas.size());

        assertEquals("Novio/a", protagonista1.get(0).getText());
        assertEquals("SAMUEL", protagonista1.get(1).getText());
        assertEquals("666278200", protagonista1.get(2).getText());
        assertEquals("q0ak2ovb88@blu.it", protagonista1.get(3).getText());

        assertEquals("Novio/a", protagonista2.get(0).getText());
        assertEquals("NAYARA", protagonista2.get(1).getText());
        assertEquals("633196776", protagonista2.get(2).getText());
        assertEquals("iyvepusa@lycos.nl", protagonista2.get(3).getText());
    }

    @Then("^Redirect to protagonista anyadir page$")
    public void redirect_to_add_protagonista_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/evento/protagonistas/anyadir?eventoId=62dc2a63ec628818203950b9"));
    }

    @Then("^Redirect to protagonistas page$")
    public void redirect_to_protagonistas_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/evento/protagonistas?eventoId=62dc2a63ec628818203950b9"));
    }

    @And("^Added protagonista should display$")
    public void added_protagonista_should_display() {
        List<WebElement> protagonistas = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr"));

        List<WebElement> protagonista3 = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr[3]/td"));

        assertEquals(3, protagonistas.size());

        assertEquals("Padre/Madre", protagonista3.get(0).getText());
        assertEquals("Abelardo", protagonista3.get(1).getText());
        assertEquals("678678678", protagonista3.get(2).getText());
        assertEquals("abelardo@gmail.com", protagonista3.get(3).getText());
    }

    @And("^Deleted protagonista shouldnt display$")
    public void deleted_protagonist_shouldnt_display() {
        List<WebElement> protagonistas = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr"));

        List<WebElement> protagonista1 = connector.getDriver().findElements(By.xpath("//table[@id='protagonistas']/tbody/tr[1]/td"));

        assertEquals(1, protagonistas.size());

        assertEquals("Novio/a", protagonista1.get(0).getText());
        assertEquals("NAYARA", protagonista1.get(1).getText());
        assertEquals("633196776", protagonista1.get(2).getText());
        assertEquals("iyvepusa@lycos.nl", protagonista1.get(3).getText());
    }

}
