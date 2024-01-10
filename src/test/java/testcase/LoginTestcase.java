package testcase;

import common.BaseTest;
import common.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import report.TestListener;

@Listeners(TestListener.class)
public class LoginTestcase extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ExcelHelpers excelHelpers = new ExcelHelpers();
    @BeforeClass
    public void setExcelData(){
        excelHelpers.setExcelFile("src/test/java/data/DataYodyfile.xlsx", "Sheet1");
    }
    @Test(priority = 1, description = "Login thành công vào yody 2")
    public void loginSuccess(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        String username = excelHelpers.getCellData("username",1);
        String pw = excelHelpers.getCellData("password",1);
        System.out.println("us/pw: " + username + "/" +pw);
        homePage.goToHomePage();
        homePage.clickOnLoginBtn();
        loginPage.verifyLoginUrl();
        loginPage.login(username, pw);
        homePage.verifyLoginSuccess();
    }
}
