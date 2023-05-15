import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFundamentals {

    WebDriver driver;
    WebDriverWait wait; //tu tylko deklaracja, żeby można było w każdym teście z tego korzystać, potem inicjalizacja

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
    }

    @AfterEach
    public void quitdriver() {
        driver.quit();
    }

    @Test
    public void update_quantity_in_cart_should_update_total_price(){
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");

        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();

        // wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        // czekanie, aż wszystkie czekacze znikną
        wait.until(driver -> driver.findElements(By.className("blockUI")).size() == 0);

        WebElement total = driver.findElement(By.className("order-total"));

        Assertions.assertEquals("Total 24,00 €", total.getText(), "Total price is not correct");

    }

    @Test
    public void clickingMinicartShouldShowTheSamePriceAsHeader() {
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        driver.findElement(By.className("wc-block-mini-cart__button")).click();

        //WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("wc-block-components-totals-item__value")));
        WebElement totalPrice = wait.until(d -> driver.findElement(
                By.className("wc-block-components-totals-item__value")));

        Assertions.assertEquals("12,00 €", totalPrice.getText(),
                "The price in mini cart is not correct");
    }

    @Test
    public void add_to_cart_should_header_show_product_price() {
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();

        WebElement miniCart = driver.findElement(
                By.className("wc-block-mini-cart__amount"));
        Assertions.assertEquals("12,00 €", miniCart.getText(),
                "The price in header is not correct");
    }

    @Test
    public void admin_successful_login_should_display_my_account_content() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "User not logged in"
        );

    }

}