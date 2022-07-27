package com.example.eventos.navbar;

import com.example.eventos.connectors.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavbarStepDef {
    private final WebConnector connector;

    public NavbarStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Open Chrome and visit verEventos page$")
    public void open_the_chrome_and_visit_ver_eventos_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/verEventos");
    }

    @When("^User click on calendario navbar$")
    public void user_click_calendario_navbar() {
        WebElement calendarioNavbarButton = connector.getDriver().findElement(By.xpath("//li[@class='nav-item']//a[1]"));
        calendarioNavbarButton.click();
    }

    @When("^User click on eventos navbar$")
    public void user_click_eventos_navbar() {
        WebElement eventosNavbarButton = connector.getDriver().findElement(By.xpath("(//li[@class='nav-item dropdown']//a)[1]"));
        eventosNavbarButton.click();
    }

    @When("^User click on anyadir navbar$")
    public void user_click_anyadir_navbar() {
        WebElement anyadirNavbarButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[1]"));
        anyadirNavbarButton.click();
    }

    @When("^User click on ver navbar$")
    public void user_click_ver_navbar() {
        WebElement verNavbarButton = connector.getDriver().findElement(By.xpath("//div[@class='dropdown-menu show']//a[2]"));
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

    @Then("^Anyadir evento page is shown$")
    public void anyadir_evento_page_is_shown() {
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/anyadirEvento"));
    }

    @Then("^Ver eventos page is shown$")
    public void ver_eventos_page_is_shown() {
        String url = connector.getDriver().getCurrentUrl();
        assertThat(url, CoreMatchers.containsString("/verEventos"));
    }
}
