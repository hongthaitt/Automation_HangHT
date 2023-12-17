package lean03;

import common.BaseTest;
import common.CommonUI;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

public class Bai03 extends BaseTest {
    CommonUI commonUI;
    @Test
    public void customizeDropDown(){
        commonUI = new CommonUI(driver);
        String country = "Benin";
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        sleepInSecond(10);
        driver.findElement(By.xpath("//div[@class='ui fluid search selection dropdown']")).click();
        List<WebElement> listCuntry = driver.findElements(By.xpath("//div[@role='option']/span"));
        for(int i=0; i <listCuntry.size(); i++){
            if(listCuntry.get(i).getText().equals(country)){
                commonUI.scrollToViewElement(By.xpath("//span[text()='Benin']"));
                driver.findElement(By.xpath("//span[text()='Benin']")).click();
                break;
            }
        }
        String actualValue = driver.findElement(By.xpath("//div[@class='ui fluid search selection dropdown']")).getText();
        Assert.assertTrue(actualValue.equals(country));
    }

    @Test()
    public void alertPromt(){
        commonUI = new CommonUI(driver);
        driver.get("https://automationfc.github.io/basic-form/index.html");
        commonUI.scrollToViewElement(By.xpath("//button[text()='Click for JS Prompt']"));
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        commonUI.sleepInSecond(4);
        Alert alert = driver.switchTo().alert();
        String content = alert.getText();
        String key = "thaitth";
        Assert.assertTrue(content.equals("I am a JS prompt"));
        alert.sendKeys(key);
        alert.accept();
        //get result:
       compareValue(key);
    }
    public void compareValue(String key){
        String actualResult = driver.findElement(By.id("result")).getText();
        String expect = "You entered: " + key;
        System.out.println("expect: " + expect + "/ actual: " + actualResult);
        Assert.assertTrue(actualResult.equals(expect));
    }
    @Test
    public void byPassAuthentication(){
        commonUI = new CommonUI(driver);
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        commonUI.sleepInSecond(5);
        String actual = driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        String expect = "Congratulations! You must have the proper credentials.";
        Assert.assertTrue(actual.equals(expect));
    }
}
