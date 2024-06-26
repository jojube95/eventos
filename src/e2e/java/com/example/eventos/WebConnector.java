package com.example.eventos;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebConnector {
    WebDriver driver;

    @Before
    public void initSelenium() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--lang=es");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @After
    public void destroySelenium() {
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

        String command = "mongorestore --uri " + System.getenv("MONGODB_URI");
        String path = new File("src/e2e/resources/database/test").getAbsolutePath();

        String finalCommand = command + " " + path + " --drop --numInsertionWorkersPerCollection=10 --quiet";

        Process process = Runtime.getRuntime().exec(finalCommand);

        BufferedReader inStream = new BufferedReader(
                    new InputStreamReader( process.getInputStream() ));
        System.out.println(inStream.readLine());

        System.out.println("Restored test database");
    }

    public String getBaseUrl(){
        String baseUrl = "http://localhost";
        String port = System.getenv("PORT");

        return baseUrl + ":" + port;
    }
}
