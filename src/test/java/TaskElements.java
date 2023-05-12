import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskElements {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quitdriver() {
        driver.quit();
    }

    @Test
    public void createNewEvent() {
        driver.get("http://localhost:3000/new");
        driver.findElement(By.id("title")).sendKeys("Knowledge Transfer");
        driver.findElement(By.className("btn-primary")).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("mt-3")),
                "User wasn't redirected to step 2: choosing the date.");
    }
}
