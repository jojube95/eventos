package com.example.eventos.connectors;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebConnector {
    WebDriver driver;

    @Before
    public void initSelenium() {
        System.setProperty("webdriver.chrome.driver", "src/e2e/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void destroySelenium() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
