import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PracticeElementsInformation {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void quitdriver() {
        driver.quit();
    }

    @Test
    public void select_all_should_checkbox_in_every_option() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost:8080/wp-admin/edit.php?post_type=product");
        driver.findElement(By.id("cb-select-all-1")).click();

        List<WebElement> productCheckboxes = driver.findElements(By.name("post[]"));
        long numberOfSelectedCheckboxes = productCheckboxes.stream()
                .filter(checkbox -> checkbox.isSelected()).count();

        //Assertions.assertEquals(7, numberOfSelectedCheckboxes, "Not all checkboxes were selected");

        // CHCIAŁAM SPRÓBOWAĆ JAK POBRAĆ NAJPIERW INFORMACJĘ ILE JEST PRODUKTÓW NA STRONIE
        // ZAMIAST WPISYWAĆ JE NA SZTYWNO
        String selectedCheckboxes = Long.toString(numberOfSelectedCheckboxes);
        char itemsPerPage = driver.findElement(By.className("displaying-num")).getText().charAt(0);
        String itemsListed = Character.toString(itemsPerPage);

        Assertions.assertEquals(itemsListed, selectedCheckboxes, "Not all checkboxes were selected");

    }

    @Test
    public void product_marked_virtual_should_not_display_shipping_in_menu() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost:8080/wp-admin/post-new.php?post_type=product");
        driver.findElement(By.id("_virtual")).click();
        WebElement shippingTab = driver.findElement(By.className("shipping_tab"));
        Assertions.assertFalse(shippingTab.isDisplayed(), "Shipping tab is not hidden.");


    }

    @Test
    public void cart_not_changed_should_update_button_not_activated() {
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
//        TEST ASERCJI:
//        WebElement quantityField = driver.findElement(By.className("qty"));
//        quantityField.clear();
//        quantityField.sendKeys("2");

        WebElement updateButton = driver.findElement(By.name("update_cart"));

        Assertions.assertFalse(updateButton.isEnabled(),
                "Update button shouldn't be enabled - there were no changes in cart");
    }
}
