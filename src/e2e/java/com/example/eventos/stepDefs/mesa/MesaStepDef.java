package com.example.eventos.stepDefs.mesa;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

public class MesaStepDef {
    private final WebConnector connector;

    public MesaStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit mesas page$")
    public void visit_mesas_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get(connector.getBaseUrl() + "/evento/mesas?eventoId=62dc2a96ec628818203950ba");
    }

    @Then("^Table display all mesas$")
    public void table_display_all_mesas() {
        List<WebElement> mesas = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr"));

        List<WebElement> mesa1 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[1]/td"));
        List<WebElement> mesa2 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[2]/td"));
        List<WebElement> mesa3 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[3]/td"));
        List<WebElement> mesa4 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tfoot/tr/td"));

        assertEquals(4, mesas.size());

        assertEquals("1", mesa1.get(0).getText());
        assertEquals("0", mesa1.get(1).getText());
        assertEquals("6", mesa1.get(2).getText());
        assertEquals("Licores", mesa1.get(3).getText());

        assertEquals("2", mesa2.get(0).getText());
        assertEquals("11", mesa2.get(1).getText());
        assertEquals("0", mesa2.get(2).getText());
        assertEquals("Botellas", mesa2.get(3).getText());

        assertEquals("3", mesa3.get(0).getText());
        assertEquals("8", mesa3.get(1).getText());
        assertEquals("1", mesa3.get(2).getText());
        assertEquals("", mesa3.get(3).getText());

        assertEquals("4", mesa4.get(0).getText());
        assertEquals("16", mesa4.get(1).getText());
        assertEquals("0", mesa4.get(2).getText());
        assertEquals("", mesa4.get(3).getText());

        assertEquals("35", footer.get(1).getText());
        assertEquals("7", footer.get(2).getText());
    }

    @When("^User click first mesa$")
    public void user_click_firest_mesa() {
        WebElement firstMesa = connector.getDriver().findElement(By.xpath("//table[@id='mesas']/tbody/tr[1]"));
        firstMesa.click();
    }

    @When("^User click add mesa button$")
    public void user_click_add(){
        WebElement add = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[1]"));
        add.click();
    }

    @When("^User click edit mesa button$")
    public void user_click_edit(){
        WebElement edit = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[2]"));
        edit.click();
    }

    @When("^User click delete mesa button$")
    public void user_click_delete() {
        WebElement delete = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[3]"));
        delete.click();
    }

    @When("^User click invitados mesa button$")
    public void user_click_invitados(){
        WebElement invitados = connector.getDriver().findElement(By.xpath("(//div[@class='dt-buttons']//button)[4]"));
        invitados.click();
    }

    @When("^User fill add mesa form$")
    public void user_fill_add_mesa_form(){
        WebElement personasInput = connector.getDriver().findElement(By.id("mayores"));
        WebElement ninyosInput = connector.getDriver().findElement(By.id("ninyos"));
        WebElement descripcionInput = connector.getDriver().findElement(By.id("descripcion"));


        personasInput.sendKeys("9");
        ninyosInput.sendKeys("2");
        descripcionInput.sendKeys("Botella");
    }

    @When("^User fill edit mesa form$")
    public void user_fill_edit_mesa_form(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editRowBtn")));

        WebElement numeroInput = connector.getDriver().findElement(By.id("numero"));
        WebElement personasInput = connector.getDriver().findElement(By.id("mayores"));
        WebElement ninyosInput = connector.getDriver().findElement(By.id("ninyos"));
        WebElement descripcionInput = connector.getDriver().findElement(By.id("descripcion"));

        numeroInput.sendKeys(Keys.BACK_SPACE);
        personasInput.sendKeys(Keys.BACK_SPACE);
        ninyosInput.sendKeys(Keys.BACK_SPACE);

        numeroInput.sendKeys("5");
        personasInput.sendKeys("9");
        ninyosInput.sendKeys("4");
        descripcionInput.sendKeys("Buenas");

    }

    @When("^User click delete mesa confirm button$")
    public void user_delete_mesa_confirm_button(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteRowBtn")));

        WebElement deleteButton = connector.getDriver().findElement(By.id("deleteRowBtn"));
        deleteButton.click();
    }

    @And("^User click add mesa$")
    public void user_click_add_mesa(){
        WebElement addButton = connector.getDriver().findElement(By.id("addRowBtn"));
        addButton.click();
    }

    @Then("^Add modal mesa should display$")
    public void add_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addRowBtn")));
    }

    @Then("^Type mesa modal should display$")
    public void type_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonRedonda")));
    }

    @Then("^User click on redonda$")
    public void user_click_redonda(){
        WebElement redondaButton = connector.getDriver().findElement(By.id("buttonRedonda"));
        redondaButton.click();
    }

    @Then("^Add modal mesa should hide$")
    public void add_modal_mesa_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addRowBtn")));
    }

    @Then("^Edit modal mesa should display$")
    public void edit_modal_mesa_should_display(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
    }

    @Then("^Edit modal mesa should hide")
    public void edit_modal_mesa_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
    }

    @Then("^Delete modal mesa should hide")
    public void delete_modal_mesa_should_hide(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
    }

    @And("^User click edit modal mesa$")
    public void user_click_edit_modal_mesa(){
        WebElement editButton = connector.getDriver().findElement(By.id("editRowBtn"));
        editButton.click();
    }

    @And("^Edit modal data should be correct$")
    public void edit_modal_data_should_be_correct(){
        WebElement numeroInput = connector.getDriver().findElement(By.id("numero"));
        WebElement personasInput = connector.getDriver().findElement(By.id("mayores"));
        WebElement ninyosInput = connector.getDriver().findElement(By.id("ninyos"));

        assertEquals("1", numeroInput.getAttribute("value"));
        assertEquals("6", personasInput.getAttribute("value"));
        assertEquals("", ninyosInput.getAttribute("value"));
    }

    @And("^Add modal mesa content should have max table number$")
    public void add_modal_data_should_have_max_table_number(){
        WebElement numeroInput = connector.getDriver().findElement(By.id("numero"));
        WebElement mayoresInput = connector.getDriver().findElement(By.id("mayores"));
        WebElement ninyosInput = connector.getDriver().findElement(By.id("ninyos"));

        assertEquals("5", numeroInput.getAttribute("value"));
        assertEquals("", mayoresInput.getAttribute("value"));
        assertEquals("", ninyosInput.getAttribute("value"));
    }

    @And("^New mesa display on table$")
    public void new_mesa_display_on_table() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='mesas']/tbody[1]/tr[5]")));

        List<WebElement> mesas = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr"));

        List<WebElement> mesa1 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[1]/td"));
        List<WebElement> mesa2 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[2]/td"));
        List<WebElement> mesa3 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[3]/td"));
        List<WebElement> mesa4 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[4]/td"));
        List<WebElement> mesa5 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[5]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tfoot/tr/td"));

        assertEquals(5, mesas.size());

        assertEquals("1", mesa1.get(0).getText());
        assertEquals("0", mesa1.get(1).getText());
        assertEquals("6", mesa1.get(2).getText());
        assertEquals("Licores", mesa1.get(3).getText());

        assertEquals("2", mesa2.get(0).getText());
        assertEquals("11", mesa2.get(1).getText());
        assertEquals("0", mesa2.get(2).getText());
        assertEquals("Botellas", mesa2.get(3).getText());

        assertEquals("3", mesa3.get(0).getText());
        assertEquals("8", mesa3.get(1).getText());
        assertEquals("1", mesa3.get(2).getText());
        assertEquals("", mesa3.get(3).getText());

        assertEquals("4", mesa4.get(0).getText());
        assertEquals("16", mesa4.get(1).getText());
        assertEquals("0", mesa4.get(2).getText());
        assertEquals("", mesa4.get(3).getText());

        assertEquals("5", mesa5.get(0).getText());
        assertEquals("9", mesa5.get(1).getText());
        assertEquals("2", mesa5.get(2).getText());
        assertEquals("Botella", mesa5.get(3).getText());

        assertEquals("44", footer.get(1).getText());
        assertEquals("9", footer.get(2).getText());
    }

    @And("^Edited mesa display on table$")
    public void edited_mesa_display_on_table() {
        WebElement columnaEditada = connector.getDriver().findElement(By.xpath("//table[@id='mesas']/tbody[1]/tr[1]/td[1]"));

        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.textToBePresentInElement(columnaEditada, "5"));

        List<WebElement> mesas = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr"));

        List<WebElement> mesa1 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[1]/td"));
        List<WebElement> mesa2 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[2]/td"));
        List<WebElement> mesa3 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[3]/td"));
        List<WebElement> mesa4 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[4]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tfoot/tr/td"));

        assertEquals(4, mesas.size());

        assertEquals("5", mesa1.get(0).getText());
        assertEquals("9", mesa1.get(1).getText());
        assertEquals("4", mesa1.get(2).getText());
        assertEquals("LicoresBuenas", mesa1.get(3).getText());

        assertEquals("2", mesa2.get(0).getText());
        assertEquals("11", mesa2.get(1).getText());
        assertEquals("0", mesa2.get(2).getText());
        assertEquals("Botellas", mesa2.get(3).getText());

        assertEquals("3", mesa3.get(0).getText());
        assertEquals("8", mesa3.get(1).getText());
        assertEquals("1", mesa3.get(2).getText());
        assertEquals("", mesa3.get(3).getText());

        assertEquals("4", mesa4.get(0).getText());
        assertEquals("16", mesa4.get(1).getText());
        assertEquals("0", mesa4.get(2).getText());
        assertEquals("", mesa4.get(3).getText());

        assertEquals("44", footer.get(1).getText());
        assertEquals("5", footer.get(2).getText());
    }

    @And("^First mesa should be deleted$")
    public void first_mesa_should_be_deleted() {
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table[@id='mesas']/tbody[1]/tr[4]")));

        List<WebElement> mesas = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr"));

        List<WebElement> mesa2 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[1]/td"));
        List<WebElement> mesa3 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[2]/td"));
        List<WebElement> mesa4 = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tbody/tr[3]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='mesas']/tfoot/tr/td"));

        assertEquals(3, mesas.size());

        assertEquals("2", mesa2.get(0).getText());
        assertEquals("11", mesa2.get(1).getText());
        assertEquals("0", mesa2.get(2).getText());
        assertEquals("Botellas", mesa2.get(3).getText());

        assertEquals("3", mesa3.get(0).getText());
        assertEquals("8", mesa3.get(1).getText());
        assertEquals("1", mesa3.get(2).getText());
        assertEquals("", mesa3.get(3).getText());

        assertEquals("4", mesa4.get(0).getText());
        assertEquals("16", mesa4.get(1).getText());
        assertEquals("0", mesa4.get(2).getText());
        assertEquals("", mesa4.get(3).getText());

        assertEquals("35", footer.get(1).getText());
        assertEquals("1", footer.get(2).getText());
    }
}
