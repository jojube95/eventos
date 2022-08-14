package com.example.eventos;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            System.setProperty("webdriver.chrome.driver", "src/e2e/resources/drivers/windows/chromedriver.exe");
        }
        else if(System.getProperty("os.name").toLowerCase().contains("nux")){
            System.setProperty("webdriver.chrome.driver", "src/e2e/resources/drivers/linux/chromedriver");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=es");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");

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
