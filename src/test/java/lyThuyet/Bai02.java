package lyThuyet;


import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Bai02 extends BaseTest {

    @Test
    public void TC01(){
        driver.get("https://yody.vn/");
        sleepInSecond(10);
        driver.findElement(By.xpath("//a[@class='register']")).click();
        sleepInSecond(10);
        Assert.assertEquals(driver.getCurrentUrl(), "https://yody.vn/account/register");
    }

    @Test
    public void TC02(){
        driver.get("https://demoqa.com/select-menu");
        sleepInSecond(5);
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Purple");
        String value= select.getFirstSelectedOption().getText();
        Assert.assertEquals("Purple", value);
        List<String> a = new ArrayList<>();
    }

}
