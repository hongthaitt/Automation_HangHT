package testcase;

import common.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class LoginTestcase extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    @Test
    public void loginSuccess(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.goToHomePage();
        homePage.clickOnLoginBtn();
        loginPage.verifyLoginUrl();
        loginPage.login("tester@gmail.com", "123456");
        homePage.verifyLoginSuccess();
    }
}
