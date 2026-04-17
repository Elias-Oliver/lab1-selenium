package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class login {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("usuario_incorrecto");
            driver.findElement(By.id("password")).sendKeys("clave_incorrecta");
            driver.findElement(By.id("login-button")).click();

            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
            );

            if (error.isDisplayed()) {
                System.out.println("Escenario 1 correcto: el mensaje de error apareció.");
                System.out.println("Texto del error: " + error.getText());
            } else {
                System.out.println("Escenario 1 falló: no apareció el mensaje de error.");
            }

            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("inventory")) {
                System.out.println("Escenario 2 correcto: el login fue exitoso.");
            } else {
                System.out.println("Escenario 2 falló: no se cargó el inventario.");
            }

            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))
            );
            addToCart.click();
            System.out.println("Escenario 3 correcto: producto agregado al carrito.");

            WebElement badge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='shopping-cart-badge']"))
            );

            if (badge.getText().equals("1")) {
                System.out.println("Escenario 4 correcto: el carrito muestra 1 producto.");
            } else {
                System.out.println("Escenario 4 falló: el carrito no muestra 1 producto.");
            }

        } finally {
            driver.quit();
        }
    }
}