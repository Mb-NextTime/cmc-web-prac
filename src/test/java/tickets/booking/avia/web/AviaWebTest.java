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

import static org.junit.jupiter.api.Assertions.*;
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
    void SingleBookFlight() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("max@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Profile")).click();
            assertEquals(1, driver.findElements(By.tagName("a")).size());

            driver.get("http://localhost:" + port);
            driver.findElement(By.name("dCity")).sendKeys("Moscow");
            driver.findElement(By.name("aCity")).sendKeys("Oslo");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('dDate')[0].valueAsNumber = Date.parse('2023-11-21');");
            ((JavascriptExecutor)driver).executeScript ("document.getElementsByName('aDate')[0].valueAsNumber = Date.parse('2023-11-22');");
            driver.findElement(By.className("home-search")).click();
            driver.findElement(By.id("flight-5")).click();

            driver.findElement(By.name("0_0")).click();
            driver.findElement(By.tagName("button")).click();

            assertEquals(2, driver.findElements(By.tagName("a")).size());

        } finally {
            driver.quit();
        }
    }

    @Test
    @Transactional
    @Rollback
    void MultipleBookFlightFromProfile() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("sam@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Profile")).click();
            assertEquals(2, driver.findElements(By.tagName("a")).size());

            driver.findElement(By.partialLinkText("0:1, price: $900")).click();

            driver.findElement(By.name("0_0")).click();
            driver.findElement(By.name("1_1")).click();
            driver.findElement(By.tagName("button")).click();

            assertEquals(4, driver.findElements(By.tagName("a")).size());

        } finally {
            driver.quit();
        }
    }

    @Test
    @Transactional
    @Rollback
    void Registration() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // check there is no such customer yet
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("example@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();
            assertFalse(driver.getPageSource().contains("Profile"));

            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Register")).click();
            driver.findElement(By.name("name")).sendKeys("example");
            driver.findElement(By.name("email")).sendKeys("example@mail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Profile")).click();
            assertTrue(driver.getPageSource().contains("Your name: example"));
        } finally {
            driver.quit();
        }
    }

    @Test
    @Transactional
    @Rollback
    void FailedRegistration() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Register")).click();
            driver.findElement(By.name("name")).sendKeys("1");
            driver.findElement(By.name("email")).sendKeys("1@mail.com");
            driver.findElement(By.name("password")).sendKeys("1");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Logout")).click();

            driver.findElement(By.linkText("Register")).click();
            driver.findElement(By.name("name")).sendKeys("1");
            driver.findElement(By.name("email")).sendKeys("1@mail.com");
            driver.findElement(By.name("password")).sendKeys("1");
            driver.findElement(By.tagName("button")).click();

            assertFalse(driver.getPageSource().contains("Profile"));
        } finally {
            driver.quit();
        }
    }

    @Test
    @Transactional
    @Rollback
    void RegisterThenLogin() throws Exception {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("http://localhost:" + port);
            driver.findElement(By.linkText("Register")).click();
            driver.findElement(By.name("name")).sendKeys("2");
            driver.findElement(By.name("email")).sendKeys("2@mail.com");
            driver.findElement(By.name("password")).sendKeys("2");
            driver.findElement(By.tagName("button")).click();

            driver.findElement(By.linkText("Logout")).click();

            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("email")).sendKeys("2@mail.com");
            driver.findElement(By.name("password")).sendKeys("2");
            driver.findElement(By.tagName("button")).click();

            assertTrue(driver.getPageSource().contains("Profile"));
        } finally {
            driver.quit();
        }
    }
}
