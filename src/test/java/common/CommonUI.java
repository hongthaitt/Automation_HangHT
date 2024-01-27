package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import report.AllureManager;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CommonUI {
    public static WebDriver driver;
    public static int TIME_OUT = 20;
    public CommonUI(WebDriver y){
        driver = y;
    }
    public static void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sleep in " + timeInSecond + " seconds");
    }
    public static void scrollToViewElement(By by){
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean checkDisplay(By by){
        return driver.findElement(by).isDisplayed();
    }
    public static void vefifyDisplay(By by){
        Assert.assertTrue(checkDisplay(by));
    }
    public static void clickOnElement(By by){
        waitUntilVisible(by);
        driver.findElement(by).click();
    }
    public static void goToUrl(String url){
        driver.get(url);
    }
    public static void setText(By by, String value){
        driver.findElement(by).sendKeys(value);
        AllureManager.saveScreenshotPNG();
    }
    public static String getText(By by){
        return driver.findElement(by).getText();
    }
    public static void verifyMessage(String actualMessage, String expectMessage){
        System.out.println("Actual Message: " + actualMessage);
        System.out.println("expectMessage: " + expectMessage);
        Assert.assertTrue(actualMessage.equals(expectMessage));
    }
    public static void assertTrue(boolean condition){
        Assert.assertTrue(condition);
    }
    public static List<WebElement> listElement(By by){
        return driver.findElements(by);
    }

    public static void verifyCurrentUrl(String expectUrl){
        Assert.assertTrue(driver.getCurrentUrl().equals(expectUrl));
    }
    public static void verifyTitle(String expectTitle){
        Assert.assertTrue(driver.getTitle().equals(expectTitle));
    }

    public static void switchFrameByWebElement(By by){
        WebElement frame = driver.findElement(by);
        driver.switchTo().frame(frame);
    }
    public static void switchFrameById(String id){
        driver.switchTo().frame(id);
    }
    public static void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    //Chỉ dùng trong trường hợp script có mỗi 2 window/tab
    public static void switchWindowById(String otherID){
        Set<String> listWindow = driver.getWindowHandles();
        for(String id : listWindow){
            if(!id.equals(otherID)){
                driver.switchTo().window(id);
            }
        }
    }

    //Dùng  trong trường hợp có nhiều hơn 2 windown/tab
    public static void switchWindowByTitle(String expectTitle){
        Set<String> listWindow = driver.getWindowHandles();
        for(String id : listWindow){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(expectTitle)){
                sleepInSecond(2);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }
    public static void upload01File(By by, String path){
        driver.findElement(by).sendKeys(path);
        sleepInSecond(2);
    }

    public static void waitUntilInvisible(By by){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
        catch (Exception e){
            Assert.fail("ELEMENT not invisible!");
        }
    }

    public static void waitUntilVisible(By by){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }
        catch (Exception e){
            Assert.fail("ELEMENT not visible!");
        }
    }
    public static void implicitWait(int timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
    public static List<WebElement> listEle(By by){
        return driver.findElements(by);
    }
}
