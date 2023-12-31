package page;

import common.CommonUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void goToHomePage(){
        goToUrl(URL_HOMEPAGE);
    }

    public void clickOnLoginBtn(){
        clickOnElement(loginBtn);
    }
    public void verifyLoginSuccess(){
        waitUntilVisible(accountBtn);
    }
}
