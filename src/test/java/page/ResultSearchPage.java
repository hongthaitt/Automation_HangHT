package page;

import common.CommonUI;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import report.AllureManager;

import java.util.List;

import static common.CommonUI.*;

public class ResultSearchPage {
    static WebDriver driver;

    public ResultSearchPage(WebDriver _driver) {
        driver = _driver;
        new CommonUI(driver);
    }

    int count;
    String DEFAULT = "KẾT QUẢ TÌM KIẾM SẢN PHẨM";
    By header = By.xpath("//span[@class='search-product aa' or @class='search-product']");
    //    By listPrd = By.xpath("//h3[@class='product-name']/a");
    By listPrd = By.xpath("//h3[@class='product-name']/a");
    By detailElement = By.xpath("//h3[@class='product-name']/a");
    By listPrdSpace = By.xpath("//form[@id='header-search-product']//parent::div//div[@class='d-title name_SP']");
    By noProduct = By.xpath("//div[@class='search-terms search-product']");

    @Step("Verify header")
    public void verifyHeader(String key) {
        waitUntilVisible(header);
        String expect = DEFAULT + " \"" + key + "\"";
        String actual = getText(header);
        verifyMessage(actual, expect);
    }

    public String getInnerHtml(WebElement webElement) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", webElement);
    }

    public void verifyResultSearch(String key) {
        scrollUntilEndPage();
        String[] keySplit = key.toLowerCase().split("\\s+");
        List<WebElement> listResult = listEle(listPrd);
        System.out.println("Total product: " + listResult.size());
        for (int i = 0; i < listResult.size(); i++) {
            String prdName = listResult.get(i).getText().toLowerCase();
            System.out.println("In prd name thứ :  " + i + " " + prdName);
            count = 0;
            if (prdName.equals("") || prdName.equals(null)) {
                prdName = getInnerHtml(listResult.get(i)).toLowerCase();
                System.out.println("Prd inner: " + prdName);
            }
            for (int j = 0; j < keySplit.length; j++) {
                System.out.println("key thứ j: " + keySplit[j]);
                if (prdName.contains(keySplit[j])) {
                    count++;
                }
            }
            assertTrue(count > 0);
        }
    }

    public void verifyResultSpaceSearch(String key) {
        String[] keySplit = key.toLowerCase().split("\\s+");
        List<WebElement> listResult = listEle(listPrdSpace);
        System.out.println("Total product: " + listResult.size());
        for (int i = 0; i < listResult.size(); i++) {
            String prdName = listResult.get(i).getText().toLowerCase();
            System.out.println("In prd name thứ :  " + i + " " + prdName);
            count = 0;
            if (prdName.equals("") || prdName.equals(null)) {
                prdName = getInnerHtml(listResult.get(i)).toLowerCase();
                System.out.println("Prd inner: " + prdName);
            }
            for (int j = 0; j < keySplit.length; j++) {
                System.out.println("key thứ j: " + keySplit[j]);
                if (prdName.contains(keySplit[j])) {
                    count++;
                }
            }
            assertTrue(count > 0);
        }
    }
    @Step("Verify no result: ")
    public void verifyNoResult() {
        List<WebElement> listResult = listEle(listPrd);
        AllureManager.saveScreenshotPNG();
        Assert.assertTrue(listResult.size() == 0);
    }

    @Step("Verify message no product: ")
    public void messageNoProduct(String key){
        String actual = getText(noProduct);
        String expect = "Tìm kiếm " + key + " của bạn không có sản phẩm phù hợp";
        verifyMessage(actual, expect);
    }

    @Step("Verify have scroll")
    public void verifyHaveScroll(){
        assertTrue(haveScroll());
    }
}
