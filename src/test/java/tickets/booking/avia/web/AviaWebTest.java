package tickets.booking.avia.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AviaWebTest {
    @LocalServerPort
    private int port;

    @Test
    void ListAllFlights() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.className("home-search")).click();//.sendKeys("cheese" + Keys.ENTER);
            var nResults = driver.findElements(By.partialLinkText("Base ticket price")).size();
            assertEquals(15, nResults);
        } finally {
            driver.quit();
        }
    }

    @Test
    void ListFilterFlights() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.name("dCity")).sendKeys("Moscow");
            driver.findElement(By.name("aCity")).sendKeys("Oslo");
            driver.findElement(By.className("home-search")).click();
            var nResults = driver.findElements(By.partialLinkText("Base ticket price")).size();
            assertEquals(6, nResults);
        } finally {
            driver.quit();
        }
    }

    @Test
    void OpenFlightPage() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.name("dCity")).sendKeys("Moscow");
            driver.findElement(By.name("aCity")).sendKeys("Oslo");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('dDate')[0].valueAsNumber = Date.parse('2023-11-21');");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('aDate')[0].valueAsNumber = Date.parse('2023-11-22');");
            driver.findElement(By.className("home-search")).click();
            var nResults = driver.findElements(By.partialLinkText("Base ticket price")).size();
            assertEquals(1, nResults);
            driver.findElement(By.id("flight-5")).click();
            var flight = wait.until(presenceOfElementLocated(By.tagName("body")));
            assertTrue(flight.getText().contains("Base ticket price: $505"));

        } finally {
            driver.quit();
        }
    }

    @Test
    void Login() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("john@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            assertTrue(driver.getPageSource().contains("Logout"));

        } finally {
            driver.quit();
        }
    }

    @Test
    void Logout() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("john@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Logout")).click();
            assertTrue(driver.getPageSource().contains("Login"));

        } finally {
            driver.quit();
        }
    }

    @Test
    void OpenProfile() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("john@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Profile")).click();
            assertEquals(1, driver.findElements(By.tagName("a")).size());

        } finally {
            driver.quit();
        }
    }

    @Test
    @Transactional
    @Rollback
    void BookFlight() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("max@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.name("dCity")).sendKeys("Moscow");
            driver.findElement(By.name("aCity")).sendKeys("Oslo");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('dDate')[0].valueAsNumber = Date.parse('2023-11-21');");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('aDate')[0].valueAsNumber = Date.parse('2023-11-22');");
            driver.findElement(By.className("home-search")).click();
            driver.findElement(By.id("flight-5")).click();

            driver.findElement(By.name("0_0")).click();
            driver.findElement(By.name("0_1")).click();
            driver.findElement(By.tagName("button")).click();

            assertEquals(3, driver.findElements(By.tagName("a")).size());

        } finally {
            driver.quit();
        }
    }
}
