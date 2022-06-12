package com.example.eventos;

import com.example.eventos.connectors.WebConnector;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadPageStepDef {

  private final WebConnector connector;

  public LoadPageStepDef(WebConnector connector) {
    this.connector = connector;
  }

  @Before
  public void beforeScenario() {
    System.setProperty("webdriver.chrome.driver", "src/it/resources/drivers/chromedriver.exe");
  }

  @Given("^Open the Chrome and launch the application$")
  public void open_the_chrome_and_launch_the_application() throws Throwable {
    connector.getDriver().manage().window().maximize();
    connector.getDriver().get("http://localhost:8080/home");
  }

  @And("^Home page will be displayed$")
  public void verify_welcome_page() throws Throwable {
    String actualString = connector.getDriver().findElement(By.tagName("h1")).getText();
    assertTrue(actualString.contains("Eventos"));
  }

  @When("^User will see evento$")
  public void enter_the_Username_and_Password() throws Throwable {
    String actualString = connector.getDriver().findElement(By.tagName("p")).getText();
    assertTrue(actualString.contains("Evento: Boda - Cena - 150"));
  }

}
