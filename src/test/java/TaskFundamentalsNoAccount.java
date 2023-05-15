import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskFundamentalsNoAccount {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void quitdriver() {
        driver.quit();
    }

    @Test
    public void no_account_button_should_no_access() {
        driver.get("http://localhost:8065/login/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alternate-link__link"))).click();

        Assertions.assertEquals("http://localhost:8065/access_problem", driver.getCurrentUrl(),
                "You were not redirected to 'Access Problem' page");
    }

    @Test
    public void successful_login_should_town_square_page(){
        driver.get("http://localhost:8065/login/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input_loginId"))).sendKeys("tyszkiewicz.konopqa@gmail.com");
        driver.findElement(By.id("input_password-input")).sendKeys("p!d7cJN5cZCM");
        driver.findElement(By.id("saveSetting")).click();
        wait.until(ExpectedConditions.titleIs("Town Square - TestTeam Mattermost"));

        Assertions.assertEquals("http://localhost:8065/testteam/channels/town-square", driver.getCurrentUrl(),
                "You were not redirected to Town Square page");
    }

}
