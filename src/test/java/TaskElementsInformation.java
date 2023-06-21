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

public class TaskElementsInformation {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

//    @AfterEach
//    public void quitdriver() {
//        driver.quit();
//    }


    //Napisz test który potwierdzi, że jeżeli nie „dotkniemy” ustawień uprawnień, to przycisk „Save” nie będzie aktywny.

    @Test
    public void not_changing_permissions_should_not_enable_save_button(){
        driver.get("http://localhost:8065/login/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input_loginId"))).sendKeys("tyszkiewicz.konopqa@gmail.com");
        driver.findElement(By.id("input_password-input")).sendKeys("p!d7cJN5cZCM");
        driver.findElement(By.id("saveSetting")).click();
        wait.until(ExpectedConditions.titleIs("Town Square - TestTeam Mattermost"));

        driver.get("http://localhost:8065/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("save-button")));

        Assertions.assertFalse(saveButton.isEnabled(), "Save button shouldn't be enabled - there were no changes in settings");
    }
    // Twoim zadaniem jest potwierdzenie, że placeholdery dla pól odpowiadających tytułowi, lokacji i opisowi wydarzenia,
    // są równe kolejno: „Monthly Meetup”, „Joe’s Coffee Shop” i „Hey everyone, please choose the dates that work for you!”.

    @Test
    public void all_fields_should_have_placeholder_names(){
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
                () -> Assertions.assertEquals("Monthly Meetup",
                        title.getDomAttribute("placeholder"),
                        "Title field placeholder is not correct."),
                () -> Assertions.assertEquals("Joe's Coffee Shop",
                        location.getDomAttribute("placeholder"),
                        "Location field placeholder is not correct."),
                () -> Assertions.assertEquals("Hey everyone, please choose the dates that work for you!",
                        description.getDomAttribute("placeholder"),
                        "Description field placeholder is not correct.")
        );
    }
}
