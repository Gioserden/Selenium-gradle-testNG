package com.automation.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.base.TestBase;

public class TestListener extends TestBase implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Iniciando prueba: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Prueba exitosa: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Prueba fallida: " + result.getName());
        takeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Prueba omitida: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Inicio del contexto de prueba: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finalización del contexto de prueba: " + context.getName());
    }
}
