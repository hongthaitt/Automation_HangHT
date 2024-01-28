package testcase;

import common.BaseTest;
import common.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.ResultSearchPage;
import report.TestListener;

@Listeners(TestListener.class)
public class SearchTestcase extends BaseTest {
    HomePage homePage;
    ResultSearchPage resultSearchPage;
     ExcelHelpers excelHelpers = new ExcelHelpers();

    @BeforeClass
    public void setExcelData() {
        excelHelpers.setExcelFile("src/test/java/data/DataYodyfile.xlsx", "Sheet2");
    }

    @Test(priority = 1, description = "Search từ khóa chưa 1 phần tên sản phẩm")
    public void search01(){
        homePage = new HomePage(driver);
        resultSearchPage = new ResultSearchPage(driver);
        String key = excelHelpers.getCellData("Data", 2);
        homePage.goToHomePage();
        homePage.inputKeySearch(key);
        homePage.clickOnSearchBtn();
        resultSearchPage.verifyHeader(key);
        resultSearchPage.verifyResultSearch(key);
    }

    @Test(priority = 1, description = "Search từ khóa chưa 1 phần tên sản phẩm và nhập dấu cách")
    public void search02(){
        homePage = new HomePage(driver);
        resultSearchPage = new ResultSearchPage(driver);
        String key = excelHelpers.getCellData("Data", 2);
        homePage.goToHomePage();
        homePage.inputKeySearch(key);
        homePage.pressToSpace();
        resultSearchPage.verifyResultSpaceSearch(key);
    }
    @Test(priority = 1, description = "Search từ khóa không có sản phẩm nào")
    public void search03(){
        homePage = new HomePage(driver);
        resultSearchPage = new ResultSearchPage(driver);
        String key = excelHelpers.getCellData("Data", 5);
        homePage.goToHomePage();
        homePage.inputKeySearch(key);
        homePage.clickOnSearchBtn();
        resultSearchPage.verifyHeader(key);
        resultSearchPage.verifyNoResult();
        resultSearchPage.messageNoProduct(key);
    }
    @Test(priority = 1, description = "Search với khoảng trắng")
    public void search04(){
        homePage = new HomePage(driver);
        resultSearchPage = new ResultSearchPage(driver);
        homePage.goToHomePage();
        homePage.clickOnSearchBtn();
        homePage.verifyNullKey();
    }

    @Test(priority = 1, description = "Search từ khóa không có sản phẩm nào và kiểm tra scroll")
    public void search05(){
        homePage = new HomePage(driver);
        resultSearchPage = new ResultSearchPage(driver);
        String key = excelHelpers.getCellData("Data", 5);
        homePage.goToHomePage();
        homePage.inputKeySearch(key);
        homePage.clickOnSearchBtn();
        resultSearchPage.verifyHaveScroll();
    }
}
