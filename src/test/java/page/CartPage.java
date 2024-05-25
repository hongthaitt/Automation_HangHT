package page;

import common.CommonUI;
import constanst.CommonConstanst;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

import static common.CommonUI.*;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver _driver) {
        driver = _driver;
        new CommonUI(driver);
    }

    private String URL_CART = "https://yody.vn/cart";
    By removeCart = By.xpath("//*[@class='remove-cart']//*[@class='cart__btn-remove remove-item-cart ajaxifyCart--remove']");
    By deleteConfirm = By.xpath("//*[@class='button btn btn-confirm-delete']");
    By cart = By.xpath("//*[text()='GIỎ HÀNG']");
    By qtyPrd = By.xpath("//div[@class='ajaxcart__row cart-item  ']//div[@class='ajaxcart__qty input-group-btn']//input");
    By colorAndSizePrd = By.xpath("//div[@class='ajaxcart__row cart-item  ']//div[@class='ajaxcart__product-name-wrapper cart_name']//span");
    By pricePrd = By.xpath("//div[@class='ajaxcart__row cart-item  ']//div[@class='grid__item one-half text-center cart_prices']//span[contains(@class,'cart-price ')]"); //chu y
    By namePrd = By.xpath("//div[@class='ajaxcart__row cart-item  ']//div[@class='ajaxcart__product-name-wrapper cart_name']//a");
    By paymentNow = By.xpath("//*[@id='btn-proceed-checkout']//span");

    public void removeCart() {
        goToUrl(URL_CART);
        List<WebElement> listElement = listElement(removeCart);
        if (listElement.size() > 0) {
            System.out.println("Trong giỏ hàng có: " + listElement.size() + " sản phẩm");
            for (int i = 0; i < listElement.size(); i++) {
                clickByElement(listElement.get(0));
                clickOnElement(deleteConfirm);
            }
        } else {
            System.out.println("Không có sản phẩm nào trong giỏ hàng");
        }
    }

    public void clickOnCart() {
        refreshPage();
        waitForElementClickable(cart, 60);
        clickOnElementByJs(cart);
    }

    public void verifyProductInfoCartPage() {
        waitForUrlContain(URL_CART);
        List<WebElement> listName = driver.findElements(namePrd);
        List<WebElement> listPrice = driver.findElements(pricePrd);
        List<WebElement> listColorAndSize = driver.findElements(colorAndSizePrd);
        List<WebElement> listQty = driver.findElements(qtyPrd);
        System.out.println("size qty:" + listQty.size());
        System.out.println("size qty constant:" + CommonConstanst.productList.size());
        for (int i = 0; i < listName.size(); i++) {
            System.out.println("i: " + i);
            System.out.println("name real: " + listName.get(i).getText().toLowerCase() + "expect: " + CommonConstanst.productList.get(i).getName().toLowerCase());
            System.out.println("prce real: " + listPrice.get(i).getText().toLowerCase());
            System.out.println("prce expect: " + CommonConstanst.productList.get(i).getPrice().toLowerCase());
            System.out.println("price real: " + listPrice.get(i).getText().toLowerCase() + "expect: " + CommonConstanst.productList.get(i).getPrice().toLowerCase());
            System.out.println("qty real: " + listQty.get(i).getAttribute("value").toLowerCase() + "expect: " + CommonConstanst.productList.get(i).getQty().toLowerCase());
            System.out.println("size and color real: " + listColorAndSize.get(i).getText().toLowerCase() + "expect: " + CommonConstanst.productList.get(i).getColor().toLowerCase() + " / " + CommonConstanst.productList.get(0).getSize().toLowerCase());
            Assert.assertTrue(listName.get(i).getText().toLowerCase().equals(CommonConstanst.productList.get(i).getName().toLowerCase()));
            Assert.assertTrue(listPrice.get(i).getText().toLowerCase().equals(CommonConstanst.productList.get(i).getPrice().toLowerCase()));
            Assert.assertTrue(listQty.get(i).getAttribute("value").toLowerCase().equals(CommonConstanst.productList.get(i).getQty().toLowerCase()));
            Assert.assertTrue(listColorAndSize.get(i).getText().toLowerCase().equals(CommonConstanst.productList.get(i).getColor().toLowerCase() + " / " + CommonConstanst.productList.get(i).getSize().toLowerCase()));
        }
    }

    public void clickPayment() {
        System.out.println("size : " + driver.findElements(paymentNow).size());
        waitUntilVisible(paymentNow);
        clickOnElement(paymentNow);
    }

}
