package learn01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai01 {
    public WebDriver driver;

    @BeforeMethod
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/webDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test01() {
        //Go to url:
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//span[@class='label'][text()='Account']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
        String curentUrl = driver.getCurrentUrl();
        System.out.println("current url: " + curentUrl);
        Assert.assertEquals(curentUrl, "http://live.techpanda.org/index.php/customer/account/login/");
        //Click on create account:
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void test02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(3);
        //check isDisplay mail:
        if(driver.findElement(By.id("mail")).isDisplayed()){
            System.out.println("element is displayed");
        }
        else {
            System.out.println("element isnt displayed");
        }
        Assert.assertTrue(driver.findElement(By.id("mail")).isDisplayed());

        //Check isEnable email:
        if(driver.findElement(By.id("mail")).isEnabled()){
            System.out.println("element is isEnabled");
        }
        else {
            System.out.println("element isnt isEnabled");
        }
        Assert.assertTrue(driver.findElement(By.id("mail")).isEnabled());
        //Check disable:
        if(driver.findElement(By.id("disable_password")).isEnabled()){
            System.out.println("element is isEnabled");
        }
        else {
            System.out.println("element is disable");
        }
        Assert.assertFalse(driver.findElement(By.id("disable_password")).isEnabled());
        //is selected:
        driver.findElement(By.id(("under_18"))).click();
        if (driver.findElement(By.id("under_18")).isSelected()) {
            System.out.println("element is isSelected");
        } else {
            System.out.println("element isnt isSelected");
        }
        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sleep in " + timeInSecond + " seconds");
    }

}
