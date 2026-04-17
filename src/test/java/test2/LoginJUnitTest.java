package test2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginJUnitTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void iniciarNavegador() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginInvalidoDebeMostrarMensajeDeError() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("usuario_incorrecto");
        driver.findElement(By.id("password")).sendKeys("clave_incorrecta");
        driver.findElement(By.id("login-button")).click();

        WebElement mensajeError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='error']")
                )
        );

        assertTrue(mensajeError.isDisplayed());
        assertTrue(mensajeError.getText().contains("Epic sadface"));
    }

    @Test
    public void loginValidoDebeEntrarAlInventario() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))
        );

        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void agregarProductoDebeMostrarUnoEnElCarrito() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))
        );

        WebElement botonAgregar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        );
        botonAgregar.click();

        WebElement contadorCarrito = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='shopping-cart-badge']")
                )
        );

        assertEquals("1", contadorCarrito.getText());
    }
}