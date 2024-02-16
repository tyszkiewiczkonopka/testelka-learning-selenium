package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product extends BaseTest {

    private final By qtyFieldLocator = By.className("qty");
    @Test
    public void new_product_quantity_typed_in_should_product_quantity_changed() {
        driver.get(baseUrl + "/product/the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/");
        WebElement productQuantity = driver.findElement(qtyFieldLocator);
        productQuantity.clear();
        productQuantity.sendKeys("3");

        Assertions.assertEquals("3", productQuantity.getDomProperty("value"),
                "tests.Product quantity not changed.");
    }

}
