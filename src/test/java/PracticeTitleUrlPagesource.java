import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticeTitleUrlPagesource {

        WebDriver driver;

        @BeforeEach
        public void driverSetup() {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1280, 720));
        }

        @AfterEach
        public void driverQuit() {
            driver.close();
            driver.quit();
        }

}
