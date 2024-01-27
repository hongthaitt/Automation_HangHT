package page;

import common.CommonUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    By header = By.xpath("//span[@class='search-product aa']");
    //    By listPrd = By.xpath("//h3[@class='product-name']/a");
    By listPrd = By.xpath("//h3[@class='product-name']/a");
    By detailElement =  By.xpath("//h3[@class='product-name']/a");


    @Step("Verify header")
    public void verifyHeader(String key) {
        waitUntilVisible(header);
        String expect = DEFAULT + " \"" + key + "\"";
        String actual = getText(header);
        verifyMessage(actual, expect);
    }

    public String getInnerHtml(WebElement webElement){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", webElement);
    }
    public void verifyResultSearch(String key) {
        String[] keySplit = key.toLowerCase().split("\\s+");
        List<WebElement> listResult = listEle(listPrd);
        System.out.println("Total product: " + listResult.size());
        for (int i = 0; i < listResult.size(); i++) {
            String prdName = listResult.get(i).getText().toLowerCase();
            System.out.println("In prd name thứ :  " + i + " " + prdName);
            count = 0;
            if(prdName.equals("") || prdName.equals(null)){
                prdName = getInnerHtml(listResult.get(i)).toLowerCase();
                System.out.println("Prd inner: " + prdName);
            }
            for (int j = 0; j < keySplit.length; j++) {
                System.out.println("key thứ j: " + keySplit[j]);
                if (prdName.contains(keySplit[j])){
                    count ++;
                }
            }
            assertTrue(count > 0);
        }
    }
}
