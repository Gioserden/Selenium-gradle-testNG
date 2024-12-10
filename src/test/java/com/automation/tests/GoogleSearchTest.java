package com.automation.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;

public class GoogleSearchTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        setup();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        System.out.println("Página cargada correctamente");
    }

    @AfterMethod
    public void tearDown() {
        teardown();
    }
}

