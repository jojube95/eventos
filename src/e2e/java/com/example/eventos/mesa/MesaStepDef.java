package com.example.eventos.mesa;

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

public class MesaStepDef {
    private final WebConnector connector;

    public MesaStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Open Chrome and visit mesas page$")
    public void open_the_chrome_and_visit_mesas_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/evento/mesas?eventoId=62dc2a96ec628818203950ba");
    }

    @Then("^Table display all mesas$")
    public void table_display_all_mesas() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tbody/tr"));

        List<WebElement> mesa1 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td"));
        List<WebElement> mesa2 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tbody/tr[2]/td"));
        List<WebElement> mesa3 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td"));
        List<WebElement> mesa4 = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("/html/body/div[2]/div/table/tfoot/tr/th"));

        assertEquals(4, eventos.size());

        assertEquals("1", mesa1.get(0).getText());
        assertEquals("6", mesa1.get(1).getText());

        assertEquals("2", mesa2.get(0).getText());
        assertEquals("11", mesa2.get(1).getText());

        assertEquals("3", mesa3.get(0).getText());
        assertEquals("8", mesa3.get(1).getText());

        assertEquals("4", mesa4.get(0).getText());
        assertEquals("16", mesa4.get(1).getText());

        assertEquals("41", footer.get(1).getText());
    }

    @When("^User click first mesa$")
    public void user_click_firest_mesa(){
        WebElement firstMesa = connector.getDriver().findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]"));
        firstMesa.click();
    }

    @When("^User click add mesa button$")
    public void user_click_add(){
        WebElement add = connector.getDriver().findElement(By.xpath("(//button[@type='button'])[2]"));
        add.click();
    }

    @When("^User click edit mesa button$")
    public void user_click_edit(){
        WebElement edit = connector.getDriver().findElement(By.xpath("(//button[@type='button'])[3]"));
        edit.click();
    }

    @When("^User click delete mesa button$")
    public void user_click_delete(){
        WebElement delete = connector.getDriver().findElement(By.xpath("(//button[@type='button'])[4]"));
        delete.click();
    }

    @When("^User click invitados mesa button$")
    public void user_click_invitados(){
        WebElement invitados = connector.getDriver().findElement(By.xpath("(//button[@type='button'])[5]"));
        invitados.click();
    }

    @Then("^Add modal mesa should display$")
    public void add_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement addButton = connector.getDriver().findElement(By.id("addRowBtn"));
        assertTrue(addButton.isDisplayed());
    }

    @Then("^Edit modal mesa should display$")
    public void edit_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement editButton = connector.getDriver().findElement(By.id("editRowBtn"));
        assertTrue(editButton.isDisplayed());
    }

    @Then("^Delete modal mesa should display$")
    public void delete_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement deleteButton = connector.getDriver().findElement(By.id("deleteRowBtn"));
        assertTrue(deleteButton.isDisplayed());
    }

    @Then("^Invitados modal mesa should display$")
    public void invitado_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        WebElement invitados = connector.getDriver().findElement(By.id("invitados"));
        assertTrue(invitados.isDisplayed());
    }

    @And("^Edit modal data should be correct$")
    public void edit_modal_data_should_be_correct(){
        WebElement numeroInput = connector.getDriver().findElement(By.id("numero"));
        WebElement personasInput = connector.getDriver().findElement(By.id("personas"));

        assertEquals("1", numeroInput.getAttribute("value"));
        assertEquals("6", personasInput.getAttribute("value"));
    }
}
