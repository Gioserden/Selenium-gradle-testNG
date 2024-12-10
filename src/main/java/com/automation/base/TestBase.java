package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automation.utilities.ConfigReader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import java.time.Duration;

public class TestBase {

    protected WebDriver driver;

    public void setup() {
        createScreenshotsDir();
        String browser = ConfigReader.get("browser");
        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Navegador no soportado: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.get("timeout"))));
        driver.manage().window().maximize();
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Captura de pantalla guardada en: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Error al guardar la captura de pantalla: " + e.getMessage());
        }
    }

    private void createScreenshotsDir() {
        File screenshotsDir = new File("screenshots");
        if (!screenshotsDir.exists()) {
            if (screenshotsDir.mkdir()) {
                System.out.println("Directorio 'screenshots' creado exitosamente.");
            } else {
                System.err.println("No se pudo crear el directorio 'screenshots'.");
            }
        }
    }
}
