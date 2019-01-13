package backend;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

  private static String url;
  private static WebDriver driver;
  private String username = "admin";
  private String password = "admin";

  @BeforeClass
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", "C:/Users/davyd/Downloads/geckodriver.exe");
    url = "http://localhost:8080";
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }

  @Test
  public void login() throws Exception {
    driver.navigate().to(url + "/login");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
    Thread.sleep(3000);
  }

  @Test
  public void register() throws Exception {
    driver.navigate().to(url + "/register");

    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("passwordconfirmation")).clear();
    driver.findElement(By.name("passwordconfirmation")).sendKeys(password);
    driver.findElement(By.name("register")).click();
    Thread.sleep(3000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
