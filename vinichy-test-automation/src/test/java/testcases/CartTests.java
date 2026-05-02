package testcases;

import common.Constant;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import common.LoggerUtil;
import testdata.CartTestData;
import testdata.CartTestData.CartLine;

import java.util.Map;

public class CartTests extends testcases.BaseTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String CART_URL = BASE_URL + "/gio-hang";

    private static final String EMAIL = "thaophan@gmail.com";
    private static final String PASSWORD = "123456";

    private static final String SKU_NORMAL = "HA634-DEN";
    private static final String SKU_STOCK_1 = "HA761-NAU";
    private static final String SKU_VARIANT = "HA623-HONG";
    private static final String SKU_VARIANT_CHANGED = "HA623-DEN";
    private static final String SKU_OUT_OF_STOCK = "HA494-HONG";
    private static final String SKU_DELETE_A = "TOTE-BAN-NGUYET-TRANG";
    private static final String SKU_DELETE_B = "TOTE-CO-DIEN-DEN";

    private static final int NORMAL_STOCK = 10;
    private static final int STOCK_ONE = 1;
    private static final int DEFAULT_STOCK = 100;
    private static final int OUT_OF_STOCK = 0;

    private final CartTestData cartTestData = new CartTestData();

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void initPage() {
        LoggerUtil.info("=== SETUP CART TEST ===");
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        LoggerUtil.info("CartPage and CheckoutPage initialized.");
    }

    private void openCartWith(CartLine... lines) {
        cartTestData.seedCart(EMAIL, PASSWORD, lines);
        loginByApi();
        cartPage.open();

        if (lines.length == 0) {
            Assert.assertTrue(cartPage.isEmptyCartDisplayed(), "Expected an empty cart");
            return;
        }

        for (CartLine line : lines) {
            Assert.assertTrue(
                    cartPage.hasCartItemBySku(line.sku()),
                    "Missing seeded SKU in cart: " + line.sku()
            );
        }
    }

    private void loginByApi() {
        Constant.getDriver().manage().deleteAllCookies();
        Constant.getDriver().get(BASE_URL);

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ((JavascriptExecutor) Constant.getDriver())
                .executeAsyncScript(
                        "const done = arguments[arguments.length - 1];" +
                                "fetch('/api/auth/login', {" +
                                "  method: 'POST'," +
                                "  headers: {'Content-Type': 'application/json'}," +
                                "  body: JSON.stringify({email: arguments[0], matKhau: arguments[1]})" +
                                "}).then(async response => done({status: response.status, body: await response.text()}))" +
                                ".catch(error => done({status: 0, body: String(error)}));",
                        EMAIL,
                        PASSWORD
                );

        int status = ((Number) result.get("status")).intValue();
        Assert.assertEquals(status, 200, "Login API failed: " + result.get("body"));
    }

    private CartLine normal(int quantity) {
        return CartTestData.line(SKU_NORMAL, quantity, NORMAL_STOCK);
    }

    private CartLine stockOne() {
        return CartTestData.line(SKU_STOCK_1, 1, STOCK_ONE);
    }

    private CartLine variant() {
        return CartTestData.line(SKU_VARIANT, 1, DEFAULT_STOCK);
    }

    private CartLine variantChanged() {
        return CartTestData.line(SKU_VARIANT_CHANGED, 1, DEFAULT_STOCK);
    }

    private CartLine outOfStock() {
        return CartTestData.line(SKU_OUT_OF_STOCK, 1, OUT_OF_STOCK);
    }

    private CartLine deleteA() {
        return CartTestData.line(SKU_DELETE_A, 1, DEFAULT_STOCK);
    }

    private CartLine deleteB() {
        return CartTestData.line(SKU_DELETE_B, 1, DEFAULT_STOCK);
    }

    private void assertToastContains(String expectedMessage) {
        String actualMessage = cartPage.getToastText();

        Assert.assertFalse(actualMessage.isBlank(), "Expected a toast message");
        Assert.assertTrue(
                actualMessage.contains(expectedMessage),
                "Sai nội dung thông báo. Expected contains: " + expectedMessage + ", Actual: " + actualMessage
        );
    }

    private boolean waitForLoginPopupDisplayed() {
        long endTime = System.currentTimeMillis() + 7000;

        while (System.currentTimeMillis() < endTime) {
            Boolean visible = (Boolean) ((JavascriptExecutor) Constant.getDriver())
                    .executeScript(
                            "const el = document.querySelector('#loginPopup');" +
                                    "return !!el && el.classList.contains('active') && " +
                                    "window.getComputedStyle(el).display !== 'none';"
                    );

            if (Boolean.TRUE.equals(visible)) {
                return true;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return false;
    }

    @Test(priority = 1)
    public void TC10_F001() {
        LoggerUtil.info("START TEST: TC10_F001 - Click icon giỏ hàng khi chưa đăng nhập");
        Constant.getDriver().manage().deleteAllCookies();
        Constant.getDriver().get(BASE_URL);

        ((JavascriptExecutor) Constant.getDriver()).executeScript(
                "window.isLoggedIn = false;" +
                        "document.body.setAttribute('data-logged-in', 'false');"
        );

        LoggerUtil.info("Simulating cart icon click...");
        ((JavascriptExecutor) Constant.getDriver()).executeScript(
                "handleCartClick(new MouseEvent('click', {bubbles:true, cancelable:true}));"
        );

        boolean isPopupVisible = waitForLoginPopupDisplayed();
        LoggerUtil.info("Result: Login popup displayed=" + isPopupVisible);
        Assert.assertTrue(
                isPopupVisible,
                "Khi chưa đăng nhập và click icon giỏ hàng, hệ thống phải hiển thị popup đăng nhập"
        );

        String currentUrl = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: Current URL=" + currentUrl);
        Assert.assertTrue(
                currentUrl.equals(BASE_URL + "/")
                        || currentUrl.equals(BASE_URL + "/#"),
                "Khi chưa đăng nhập, hệ thống không được chuyển sang trang giỏ hàng"
        );
    }

    @Test(priority = 2)
    public void TC10_F002() {
        LoggerUtil.info("START TEST: TC10_F002 - Breadcrumb 'Trang chủ'");
        openCartWith(normal(1));

        LoggerUtil.info("Clicking Home breadcrumb...");
        cartPage.clickHomeBreadcrumb();

        String currentUrl = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: Current URL=" + currentUrl);
        Assert.assertEquals(
                currentUrl,
                BASE_URL + "/",
                "Không điều hướng đúng về trang chủ"
        );
    }

    @Test(priority = 3)
    public void TC10_F003() {
        LoggerUtil.info("START TEST: TC10_F003 - Click 'Tiến hành đặt hàng'");
        openCartWith(normal(1));

        LoggerUtil.info("Clicking Checkout button...");
        cartPage.clickCheckout();

        boolean isAtCheckout = checkoutPage.isAtCheckoutPage();
        LoggerUtil.info("Result: Is at checkout page=" + isAtCheckout);
        Assert.assertTrue(isAtCheckout, "Không điều hướng đến trang đặt hàng");
    }

    @Test(priority = 4)
    public void TC10_F004() {
        LoggerUtil.info("START TEST: TC10_F004 - Tăng số lượng bằng nút '+'");
        openCartWith(normal(1));

        int beforeQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity before: " + beforeQty);

        LoggerUtil.info("Clicking Plus button for SKU: " + SKU_NORMAL);
        cartPage.clickPlusBySku(SKU_NORMAL);

        int afterQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after: " + afterQty);

        Assert.assertEquals(afterQty, beforeQty + 1);
        Assert.assertEquals(cartPage.getSummaryTotal(), cartPage.sumAllItemTotals());
    }

    @Test(priority = 5)
    public void TC10_F005() {
        LoggerUtil.info("START TEST: TC10_F005 - Giảm số lượng bằng nút '-'");
        openCartWith(normal(2));

        int beforeQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity before: " + beforeQty);

        LoggerUtil.info("Clicking Minus button for SKU: " + SKU_NORMAL);
        cartPage.clickMinusBySku(SKU_NORMAL);

        int afterQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after: " + afterQty);

        Assert.assertEquals(afterQty, beforeQty - 1);
        Assert.assertEquals(cartPage.getSummaryTotal(), cartPage.sumAllItemTotals());
    }

    @Test(priority = 6)
    public void TC10_F006() {
        LoggerUtil.info("START TEST: TC10_F006 - Tăng số lượng vượt tồn kho");
        openCartWith(stockOne());

        LoggerUtil.info("Clicking Plus until reach stock for SKU: " + SKU_STOCK_1);
        cartPage.clickPlusUntilReachStockBySku(SKU_STOCK_1);

        int qty = cartPage.getQuantityBySku(SKU_STOCK_1);
        LoggerUtil.info("Quantity: " + qty);

        Assert.assertEquals(qty, STOCK_ONE);
        assertToastContains("Số lượng vượt ngưỡng tồn kho");
    }

    @Test(priority = 7)
    public void TC10_F007() {
        LoggerUtil.info("START TEST: TC10_F007 - Nhập số lượng hợp lệ trực tiếp");
        openCartWith(normal(1));

        LoggerUtil.info("Setting quantity to 2 for SKU: " + SKU_NORMAL);
        cartPage.setQuantityBySku(SKU_NORMAL, "2");

        int qty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity: " + qty);
        Assert.assertEquals(qty, 2);
        
        long itemTotal = cartPage.getItemTotalBySku(SKU_NORMAL);
        long unitPrice = cartPage.getUnitPriceBySku(SKU_NORMAL);
        LoggerUtil.info("Item Total: " + itemTotal + " | Unit Price: " + unitPrice);
        
        Assert.assertEquals(
                itemTotal,
                unitPrice * 2,
                "Thành tiền từng sản phẩm chưa cập nhật đúng"
        );
        Assert.assertEquals(
                cartPage.getSummaryTotal(),
                cartPage.sumAllItemTotals(),
                "Tổng tiền chưa cập nhật đúng sau khi nhập số lượng hợp lệ"
        );
    }

    @Test(priority = 8)
    public void TC10_F008() {
        LoggerUtil.info("START TEST: TC10_F008 - Nhập số lượng = 0");
        openCartWith(normal(2));

        LoggerUtil.info("Setting quantity to 0 for SKU: " + SKU_NORMAL);
        cartPage.setQuantityBySku(SKU_NORMAL, "0");

        int qty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after setting 0: " + qty);
        Assert.assertTrue(qty >= 1);
    }

    @Test(priority = 9)
    public void TC10_F009() {
        LoggerUtil.info("START TEST: TC10_F009 - Nhập số lượng cực lớn");
        openCartWith(normal(1));

        LoggerUtil.info("Setting quantity to 999 for SKU: " + SKU_NORMAL);
        cartPage.setQuantityBySku(SKU_NORMAL, "999");

        int qty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after setting 999: " + qty);
        Assert.assertTrue(qty <= NORMAL_STOCK);
    }

    @Test(priority = 10)
    public void TC10_F010() {
        LoggerUtil.info("START TEST: TC10_F010 - Nhập số lượng là chữ");
        openCartWith(normal(3));

        int beforeQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity before: " + beforeQty);

        LoggerUtil.info("Setting quantity to 'abc' for SKU: " + SKU_NORMAL);
        cartPage.setQuantityBySku(SKU_NORMAL, "abc");

        int afterQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after: " + afterQty);
        Assert.assertEquals(afterQty, beforeQty);
    }

    @Test(priority = 11)
    public void TC10_F011() {
        LoggerUtil.info("START TEST: TC10_F011 - Kiểm tra tính khớp tổng tiền");
        openCartWith(normal(2), deleteA(), deleteB());

        long summaryTotal = cartPage.getSummaryTotal();
        long calculatedTotal = cartPage.sumAllItemTotals();
        LoggerUtil.info("Summary Total: " + summaryTotal + " | Calculated Total: " + calculatedTotal);
        Assert.assertEquals(summaryTotal, calculatedTotal);
    }

    @Test(priority = 12)
    public void TC10_F012() {
        openCartWith(normal(1));

        cartPage.setQuantityBySku(SKU_NORMAL, "2");

        Assert.assertEquals(
                cartPage.getItemTotalBySku(SKU_NORMAL),
                cartPage.getUnitPriceBySku(SKU_NORMAL) * 2
        );
    }

    @Test(priority = 13)
    public void TC10_F013() {
        LoggerUtil.info("START TEST: TC10_F013 - Click '-' khi số lượng đang là 1");
        openCartWith(stockOne());

        LoggerUtil.info("Clicking Minus for SKU (qty=1): " + SKU_STOCK_1);
        cartPage.clickMinusWhenQtyIsOneBySku(SKU_STOCK_1);

        boolean isModalDisplayed = cartPage.isDeleteModalDisplayed();
        LoggerUtil.info("Result: Delete modal displayed=" + isModalDisplayed);
        Assert.assertTrue(isModalDisplayed);
        Assert.assertTrue(cartPage.hasCartItemBySku(SKU_STOCK_1));
    }

    @Test(priority = 14)
    public void TC10_F014() {
        LoggerUtil.info("START TEST: TC10_F014 - Xóa sản phẩm qua modal (Xác nhận)");
        openCartWith(normal(1), deleteA(), deleteB());

        int beforeItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count before: " + beforeItemCount);

        LoggerUtil.info("Deleting SKU: " + SKU_DELETE_A);
        cartPage.clickDeleteBySku(SKU_DELETE_A);
        cartPage.confirmDelete();

        int afterItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count after: " + afterItemCount);

        Assert.assertEquals(afterItemCount, beforeItemCount - 1);
        Assert.assertFalse(cartPage.hasCartItemBySku(SKU_DELETE_A));
        Assert.assertEquals(cartPage.getSummaryTotal(), cartPage.sumAllItemTotals());
    }

    @Test(priority = 15)
    public void TC10_F015() {
        LoggerUtil.info("START TEST: TC10_F015 - Xóa sản phẩm cuối cùng");
        openCartWith(normal(1));

        LoggerUtil.info("Deleting the only item: " + SKU_NORMAL);
        cartPage.clickDeleteBySku(SKU_NORMAL);
        cartPage.confirmDelete();

        boolean isEmpty = cartPage.isEmptyCartDisplayed();
        LoggerUtil.info("Result: Is cart empty=" + isEmpty);
        Assert.assertTrue(isEmpty);
    }

    @Test(priority = 16)
    public void TC10_F016() {
        LoggerUtil.info("START TEST: TC10_F016 - Xóa sản phẩm qua modal (Hủy)");
        openCartWith(normal(1), deleteB());

        LoggerUtil.info("Clicking delete then cancel for: " + SKU_DELETE_B);
        cartPage.clickDeleteBySku(SKU_DELETE_B);
        cartPage.cancelDelete();

        boolean hasItem = cartPage.hasCartItemBySku(SKU_DELETE_B);
        LoggerUtil.info("Result: Has item still=" + hasItem);
        Assert.assertTrue(hasItem);
    }

    @Test(priority = 17)
    public void TC10_F017() {
        LoggerUtil.info("START TEST: TC10_F017 - Đổi phân loại sản phẩm (Variant)");
        openCartWith(variant());

        LoggerUtil.info("Changing variant from " + SKU_VARIANT + " to " + SKU_VARIANT_CHANGED);
        cartPage.changeVariantBySku(SKU_VARIANT, SKU_VARIANT_CHANGED);

        boolean hasNew = cartPage.hasCartItemBySku(SKU_VARIANT_CHANGED);
        boolean hasOld = cartPage.hasCartItemBySku(SKU_VARIANT);
        LoggerUtil.info("Result: Has new=" + hasNew + " | Has old=" + hasOld);
        Assert.assertTrue(
                hasNew,
                "Variant mới phải xuất hiện trong giỏ sau khi đổi"
        );
        Assert.assertFalse(
                hasOld,
                "Variant cũ phải được thay thế"
        );
    }

    @Test(priority = 18)
    public void TC10_F018() {
        LoggerUtil.info("START TEST: TC10_F018 - Đổi phân loại trùng với sản phẩm đã có (Gộp dòng)");
        openCartWith(variant(), variantChanged());

        int beforeItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count before: " + beforeItemCount);

        LoggerUtil.info("Changing variant " + SKU_VARIANT + " to " + SKU_VARIANT_CHANGED);
        cartPage.changeVariantBySku(SKU_VARIANT, SKU_VARIANT_CHANGED);

        int afterItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count after: " + afterItemCount);

        Assert.assertEquals(
                afterItemCount,
                beforeItemCount - 1,
                "Đổi sang phân loại đã tồn tại thì phải gộp dòng"
        );
        Assert.assertFalse(
                cartPage.hasCartItemBySku(SKU_VARIANT),
                "SKU cũ phải biến mất sau khi gộp"
        );
        Assert.assertTrue(
                cartPage.hasCartItemBySku(SKU_VARIANT_CHANGED),
                "SKU variant đã tồn tại phải còn lại sau khi gộp"
        );
        
        int finalQty = cartPage.getQuantityBySku(SKU_VARIANT_CHANGED);
        LoggerUtil.info("Final quantity for merged SKU: " + finalQty);
        Assert.assertEquals(
                finalQty,
                2,
                "Số lượng sau khi gộp phải được cộng dồn"
        );
        Assert.assertEquals(
                cartPage.getSummaryTotal(),
                cartPage.sumAllItemTotals(),
                "Tổng tiền sau khi gộp variant chưa đúng"
        );
    }

    @Test(priority = 19)
    public void TC10_F019() {
        LoggerUtil.info("START TEST: TC10_F019 - Sản phẩm hết hàng (Out of Stock)");
        openCartWith(outOfStock());

        boolean hasTag = cartPage.hasOutOfStockTag();
        boolean isDisabled = cartPage.isCheckoutDisabled();
        LoggerUtil.info("Result: Has OOS tag=" + hasTag + " | Checkout disabled=" + isDisabled);
        Assert.assertTrue(hasTag);
        Assert.assertTrue(isDisabled);
    }

    @Test(priority = 20)
    public void TC10_F020() {
        LoggerUtil.info("START TEST: TC10_F020 - Chặn đặt hàng khi giỏ chỉ có SP hết hàng");
        openCartWith(outOfStock());

        boolean isDisabled = cartPage.isCheckoutDisabled();
        String url = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: Checkout disabled=" + isDisabled + " | URL=" + url);
        Assert.assertTrue(
                isDisabled,
                "Khi giỏ chỉ có sản phẩm hết hàng thì nút Tiến hành đặt hàng phải bị vô hiệu hóa"
        );
        Assert.assertTrue(
                url.contains("/gio-hang"),
                "Vẫn phải ở lại trang giỏ hàng"
        );
    }

    @Test(priority = 21)
    public void TC10_F021() {
        LoggerUtil.info("START TEST: TC10_F021 - Đặt hàng khi giỏ có SP hết hàng + SP còn hàng");
        openCartWith(outOfStock(), normal(1));

        LoggerUtil.info("Clicking Checkout...");
        cartPage.clickCheckout();

        boolean isAtCheckout = checkoutPage.isAtCheckoutPage();
        String url = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: Is at checkout=" + isAtCheckout + " | URL=" + url);
        Assert.assertTrue(
                isAtCheckout,
                "Không điều hướng sang trang đặt hàng khi giỏ có ít nhất 1 sản phẩm còn hàng"
        );

        Assert.assertFalse(
                url.contains("/gio-hang"),
                "Khi giỏ có sản phẩm còn hàng thì không được ở lại trang giỏ hàng"
        );
    }

    @Test(priority = 22)
    public void TC10_F022() {
        LoggerUtil.info("START TEST: TC10_F022 - Nút 'Tiếp tục mua sắm' (Giỏ rỗng)");
        openCartWith();

        LoggerUtil.info("Clicking Continue Shopping...");
        cartPage.clickContinueShopping();

        String url = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: URL=" + url);
        Assert.assertTrue(url.contains("/san-pham"));
    }

    @Test(priority = 23)
    public void TC10_F023() {
        LoggerUtil.info("START TEST: TC10_F023 - Link tên sản phẩm");
        openCartWith(normal(1));

        LoggerUtil.info("Clicking Product Name...");
        cartPage.clickProductName();

        String url = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: URL=" + url);
        Assert.assertTrue(url.contains("/san-pham/"));
    }

    @Test(priority = 24)
    public void TC10_F024() {
        LoggerUtil.info("START TEST: TC10_F024 - Sản phẩm gợi ý (Related Products)");
        openCartWith(normal(1));

        boolean hasRelated = cartPage.hasRelatedProduct();
        LoggerUtil.info("Result: Has related products=" + hasRelated);
        Assert.assertTrue(hasRelated);

        LoggerUtil.info("Clicking First Related Product...");
        cartPage.clickFirstRelatedProduct();

        String url = Constant.getDriver().getCurrentUrl();
        LoggerUtil.info("Result: URL=" + url);
        Assert.assertTrue(url.contains("/san-pham/"));
    }

    @Test(priority = 25)
    public void TC10_F025() {
        LoggerUtil.info("START TEST: TC10_F025 - Lỗi API khi cập nhật số lượng");
        openCartWith(normal(1));

        int beforeQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity before: " + beforeQty);

        LoggerUtil.info("Simulating API error for quantity update...");
        cartPage.simulateApiErrorForQuantity();
        cartPage.clickPlusBySku(SKU_NORMAL);

        assertToastContains("Lỗi kết nối. Vui lòng thử lại!");
        
        int afterQty = cartPage.getQuantityBySku(SKU_NORMAL);
        LoggerUtil.info("Quantity after error: " + afterQty);
        Assert.assertEquals(
                afterQty,
                beforeQty,
                "Số lượng không được thay đổi khi API cập nhật số lượng lỗi"
        );
    }

    @Test(priority = 26)
    public void TC10_F026() {
        LoggerUtil.info("START TEST: TC10_F026 - Lỗi API khi đổi phân loại");
        openCartWith(variant());

        int beforeItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count before: " + beforeItemCount);

        LoggerUtil.info("Simulating API error for variant change...");
        cartPage.simulateApiErrorForVariant();
        cartPage.changeVariantBySku(SKU_VARIANT, SKU_VARIANT_CHANGED);

        assertToastContains("Lỗi kết nối khi đổi màu sắc!");
        
        int afterItemCount = cartPage.getItemCount();
        LoggerUtil.info("Item count after error: " + afterItemCount);
        Assert.assertEquals(
                afterItemCount,
                beforeItemCount,
                "Giỏ hàng không được thay đổi khi API đổi phân loại lỗi"
        );
    }

    @Test(priority = 27)
    public void TC10_UI001() {
        LoggerUtil.info("START TEST: TC10_UI001 - Kiểm tra giao diện giỏ hàng");
        openCartWith(normal(1));

        LoggerUtil.info("Checking Breadcrumb...");
        Assert.assertTrue(cartPage.isBreadcrumbDisplayed(), "Breadcrumb chưa hiển thị");
        String breadcrumb = cartPage.getBreadcrumbText();
        LoggerUtil.info("Breadcrumb: " + breadcrumb);
        Assert.assertTrue(breadcrumb.contains("Trang chủ") && breadcrumb.contains("Giỏ hàng"), "Breadcrumb sai nội dung");

        LoggerUtil.info("Checking Title...");
        String title = cartPage.getTitleText();
        LoggerUtil.info("Title: " + title);
        Assert.assertEquals(title.toUpperCase(), "GIỎ HÀNG", "Tiêu đề trang sai");
        Assert.assertTrue(cartPage.isTitleBold(), "Tiêu đề phải in đậm");
        Assert.assertTrue(cartPage.isTitleLeftAligned(), "Tiêu đề phải căn trái");

        LoggerUtil.info("Checking Product components...");
        Assert.assertTrue(cartPage.isProductImageDisplayed(), "Ảnh sản phẩm chưa hiển thị");
        Assert.assertTrue(cartPage.isProductNameDisplayed(), "Tên sản phẩm chưa hiển thị");
        Assert.assertTrue(cartPage.isVariantDropdownDisplayed(), "Dropdown màu chưa hiển thị");
        Assert.assertTrue(cartPage.isQuantityControlDisplayed(), "Bộ tăng giảm số lượng chưa hiển thị");
        Assert.assertTrue(cartPage.isPriceDisplayed(), "Giá/Thành tiền chưa hiển thị");
        Assert.assertTrue(cartPage.isPriceTextContainsCurrency(), "Giá phải có đơn vị tiền tệ (đ)");
        Assert.assertTrue(cartPage.isDeleteIconDisplayed(), "Icon xóa chưa hiển thị");

        LoggerUtil.info("Checking Summary components...");
        Assert.assertTrue(cartPage.isSummaryDisplayed(), "Khối tổng tiền chưa hiển thị");
        Assert.assertTrue(cartPage.isSupportTextDisplayed(), "Thông tin hỗ trợ chưa hiển thị");
        Assert.assertTrue(cartPage.isTotalAmountDisplayed(), "Tổng cộng chưa hiển thị");
        Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(), "Nút đặt hàng chưa hiển thị");
        Assert.assertTrue(cartPage.isCheckoutButtonBlackWithWhiteText(), "Style nút đặt hàng sai (phải nền đen chữ trắng)");

        LoggerUtil.info("Checking Toast behavior...");
        cartPage.clickPlusUntilReachStockBySku(SKU_NORMAL);
        assertToastContains("Số lượng vượt ngưỡng tồn kho");
        Assert.assertTrue(cartPage.isToastAutoHidden(), "Toast phải tự ẩn sau vài giây");

        LoggerUtil.info("Checking Empty Cart UI...");
        openCartWith();
        Assert.assertTrue(cartPage.isEmptyCartDisplayed(), "UI giỏ hàng trống chưa hiển thị");
        Assert.assertTrue(cartPage.getEmptyCartText().contains("Giỏ hàng của bạn đang trống"), "Nội dung thông báo giỏ trống sai");
        Assert.assertTrue(cartPage.isContinueShoppingDisplayed(), "Nút Tiếp tục mua sắm chưa hiển thị");
    }
}




