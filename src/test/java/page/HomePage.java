package page;

import common.CommonUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import report.AllureManager;

import static common.CommonUI.*;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver _driver){
        driver = _driver;
        new CommonUI(driver);
    }

    String URL_HOMEPAGE = "https://yody.vn/";
    By loginBtn = By.xpath("//a[text()='ĐĂNG NHẬP']");
    By accountBtn = By.xpath("//a[@class='logined']//span[text()='Cá nhân']");

    @Step("Open yody page:")
    public void goToHomePage(){
        goToUrl(URL_HOMEPAGE);
        AllureManager.saveTextLog("URL: "+URL_HOMEPAGE);
        AllureManager.saveScreenshotPNG();

    }

    @Step("Click on login btn")
    public void clickOnLoginBtn(){
        clickOnElement(loginBtn);
    }
    @Step("Verify login success")
    public void verifyLoginSuccess(){
        waitUntilVisible(accountBtn);
    }
}
