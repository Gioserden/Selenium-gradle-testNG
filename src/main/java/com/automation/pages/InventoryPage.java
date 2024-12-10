package com.automation.pages;

import org.openqa.selenium.WebDriver;

public class InventoryPage {
  private WebDriver driver;

  public InventoryPage(WebDriver driver) {
    this.driver = driver;
  }

  // Validar que estamos en la página de inventario
  public String getPageTitle() {
    return driver.getTitle();
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }
}
