import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartTests extends BaseTests{

    private final By addToCartButtonLocator = By.name("add-to-cart");
    private final By minicartButtonLocator = By.className("wc-block-mini-cart__button");
    private final By minicartAmountLocator = By.className("wc-block-mini-cart__amount");
    private final By updateCartButton = By.name("update_cart");
    private final By qtyFieldLocator = By.className("qty");
    private final String historyAstronomySlug = "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/";

    @Test
    public void clickingMinicartShouldShowTheSamePriceAsHeader() {
        driver.get(baseUrl + "/product/" + historyAstronomySlug);
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
        driver.findElement(minicartButtonLocator).click();

        //WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("wc-block-components-totals-item__value")));
        WebElement totalPrice = wait.until(d -> driver.findElement(
                By.className("wc-block-components-totals-item__value")));

        Assertions.assertEquals("12,00 €", totalPrice.getText(),
                "The price in mini cart is not correct");
    }
    @Test
    public void add_to_cart_should_header_show_product_price() {
        driver.get(baseUrl + "/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke" +
                "/");
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();

        WebElement miniCart = driver.findElement(minicartAmountLocator);
        Assertions.assertEquals("12,00 €", miniCart.getText(),
                "The price in header is not correct");
    }

    @Test
    public void cart_not_changed_should_update_button_not_activated() {
        driver.get(baseUrl + "/product/" + historyAstronomySlug);
        driver.findElement(addToCartButtonLocator).click();
        driver.get(baseUrl + "/cart/");

//        TEST ASERCJI:
//        WebElement quantityField = driver.findElement(By.className("qty"));
//        quantityField.clear();
//        quantityField.sendKeys("2");

        WebElement updateButton = driver.findElement(updateCartButton);

        Assertions.assertFalse(updateButton.isEnabled(),
                "Update button shouldn't be enabled - there were no changes in cart");
    }

    @Test
    public void update_quantity_in_cart_should_update_total_price(){
        driver.get(baseUrl + "/product/" + historyAstronomySlug);
        driver.findElement(addToCartButtonLocator).click();
        driver.get(baseUrl + "/cart/");

        WebElement quantityField = driver.findElement(qtyFieldLocator);
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(updateCartButton).click();

        // wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        // czekanie, aż wszystkie czekacze znikną
        wait.until(driver -> driver.findElements(By.className("blockUI")).size() == 0);

        WebElement total = driver.findElement(By.className("order-total"));

        Assertions.assertEquals("Total 24,00 €", total.getText(), "Total price is not correct");

    }
}
