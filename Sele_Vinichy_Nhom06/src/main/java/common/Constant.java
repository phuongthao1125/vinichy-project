package common;

import org.openqa.selenium.WebDriver;

public class Constant {
    static {
        LoggerUtil.info("Load Constant class");
    }

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    
    public static void setDriver(WebDriver driver) {
        threadDriver.set(driver);
    }
    
    public static WebDriver getDriver() {
        return threadDriver.get();
    }
    public static final String URL = "http://localhost:8080/";
    public static final String USERNAME = "phuongthao@gmail.com";
    public static final String PASSWORD = "123456";
    public static final String EMPTY_USERNAME = "empty@gmail.com";
    public static final String EMPTY_PASSWORD = "123456";
    public static final String NO_ORDER_USERNAME = "khanhly@gmail.com";
    public static final String NO_ORDER_PASSWORD = "123456";
}

