import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminTests extends BaseTests{

    @BeforeEach
    public void adminLogin(){
        driver.get(baseUrl + "/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }
    @Test
    public void admin_successful_login_should_display_my_account_content() {

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "User not logged in"
        );

    }
    @Test
    public void select_all_should_checkbox_in_every_option() {

        driver.get(baseUrl + "/wp-admin/edit.php?post_type=product");
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

        driver.get(baseUrl + "/wp-admin/post-new.php?post_type=product");
        driver.findElement(By.id("_virtual")).click();
        WebElement shippingTab = driver.findElement(By.className("shipping_tab"));
        Assertions.assertFalse(shippingTab.isDisplayed(), "Shipping tab is not hidden.");

    }
}
