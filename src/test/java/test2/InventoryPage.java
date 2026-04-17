package test2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    private By contenedorInventario = By.id("inventory_container");
    private By botonAgregarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private By contadorCarrito = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public By obtenerContenedorInventario() {
        return contenedorInventario;
    }

    public void agregarProductoAlCarrito() {
        driver.findElement(botonAgregarMochila).click();
    }

    public String obtenerCantidadCarrito() {
        return driver.findElement(contadorCarrito).getText();
    }
}