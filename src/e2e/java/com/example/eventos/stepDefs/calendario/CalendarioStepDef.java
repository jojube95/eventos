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

  @Given("^Open Chrome and logIn as admin$")
  public void open_the_chrome_and_log_in_as_admin() {
    connector.getDriver().manage().window().maximize();
    connector.getDriver().get("http://localhost:8080");

    WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
    wait.until(ExpectedConditions.urlToBe("http://localhost:8080/login"));

    WebElement username = connector.getDriver().findElement(By.id("username"));
    WebElement password = connector.getDriver().findElement(By.id("password"));
    WebElement loginButton = connector.getDriver().findElement(By.id("loginButton"));

    username.sendKeys("admin");
    password.sendKeys("admin");
    loginButton.click();

    WebDriverWait wait2 = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(2));
    wait2.until(ExpectedConditions.urlToBe("http://localhost:8080/calendario"));
  }

  @Given("^Visit calendar page$")
  public void visit_calendar_page() {
    connector.getDriver().manage().window().maximize();
    connector.getDriver().get("http://localhost:8080/calendario");
    ((JavascriptExecutor) connector.getDriver()).executeScript("goToDate(new Date(2022, 06, 01));");
  }

  @Given("^Visit root page$")
  public void visit_root_page() {
    connector.getDriver().manage().window().maximize();
    connector.getDriver().get("http://localhost:8080");
  }

  @When("^User click last month$")
  public void user_click_last_month() {
    WebElement nextMonthButton = connector.getDriver().findElement(By.xpath("(//div[@class='fc-button-group']//button)[1]"));
    nextMonthButton.click();
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

  @Then("^Redirect to eventoVer page$")
  public void redirect_to_verEvento() {
    String url = connector.getDriver().getCurrentUrl();
    assertThat(url, CoreMatchers.containsString("/eventoVer?eventoId=62dc2a63ec628818203950b9"));
  }

  @Then("^Page should redirect to calendar page$")
  public void should_redirect_to_calendar_page() {
    WebDriverWait wait = new WebDriverWait(connector.getDriver(), Duration.ofSeconds(5));
    wait.until(ExpectedConditions.urlToBe("http://localhost:8080/calendario"));
    ((JavascriptExecutor) connector.getDriver()).executeScript("goToDate(new Date(2022, 06, 01));");
  }

  @And("^First event title should be Boda-Cena$")
  public void first_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[1]"));
    String eventoText = evento.getText();
    assertEquals("Boda-Cena 153p", eventoText);
  }

  @And("^Second event title should be Comunion-Comida$")
  public void second_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[2]"));
    String eventoText = evento.getText();
    assertEquals("Comunión-Comida 43p", eventoText);
  }

  @And("^Third event title should be Sopar festes-Cena$")
  public void third_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[3]"));
    String eventoText = evento.getText();
    assertEquals("Sopar festes-Cena 90p", eventoText);
  }

  @And("^Fourth event title should be Dinar empresa-Comida$")
  public void fourth_event_title() {
    WebElement evento = connector.getDriver().findElement(By.xpath("(//div[@class='fc-event-title fc-sticky'])[4]"));
    String eventoText = evento.getText();
    assertEquals("Dinar empresa-Comida 36p", eventoText);
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
