import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskNavigation {

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

    @Test
    public void navigationTest(){

        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        String actualTitleWiki = driver.getTitle();
        String expectedTitleWiki = "Wikipedia, wolna encyklopedia";

        driver.navigate().to("https://www.nasa.gov/");
        String actualTitleNasa = driver.getTitle();
        String expectedTitleNasa = "NASA";

        driver.navigate().back();
        Assertions.assertEquals(expectedTitleWiki, actualTitleWiki, "Adresy URL nie są zgodne.");
        driver.navigate().forward();
        Assertions.assertEquals(expectedTitleNasa, actualTitleNasa, "Adresy URL nie są zgodne.");

        // można zamiast String actualTitleWiki = driver.getTitle(); wstawić driver.getTitle() bezpośrednio w
        // asercję zamiast zmiennej


    }
}
