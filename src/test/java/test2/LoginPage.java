package test2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By campoUsuario = By.id("user-name");
    private By campoContrasena = By.id("password");
    private By botonLogin = By.id("login-button");
    private By mensajeError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("https://www.saucedemo.com/");
    }

    public void iniciarSesion(String usuario, String contrasena) {
        driver.findElement(campoUsuario).clear();
        driver.findElement(campoContrasena).clear();
        driver.findElement(campoUsuario).sendKeys(usuario);
        driver.findElement(campoContrasena).sendKeys(contrasena);
        driver.findElement(botonLogin).click();
    }

    public By obtenerMensajeError() {
        return mensajeError;
    }
}