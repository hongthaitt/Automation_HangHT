package testcase;

import common.BaseTest;
import common.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.RegisterPage;
import report.TestListener;

@Listeners(TestListener.class)
public class RegisterTestcase extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    ExcelHelpers excelHelpers = new ExcelHelpers();
    String name, phone, email, password;
    @BeforeClass
    public void setExcelData() {
        excelHelpers.setExcelFile("src/test/java/data/DataYodyfile.xlsx", "register");
    }

    @Test(priority = 1, description = "Đăng ký thành công 1 tài khoản")
    public void registerSuccess() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        name = excelHelpers.getCellData("name", 1);
        email = excelHelpers.getCellData("email", 1);
        password = excelHelpers.getCellData("password", 1);
        homePage.goToHomePage();
        homePage.clickOnRegister();
        registerPage.registerSuccess(name, email, password);
    }
}
