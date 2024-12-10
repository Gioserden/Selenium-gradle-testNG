package com.automation.tests;

import com.automation.base.TestBase;
import com.automation.utilities.ConfigReader;

import org.testng.annotations.Test;

public class ConfigTest extends TestBase {

    @Test
    public void testConfig() {
        setup();
        driver.get(ConfigReader.get("base.url"));
        System.out.println("Página cargada: " + driver.getCurrentUrl());
        teardown();
    }
}
