package com.automation.tests;

import com.automation.base.TestBase;
import com.automation.utilities.ConfigReader;

import org.testng.annotations.Test;

public class ConfigTest extends TestBase {

    @Test
    public void testConfig() {
        System.out.println("Ruta del ChromeDriver: " + System.getProperty("webdriver.chrome.driver"));
        setup();
        driver.get(ConfigReader.get("base.url"));
        System.out.println("Página cargada: " + driver.getCurrentUrl());
        teardown();
    }
}
