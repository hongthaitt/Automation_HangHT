package testcase;

import common.BaseTest;
import common.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.*;
import report.TestListener;

@Listeners(TestListener.class)
public class CheckoutTestcase extends BaseTest {
    LoginPage loginPage;
    ResultSearchPage searchPage;
    CheckoutPage checkoutPage;
    CartPage cartPage;
    HomePage homePage;
    DetailProductPage detailProductPage;
    String username, password, key, name, phone, address, prvBilling, distBilling, wardBilling, qty;
    ExcelHelpers excelHelpers = new ExcelHelpers();
    @BeforeClass
    public void setUpBrower() throws Exception {
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/java/data/DataYodyfile.xlsx", "Sheet3");
    }

    @Test(priority = 1, description = "Kiểm tra thanh toán bằng app VPN pay ")
    public void TC05(){
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        searchPage = new ResultSearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        detailProductPage = new DetailProductPage(driver);
        username = excelHelpers.getCellData("user_name", 1);
        password = excelHelpers.getCellData("password", 1);
        key = excelHelpers.getCellData("key_search", 1);
        qty = excelHelpers.getCellData("qty", 1);
        name = excelHelpers.getCellData("name_billing", 1);
        phone = excelHelpers.getCellData("phone_billing", 1);
        address = excelHelpers.getCellData("address_billing", 1);
        prvBilling = excelHelpers.getCellData("prv_billing", 1);
        distBilling = excelHelpers.getCellData("dist_billing", 1);
        wardBilling = excelHelpers.getCellData("ward_billing", 1);
        String[] keyList = key.split(";");
        String[] qtyList = qty.split(";");

        homePage.goToHomePage();
        homePage.clickOnLoginBtn();
        loginPage.verifyLoginUrl();
        loginPage.login(username, password);
        homePage.verifyLoginSuccess();

        cartPage.removeCart();

        homePage.inputKeySearch(keyList[0]);
        homePage.clickOnSearchBtn();
        searchPage.clickToDetailProductPolo(keyList[0]);
        detailProductPage.getInfoPrd(qtyList[0]);
        detailProductPage.setAddToCart();

        homePage.inputKeySearch(keyList[1]);
        homePage.clickOnSearchBtn();
        searchPage.clickToDetailProductPolo(keyList[1]);
        detailProductPage.getInfoPrd(qtyList[1]);
        detailProductPage.setAddToCart();

        cartPage.clickOnCart();
        cartPage.verifyProductInfoCartPage();
        cartPage.clickPayment();
        checkoutPage.checkoutSuccessWithVnPayMethod(name, phone, address, prvBilling, distBilling, wardBilling);
    }

}
