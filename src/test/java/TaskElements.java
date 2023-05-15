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

        WebElement stepTracking = driver.findElement(By.className("tracking-tight"));
        Assertions.assertEquals("Step 2 of 3", stepTracking.getText(),
                "Step tracking doesn't show that we are at the second step.");
    }
}
