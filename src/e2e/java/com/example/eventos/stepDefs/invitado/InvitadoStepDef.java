package com.example.eventos.stepDefs.invitado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvitadoStepDef {
    private final WebConnector connector;

    public InvitadoStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Then("^Invitados content should be correct$")
    public void invitados_content_should_be_correct() {
        List<WebElement> invitados = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr"));

        List<WebElement> invitados1 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[1]/td"));
        List<WebElement> invitados2 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[2]/td"));
        List<WebElement> invitados3 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[3]/td"));
        List<WebElement> invitados4 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[4]/td"));
        List<WebElement> invitados5 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[5]/td"));
        List<WebElement> invitados6 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[6]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tfoot/tr/td"));

        assertEquals(6, invitados.size());

        assertEquals("Niño1", invitados1.get(0).getText());
        assertEquals("Niño", invitados1.get(1).getText());
        assertEquals("Vegetariano", invitados1.get(2).getText());

        assertEquals("Niño2", invitados2.get(0).getText());
        assertEquals("Niño", invitados2.get(1).getText());
        assertEquals("", invitados2.get(2).getText());

        assertEquals("Niño3", invitados3.get(0).getText());
        assertEquals("Niño", invitados3.get(1).getText());
        assertEquals("Celiaco", invitados3.get(2).getText());

        assertEquals("Niño4", invitados4.get(0).getText());
        assertEquals("Niño", invitados4.get(1).getText());
        assertEquals("", invitados4.get(2).getText());

        assertEquals("Niño5", invitados5.get(0).getText());
        assertEquals("Niño", invitados5.get(1).getText());
        assertEquals("", invitados5.get(2).getText());

        assertEquals("Niño6", invitados6.get(0).getText());
        assertEquals("Niño", invitados6.get(1).getText());
        assertEquals("", invitados6.get(2).getText());

        assertEquals("Personas: 6", footer.get(0).getText());
    }

    @When("^User click add invitado button$")
    public void user_click_add(){
        WebElement add = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[5]"));
        add.click();
    }

    @When("^User click edit invitado button$")
    public void user_click_edit(){
        WebElement edit = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[6]"));
        edit.click();
    }

    @When("^User click delete invitado button$")
    public void user_click_delete(){
        WebElement delete = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[7]"));
        delete.click();
    }

    @When("^User click first invitado$")
    public void user_click_first_invitado(){
        WebElement invitado = connector.getDriver().findElement(By.xpath("//table[@id='invitados']/tbody/tr[1]"));
        invitado.click();
    }

    @When("^User fill add invitado form$")
    public void user_fill_add_invitado_form(){
        WebElement nombre = connector.getDriver().findElement(By.id("nombre"));
        WebElement descripcion = connector.getDriver().findElement(By.id("descripcion"));

        nombre.sendKeys("Antonio");
        descripcion.sendKeys("Alergico");
    }

    @When("^User fill edit invitado form$")
    public void user_fill_edit_invitado_form(){
        WebElement nombre = connector.getDriver().findElement(By.id("nombre"));
        WebElement descripcion = connector.getDriver().findElement(By.id("descripcion"));

        nombre.sendKeys(Keys.BACK_SPACE);
        nombre.sendKeys("20");
        descripcion.sendKeys("Celiaco");
    }

    @And("^User click add invitado modal button$")
    public void user_click_add_invitado_modal_button$() {
        WebElement addButton = connector.getDriver().findElement(By.id("addRowBtn"));
        addButton.click();
    }

    @And("^User click edit invitado modal button$")
    public void user_click_edit_invitado_modal_button$() {
        WebElement editButton = connector.getDriver().findElement(By.id("editRowBtn"));
        editButton.click();
    }

    @And("^User click delete invitado modal button$")
    public void user_click_delete_invitado_modal_button$() {
        WebElement deleteButton = connector.getDriver().findElement(By.id("deleteRowBtn"));
        deleteButton.click();
    }

    @Then("^Add invitado modal should display$")
    public void add_modal_invitado_should_display$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addRowBtn")));
    }

    @Then("^Add invitado modal should hide")
    public void add_modal_invitado_should_hide$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addRowBtn")));
    }

    @Then("^Edit invitado modal should display$")
    public void edit_modal_invitado_should_display$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editRowBtn")));
    }

    @Then("^Edit invitado modal should hide")
    public void edit_modal_invitado_should_hide$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("editRowBtn")));
    }

    @Then("^Delete invitado modal should display$")
    public void delete_modal_invitado_should_display$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteRowBtn")));
    }

    @Then("^Delete invitado modal should hide")
    public void delete_modal_invitado_should_hide$() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteRowBtn")));
    }

    @Then("^Invitados modal mesa should display$")
    public void invitados_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invitados")));
    }

    @And("^New invitado should display on table$")
    public void added_invitado_should_display() {
        List<WebElement> invitados = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr"));

        List<WebElement> invitados1 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[1]/td"));
        List<WebElement> invitados2 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[2]/td"));
        List<WebElement> invitados3 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[3]/td"));
        List<WebElement> invitados4 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[4]/td"));
        List<WebElement> invitados5 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[5]/td"));
        List<WebElement> invitados6 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[6]/td"));
        List<WebElement> invitados7 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[7]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tfoot/tr/td"));

        assertEquals(7, invitados.size());

        assertEquals("Niño1", invitados1.get(0).getText());
        assertEquals("Niño", invitados1.get(1).getText());
        assertEquals("Vegetariano", invitados1.get(2).getText());

        assertEquals("Niño2", invitados2.get(0).getText());
        assertEquals("Niño", invitados2.get(1).getText());
        assertEquals("", invitados2.get(2).getText());

        assertEquals("Niño3", invitados3.get(0).getText());
        assertEquals("Niño", invitados3.get(1).getText());
        assertEquals("Celiaco", invitados3.get(2).getText());

        assertEquals("Niño4", invitados4.get(0).getText());
        assertEquals("Niño", invitados4.get(1).getText());
        assertEquals("", invitados4.get(2).getText());

        assertEquals("Niño5", invitados5.get(0).getText());
        assertEquals("Niño", invitados5.get(1).getText());
        assertEquals("", invitados5.get(2).getText());

        assertEquals("Niño6", invitados6.get(0).getText());
        assertEquals("Niño", invitados6.get(1).getText());
        assertEquals("", invitados6.get(2).getText());

        assertEquals("Antonio", invitados7.get(0).getText());
        assertEquals("Mayor", invitados7.get(1).getText());
        assertEquals("Alergico", invitados7.get(2).getText());

        assertEquals("Personas: 7", footer.get(0).getText());
    }

    @And("^Edited invitado should display on table$")
    public void edited_invitado_should_display() {
        List<WebElement> invitados = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr"));

        List<WebElement> invitados1 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[1]/td"));
        List<WebElement> invitados2 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[2]/td"));
        List<WebElement> invitados3 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[3]/td"));
        List<WebElement> invitados4 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[4]/td"));
        List<WebElement> invitados5 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[5]/td"));
        List<WebElement> invitados6 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[6]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tfoot/tr/td"));

        assertEquals(6, invitados.size());

        assertEquals("Niño20", invitados1.get(0).getText());
        assertEquals("Niño", invitados1.get(1).getText());
        assertEquals("VegetarianoCeliaco", invitados1.get(2).getText());

        assertEquals("Niño2", invitados2.get(0).getText());
        assertEquals("Niño", invitados2.get(1).getText());
        assertEquals("", invitados2.get(2).getText());

        assertEquals("Niño3", invitados3.get(0).getText());
        assertEquals("Niño", invitados3.get(1).getText());
        assertEquals("Celiaco", invitados3.get(2).getText());

        assertEquals("Niño4", invitados4.get(0).getText());
        assertEquals("Niño", invitados4.get(1).getText());
        assertEquals("", invitados4.get(2).getText());

        assertEquals("Niño5", invitados5.get(0).getText());
        assertEquals("Niño", invitados5.get(1).getText());
        assertEquals("", invitados5.get(2).getText());

        assertEquals("Niño6", invitados6.get(0).getText());
        assertEquals("Niño", invitados6.get(1).getText());
        assertEquals("", invitados6.get(2).getText());

        assertEquals("Personas: 6", footer.get(0).getText());
    }

    @And("^Deleted invitado shouldnt display on table$")
    public void deleted_invitado_shouldnt_display() {
        List<WebElement> invitados = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr"));

        List<WebElement> invitados2 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[1]/td"));
        List<WebElement> invitados3 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[2]/td"));
        List<WebElement> invitados4 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[3]/td"));
        List<WebElement> invitados5 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[4]/td"));
        List<WebElement> invitados6 = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tbody/tr[5]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='invitados']/tfoot/tr/td"));

        assertEquals(5, invitados.size());

        assertEquals("Niño2", invitados2.get(0).getText());
        assertEquals("Niño", invitados2.get(1).getText());
        assertEquals("", invitados2.get(2).getText());

        assertEquals("Niño3", invitados3.get(0).getText());
        assertEquals("Niño", invitados3.get(1).getText());
        assertEquals("Celiaco", invitados3.get(2).getText());

        assertEquals("Niño4", invitados4.get(0).getText());
        assertEquals("Niño", invitados4.get(1).getText());
        assertEquals("", invitados4.get(2).getText());

        assertEquals("Niño5", invitados5.get(0).getText());
        assertEquals("Niño", invitados5.get(1).getText());
        assertEquals("", invitados5.get(2).getText());

        assertEquals("Niño6", invitados6.get(0).getText());
        assertEquals("Niño", invitados6.get(1).getText());
        assertEquals("", invitados6.get(2).getText());

        assertEquals("Personas: 5", footer.get(0).getText());
    }
}
