package com.example.eventos.stepDefs.empleado;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoHistorialStepDef {
    private final WebConnector connector;

    public EmpleadoHistorialStepDef(WebConnector connector) {
        this.connector = connector;
    }

    @Given("^Visit empleado historial page$")
    public void visit_empleado_historial_page() {
        connector.getDriver().manage().window().maximize();
        connector.getDriver().get("http://localhost:8081/empleadoHistorial?empleadoId=6310ca5942c98257fb6f349d");
    }

    @Given("^Set empleado historial dates to avoid future test fails$")
    public void set_initial_dates() {
        WebElement fechaMin = connector.getDriver().findElement(By.id("fechaMin"));
        WebElement fechaMax = connector.getDriver().findElement(By.id("fechaMax"));

        fechaMin.clear();
        fechaMin.sendKeys("2022-01-01");
        connector.getDriver().findElement(By.tagName("html")).click();

        fechaMax.clear();
        fechaMax.sendKeys("2022-12-31");
        connector.getDriver().findElement(By.tagName("html")).click();
    }

    @Then("^Historial data should be correct$")
    public void historial_data_is_shown() {
        List<WebElement> eventos = connector.getDriver().findElements(By.xpath("//table[@id='empleadoHistorial']/tbody/tr"));

        List<WebElement> evento1 = connector.getDriver().findElements(By.xpath("//table[@id='empleadoHistorial']/tbody/tr[1]/td"));
        List<WebElement> evento2 = connector.getDriver().findElements(By.xpath("//table[@id='empleadoHistorial']/tbody/tr[2]/td"));

        List<WebElement> footer = connector.getDriver().findElements(By.xpath("//table[@id='empleadoHistorial']/tfoot/tr/td"));

        assertEquals(2, eventos.size());

        assertEquals("2022-07-02", evento1.get(0).getText());
        assertEquals("Boda-Cena", evento1.get(1).getText());
        assertEquals("Boda", evento1.get(2).getText());
        assertEquals("Cena", evento1.get(3).getText());
        assertEquals("Sala1", evento1.get(4).getText());
        assertEquals("Aielo de Malferit", evento1.get(5).getText());
        assertEquals("153", evento1.get(6).getText());
        assertEquals("13", evento1.get(7).getText());

        assertEquals("2022-07-10", evento2.get(0).getText());
        assertEquals("Comunión-Comida", evento2.get(1).getText());
        assertEquals("Comunión", evento2.get(2).getText());
        assertEquals("Comida", evento2.get(3).getText());
        assertEquals("Sala2", evento2.get(4).getText());
        assertEquals("Olleria", evento2.get(5).getText());
        assertEquals("43", evento2.get(6).getText());
        assertEquals("18", evento2.get(7).getText());

        assertEquals("Total:", footer.get(0).getText());
        assertEquals("2", footer.get(1).getText());
    }
}
