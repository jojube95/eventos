package com.example.eventos.stepDefs.navbar;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavbarStepDef {
    private final WebConnector connector;

    public NavbarStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @When("^User click logout button$")
    public void user_click_logout_button() {
        WebElement navBarLogout = connector.getDriver().findElement(By.id("navBarLogout"));
        navBarLogout.click();
    }

    @When("^User click on calendario navbar$")
    public void user_click_calendario_navbar() {
        WebElement calendarioNavbarButton = connector.getDriver().findElement(By.id("navBarCalendario"));
        calendarioNavbarButton.click();
    }

    @When("^User click on eventos navbar$")
    public void user_click_eventos_navbar() {
        WebElement eventosNavbarButton = connector.getDriver().findElement(By.id("navbarDropdownEventos"));
        eventosNavbarButton.click();
    }

    @When("^User click on empleado navbar$")
    public void user_click_empleado_navbar() {
        WebElement empleadosNavbarButton = connector.getDriver().findElement(By.id("navbarDropdownEmpleados"));
        empleadosNavbarButton.click();
    }

    @When("^User click on anyadir navbar$")
    public void user_click_anyadir_navbar() {
        WebElement anyadirNavbarButton = connector.getDriver().findElement(By.id("navBarEventoAnyadir"));
        anyadirNavbarButton.click();
    }

    @When("^User click on ver navbar$")
    public void user_click_ver_navbar() {
        WebElement verNavbarButton = connector.getDriver().findElement(By.id("navBarEventosVer"));
        verNavbarButton.click();
    }

    @When("^User click on anyadir empleado navbar$")
    public void user_click_anyadir_empleado_navbar() {
        WebElement anyadirNavbarButton = connector.getDriver().findElement(By.id("navBarEmpleadoAnyadir"));
        anyadirNavbarButton.click();
    }

    @When("^User click on ver empleado navbar$")
    public void user_click_ver_empleado_navbar() {
        WebElement verNavbarButton = connector.getDriver().findElement(By.id("navBarEmpleadosVer"));
        verNavbarButton.click();
    }

    @Then("^Calendar page is shown$")
    public void calendar_page_is_shown() {
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/calendario"));
    }

    @Then("^Dropdown buttons navbar is shown$")
    public void dropdown_navbar_buttons_is_shown() {
        WebElement navbarDropdownButtons = connector.getDriver().findElement(By.xpath("(//li[contains(@class,'nav-item dropdown')]//div)[1]"));
        assertTrue(navbarDropdownButtons.isDisplayed());
    }

    @Then("^Dropdown buttons empleado navbar is shown$")
    public void dropdown_navbar_empleado_buttons_is_shown() {
        WebElement navbarDropdownButtons = connector.getDriver().findElement(By.xpath("(//li[contains(@class,'nav-item dropdown')]//div)[2]"));
        assertTrue(navbarDropdownButtons.isDisplayed());
    }

    @Then("^Anyadir evento page is shown$")
    public void anyadir_evento_page_is_shown() {
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/eventoAnyadir"));
    }

    @Then("^Ver eventos page is shown$")
    public void ver_eventos_page_is_shown() {
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/eventosVer"));
    }

    @Then("^Anyadir empleado page is shown$")
    public void redirect_to_anyadir_empleados_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/empleadoAnyadir"));
    }

    @Then("^Ver empleados page is shown$")
    public void redirect_to_empleados_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/empleados"));
    }

    @Then("^Redirect to login page$")
    public void redirect_to_login_page(){
        WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/login?logout"));
    }

    @And("^Logout message shows$")
    public void logout_message_shows(){
        String message = connector.getDriver().findElement(By.className("text-success")).getText();
        assertEquals("Has salido", message);
    }
}
