package com.example.eventos.connectors;

import com.example.eventos.evento.Evento;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;


public class WebConnector {
    WebDriver driver;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Before
    public void initSelenium() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/e2e/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void setUp(){
        Evento evento1 = new Evento("Boda", "Cena", 160, new Date());
        Evento evento2 = new Evento("Comunión", "Cena", 100, new Date());
        mongoTemplate.insert(evento1);
        mongoTemplate.insert(evento2);
    }

    @After
    public void destroySelenium() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
