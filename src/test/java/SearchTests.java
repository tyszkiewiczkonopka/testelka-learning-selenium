import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends BaseTests {

    private final By searchFieldLocator = By.id("wc-block-search__input-1");

    @Test
    public void search_field_should_have_placeholder_text(){
        driver.get(baseUrl + "/");
        WebElement searchField = driver.findElement(searchFieldLocator);
        Assertions.assertEquals("Search products…", searchField.getDomAttribute("placeholder"),
                "Placeholder value is not correct");
    }
}
