package com.example.eventos.connectors;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebConnector {
    WebDriver driver;

    @BeforeAll
    public static void before_all() throws IOException {
        //restoreTestDatabase();
    }

    @Before
    public void initSelenium() {
        System.setProperty("webdriver.chrome.driver", "src/e2e/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void destroySelenium() throws IOException {
        driver.close();
    }

    @After("@modifyDatabase")
    public void restoreDatabaseAfterScenario() throws IOException {
        restoreTestDatabase();
    }

    public WebDriver getDriver() {
        return driver;
    }


    public static void restoreTestDatabase() throws IOException {
        System.out.println("Restoring test database");

        String command = "mongorestore --uri mongodb+srv://jojube95:holahola@cluster0.mlq4w.mongodb.net/test";
        String path = new File("src/e2e/resources/database/test").getAbsolutePath();

        String finalCommand = command + " " + path + " --drop --numInsertionWorkersPerCollection=10 --quiet";

        Process process = Runtime.getRuntime().exec(finalCommand);

        BufferedReader inStream = new BufferedReader(
                    new InputStreamReader( process.getInputStream() ));
        System.out.println(inStream.readLine());

        System.out.println("Restored test database");
    }
}
