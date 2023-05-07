import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTitleUrlPagesource {

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
    public void languageVersion(){
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");

        String wikiTitlePl = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitlePl, driver.getTitle(), "The title is not as expected.");

        String wikiUrlPl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(wikiUrlPl, driver.getCurrentUrl(), "The URL is not as expected");

        String pageElementPl = "\"wgPageContentLanguage\":\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(pageElementPl));

        WebElement spanishOption = driver.findElement(By.cssSelector("a[title='hiszpański']"));
        spanishOption.click();

////SPECJALNIE PRZEKLEIŁAM ASERCJE Z WERSJI POLSKIEJ, ŻEBY ZOBACZYĆ, JAK WYGLĄDA NIEUDANY TEST
//
//        Assertions.assertEquals(wikiTitlePl, driver.getTitle(), "The title is not as expected.");
//        Assertions.assertEquals(wikiUrlPl, driver.getCurrentUrl(), "The URL is not as expected");
//        Assertions.assertTrue(driver.getPageSource().contains(pageElementPl));

// A TU WERSJA TESTU PRAWIDŁOWEGO, ZE ZMIENIONYMI WARTOŚCIAMI DLA TITLE, URL, PAGESOURCE

        String wikiTitleEs = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(wikiTitleEs, driver.getTitle(), "The title is not as expected.");

        String wikiUrlEs = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(wikiUrlEs, driver.getCurrentUrl(), "The URL is not as expected");

        String pageElementEs = "\"pageLanguageCode\":\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(pageElementEs));

    }

}
