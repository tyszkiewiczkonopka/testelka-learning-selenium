import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskWindow {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }
    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }
    @Test
    public void testWindows(){
        driver.navigate().to("https://www.nasa.gov/");

        Dimension size = new Dimension(854, 480);
        driver.manage().window().setSize(size);
        Point point = new Point(445, 30);
        driver.manage().window().setPosition(point);

        Assertions.assertEquals(size,  driver.manage().window().getSize());
        Assertions.assertEquals(point, driver.manage().window().getPosition());

        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

    }

}
