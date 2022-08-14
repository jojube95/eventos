package com.example.eventos.stepDefs.calendario;

import com.example.eventos.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarioStepDef {

  private final WebConnector connector;

  public CalendarioStepDef(WebConnector connector) {
    this.connector = connector;
  }

  @Given("^Open Chrome and visit calendar page$")
  public void open_the_chrome_and_visit_calendar_page() {
    connector.getDriver().manage().window().maximize();
    connector.getDriver().get("http://localhost:8081/calendario");
    ((JavascriptExecutor) connector.getDriver()).executeScript("goToDate(new Date(2022, 06, 01));");
  }

  @When("^User click last month$")
  public void user_click_last_month() {
    WebElement nextMonthButton = connector.getDriver().findElement(By.xpath("(//div[@class='fc-button-group']//button)[1]"));
    nextMonthButton.click();
  }

  @When("^User click next month$")
  public void user_click_next_month() {
    WebElement nextMonthButton = connector.getDriver().findElement(By.xpath("(//div[@class='fc-button-group']//button)[2]"));
    nextMonthButton.click();
  }

  @When("^User click evento$")
  public void user_click_evento() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[1]"));
    evento.click();
  }

  @When("^User click ver button$")
  public void user_click_ver_button() {
    WebElement verButton = connector.getDriver().findElement(By.id("modalEventoVerButton"));
    verButton.click();
  }

  @When("^User click modificar button$")
  public void user_click_modificar_button() {
    WebElement modificarButton = connector.getDriver().findElement(By.id("modalEventoModificarButton"));
    modificarButton.click();
  }

  @When("^User click close button$")
  public void user_click_close_button() {
    WebElement closeButton = connector.getDriver().findElement(By.id("modalEventoCerrarButton"));
    closeButton.click();
  }

  @And("^Calendar will be displayed$")
  public void calendar_will_be_displayed() {
    WebElement calendario = connector.getDriver().findElement(By.id("calendar"));
    assertTrue(calendario.isDisplayed());
  }

  @And("^Date should be julio de 2022$")
  public void date_should_be_julio() {
    WebElement fechaElement = connector.getDriver().findElement(By.id("fc-dom-1"));
    String fechaText = fechaElement.getText();
    assertEquals("julio de 2022", fechaText);
  }

  @And("^Date should be junio de 2022$")
  public void date_should_be_junio() {
    WebElement fechaElement = connector.getDriver().findElement(By.id("fc-dom-1"));
    String fechaText = fechaElement.getText();
    assertEquals("junio de 2022", fechaText);
  }

  @Then("^User will see 4 different eventos$")
  public void user_will_see_eventos() {
    List<WebElement> elements = connector.getDriver().findElements(By.className("fc-event-main"));
    assertEquals(4, elements.size());
  }

  @Then("^Date should be agosto de 2022$")
  public void date_should_be_agosto() {
    WebElement fechaElement = connector.getDriver().findElement(By.id("fc-dom-1"));
    String fechaText = fechaElement.getText();
    assertEquals("agosto de 2022", fechaText);
  }

  @Then("^Event modal detail dialog is shown$")
  public void event_modal_detail_dialog_is_shown() {
    WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eventDetailModal")));
  }

  @Then("^Redirect to verEvento page$")
  public void redirect_to_verEvento() {
    String url = connector.getDriver().getCurrentUrl();
    assertThat(url, CoreMatchers.containsString("/verEvento?eventoId=62dc2a63ec628818203950b9"));
  }

  @Then("^Redirect to updateEvento page$")
  public void redirect_to_updateEvento() {
    String url = connector.getDriver().getCurrentUrl();
    assertThat(url, CoreMatchers.containsString("/updateEvento?eventoId=62dc2a63ec628818203950b9"));
  }

  @Then("^Modal detail dialogos is closed$")
  public void modal_detail_dialog_is_closed() {
    WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("eventDetailModal")));
  }

  @And("^Event modal detail dialog show correct event data$")
  public void event_modal_detail_show_correct_data() {
    String actualFecha = connector.getDriver().findElement(By.id("modalEventoFecha")).getText();
    String actualTipo = connector.getDriver().findElement(By.id("modalEventoTipo")).getText();
    String actualHorario = connector.getDriver().findElement(By.id("modalEventoHorario")).getText();
    String actualLocalidad = connector.getDriver().findElement(By.id("modalEventoLocalidad")).getText();
    String actualPersonas = connector.getDriver().findElement(By.id("modalEventoPersonas")).getText();
    String actualNinyos = connector.getDriver().findElement(By.id("modalEventoNinyos")).getText();
    String actualConfirmado = connector.getDriver().findElement(By.id("modalEventoConfirmado")).getText();

    String expectedFecha = "2022-07-02";
    String expectedTipo = "Boda";
    String expectedHorario = "Cena";
    String expectedLocalidad = "Aielo de Malferit";
    String expectedPersonas = "153";
    String expectedNinyos = "13";
    String expectedConfirmado   = "true";

    assertEquals(expectedFecha, actualFecha);
    assertEquals(expectedTipo, actualTipo);
    assertEquals(expectedHorario, actualHorario);
    assertEquals(expectedLocalidad, actualLocalidad);
    assertEquals(expectedPersonas, actualPersonas);
    assertEquals(expectedNinyos, actualNinyos);
    assertEquals(expectedConfirmado, actualConfirmado);
  }

  @And("^Event modal detail dialog have three buttons$")
  public void event_modal_detail_have_three_buttons() {
    WebElement verButton = connector.getDriver().findElement(By.id("modalEventoVerButton"));
    WebElement modificarButton = connector.getDriver().findElement(By.id("modalEventoModificarButton"));
    WebElement cerrarButton = connector.getDriver().findElement(By.id("modalEventoCerrarButton"));

    assertTrue(verButton.isDisplayed());
    assertTrue(modificarButton.isDisplayed());
    assertTrue(cerrarButton.isDisplayed());
  }

  @And("^First event title should be Boda-Cena$")
  public void first_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[1]"));
    String eventoText = evento.getText();
    assertEquals("Boda-Cena", eventoText);
  }

  @And("^Second event title should be Comunion-Comida$")
  public void second_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[2]"));
    String eventoText = evento.getText();
    assertEquals("Comunion-Comida", eventoText);
  }

  @And("^Third event title should be Sopar festes-Cena$")
  public void third_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[3]"));
    String eventoText = evento.getText();
    assertEquals("Sopar festes-Cena", eventoText);
  }

  @And("^Fourth event title should be Dinar empresa-Comida$")
  public void fourth_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[4]"));
    String eventoText = evento.getText();
    assertEquals("Dinar empresa-Comida", eventoText);
  }

  @And("^First event should be placed on day 2$")
  public void first_event_day_placed() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[1]"));
    WebElement dia = evento.findElement(By.xpath("./../../../../../../../div[1]/a[1]"));
    String diaText = dia.getText();
    assertEquals("2", diaText);
  }

  @And("^Second event should be placed on day 10$")
  public void second_event_day_placed() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[2]"));
    WebElement dia = evento.findElement(By.xpath("./../../../../../../../div[1]/a[1]"));
    String diaText = dia.getText();
    assertEquals("10", diaText);
  }

  @And("^Third event should be placed on day 16$")
  public void third_event_day_placed() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[3]"));
    WebElement dia = evento.findElement(By.xpath("./../../../../../../../div[1]/a[1]"));
    String diaText = dia.getText();
    assertEquals("16", diaText);
  }

  @And("^Fourth event should be placed on day 23$")
  public void fourth_event_day_placed() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[4]"));
    WebElement dia = evento.findElement(By.xpath("./../../../../../../../div[1]/a[1]"));
    String diaText = dia.getText();
    assertEquals("23", diaText);
  }

  @And("^No event is shown$")
  public void no_event_is_shown() {
    List<WebElement> elements = connector.getDriver().findElements(By.className("fc-event-main"));
    assertEquals(0, elements.size());
  }

  @And("^2 events is shown$")
  public void two_event_is_shown() {
    List<WebElement> elements = connector.getDriver().findElements(By.className("fc-event-main"));
    assertEquals(2, elements.size());
  }
}
