package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.utilities.ConfigReader;
import com.automation.base.TestBase;
import com.automation.pages.InventoryPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setUp() {
        setup();
        driver.get(ConfigReader.get("base.url"));
        logger.info("Página cargada: " + driver.getCurrentUrl());
        loginPage = new LoginPage(driver); // Inicializar LoginPage
        inventoryPage = new InventoryPage(driver); // Inicializar InventoryPage
    }

    @Test
    public void testSuccessfulLogin() {
        // Login con credenciales válidas
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        logger.info("Se hizo clic en el botón de login");

        String currentUrl = driver.getCurrentUrl();
        logger.info("URL actual: " + currentUrl);
        // Validar redirección y título de la página
        Assert.assertTrue(inventoryPage.getCurrentUrl().contains("/inventory.html"),
                "No se redirigió a la página de inventario.");
        Assert.assertTrue(inventoryPage.getPageTitle().contains("Swag Labs"),
                "El título de la página no contiene 'Swag Labs'.");
    }

    @Test
    public void testFailedLogin() {
        // Login con credenciales inválidas
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();

        // Validar el mensaje de error
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Epic sadface"), "El mensaje de error es incorrecto.");
    }

    @Test
    public void testSuccessfulLoginWithExplicitWait() {
        // Localizadores
        By usernameField = By.id("user-name");
        By passwordField = By.id("password");
        By loginButton = By.id("login-button");

        // Usar WebDriverWait para esperar a que los elementos sean interactuables
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        // Validar redirección
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "No se redirigió correctamente.");
    }

    @AfterMethod
    public void handleTestFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String testName = result.getName();
            logger.error("La prueba falló: " + testName);
            takeScreenshot(testName);
        }
        teardown();
        logger.info("Prueba finalizada exitosamente");
    }
}
