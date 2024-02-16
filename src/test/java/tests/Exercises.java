package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercises extends BaseTest {

    // CREATE A SURVEY



    @Test
    public void should_create_survey_with_minimum_fields() throws Exception {
        driver.get("http://localhost:3000/new");

        // STEP 1
        WebElement surveyTitle = driver.findElement(By.id("title"));
        surveyTitle.sendKeys("SPOTKANIE");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        submitButton.click();

        // STEP 2
        WebElement currentDay = driver.findElement(By.cssSelector(".font-bold.text-primary-600"));
        currentDay.click();
        submitButton.click();

        // STEP 3

        Faker faker = new Faker();

        driver.findElement(By.id("name")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("contact")).sendKeys(faker.internet().emailAddress());
        submitButton.click();

        // ASSERTION
        //   wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://localhost:3000/new")));

        WebElement summarySurveyTitle = driver.findElement(By.cssSelector("div[data-testid='poll-title']"));

        assertEquals(summarySurveyTitle, surveyTitle, "Survey titles do not match");


    }


    // ADD TO FAVOURITE

    @Test
    public void add_to_favourite_should_mark_the_star_blue() {
        logInMattermost();
        addTeamToFavourite();

        assertTrue(favouriteButton.getAttribute("class").contains("active"),
                "The favorite button is not in the inactive state.");

    }

    // NAVIGATION


    @Test
    public void navigationTest() {

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
    // Twoim zadaniem jest potwierdzenie, że placeholdery dla pól odpowiadających tytułowi, lokacji i opisowi wydarzenia,
    // są równe kolejno: „Monthly Meetup”, „Joe’s Coffee Shop” i „Hey everyone, please choose the dates that work for you!”.

    @Test
    public void not_changing_permissions_should_not_enable_save_button() {
        logInMattermost();

        driver.get("http://localhost:8065/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("save-button")));

        Assertions.assertFalse(saveButton.isEnabled(), "Save button shouldn't be enabled - there were no changes in settings");
    }
    // ELEMENTS INFORMATION

    @Test
    public void all_fields_should_have_placeholder_names() {
        driver.get("http://localhost:3000/new");
        WebElement title = driver.findElement(By.id("title"));
        WebElement location = driver.findElement(By.id("location"));
        WebElement description = driver.findElement(By.id("description"));

//        Assertions.assertEquals("Monthly Meetup", title.getDomAttribute("placeholder"),
//                "Placeholder value is not correct");
//        Assertions.assertEquals("Joe's Coffee Shop", location.getDomAttribute("placeholder"),
//                "Placeholder value is not correct");
//        Assertions.assertEquals("Hey everyone, please choose the dates that work for you!", description.getDomAttribute("placeholder"),
//                "Placeholder value is not correct");

        Assertions.assertAll(
                () -> Assertions.assertEquals("Miesięczne spotkanie",
                        title.getDomAttribute("placeholder"),
                        "Title field placeholder is not correct."),
                () -> Assertions.assertEquals("Sklep z kawą Joe",
                        location.getDomAttribute("placeholder"),
                        "Location field placeholder is not correct."),
                () -> Assertions.assertEquals("Cześć wszystkim, wybierzcie terminy, które Wam pasują!",
                        description.getDomAttribute("placeholder"),
                        "Description field placeholder is not correct.")
        );
    }


// ELEMENT
    @Test
    public void createNewEvent() {
        driver.get("http://localhost:3000/new");
        driver.findElement(By.id("title")).sendKeys("Knowledge Transfer");
        driver.findElement(By.className("btn-primary")).click();

        WebElement stepTracking = driver.findElement(By.className("tracking-tight"));
        Assertions.assertEquals("Etap 2 z 3", stepTracking.getText(),
                "Step tracking doesn't show that we are at the second step.");
    }

    // WINDOW SIZE

    @Test
    public void testWindows() {
        driver.navigate().to("https://www.nasa.gov/");

        Dimension size = new Dimension(854, 480);
        driver.manage().window().setSize(size);
        Point point = new Point(445, 30);
        driver.manage().window().setPosition(point);

        Assertions.assertEquals(size, driver.manage().window().getSize());
        Assertions.assertEquals(point, driver.manage().window().getPosition());

        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

    }


    // LANGUAGE VERSION

    @Test
    public void languageVersion() {

        // OPEN POLISH VERSION

        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        String wikiTitlePl = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitlePl, driver.getTitle(), "The title is not as expected.");
        String wikiUrlPl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(wikiUrlPl, driver.getCurrentUrl(), "The URL is not as expected");
        String pageElementPl = "\"wgPageContentLanguage\":\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(pageElementPl));

        // MOVE TO SPANISH VERSION

        driver.findElement(By.id("p-lang-btn-checkbox")).click();
        WebElement langSearch = driver.findElement(By.cssSelector(".uls-search-input-wrapper .languagefilter"));
        langSearch.sendKeys("hiszpański");
        driver.findElement(By.cssSelector("a.autonym[lang='es']")).click();

        String wikiTitleEs = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(wikiTitleEs, driver.getTitle(), "The title is not as expected.");
        String wikiUrlEs = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(wikiUrlEs, driver.getCurrentUrl(), "The URL is not as expected");
        String pageElementEs = "\"pageLanguageCode\":\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(pageElementEs));

    }

    // FUNDAMENTALS, NO ACCOUNT


    @Test
    public void no_account_button_should_no_access() {
        driver.get("http://localhost:8065/login/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alternate-link__link"))).click();

        Assertions.assertEquals("http://localhost:8065/access_problem", driver.getCurrentUrl(),
                "You were not redirected to 'Access Problem' page");
    }

    @Test
    public void successful_login_should_town_square_page() {
        logInMattermost();
        Assertions.assertEquals("http://localhost:8065/teamtestelka/channels/town-square", driver.getCurrentUrl(),
                "You were not redirected to Town Square page");
    }

    private void addTeamToFavourite() {
        favouriteButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toggleFavorite"))));
        wait.until(ExpectedConditions.attributeContains(favouriteButton, "class", "active"));
    }

    private void logInMattermost() {
        driver.get("http://localhost:8065/login/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue")))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input_loginId")))
                .sendKeys("tyszkiewicz.konopqa@gmail.com");
        driver.findElement(By.id("input_password-input"))
                .sendKeys("gOl&Xh0W#F3CONro");
        driver.findElement(By.id("saveSetting"))
                .click();
        wait.until(ExpectedConditions.titleIs("Town Square - team_testelka Mattermost"));

    }

    private WebElement favouriteButton;


}
