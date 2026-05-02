package common;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void info(String message) {
        logger.info(message);
        logToReports("INFO", message);
    }

    public static void warn(String message) {
        logger.warn(message);
        logToReports("WARN", message);
    }

    public static void error(String message) {
        logger.error(message);
        logToReports("ERROR", message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    private static void logToReports(String level, String message) {
        // Log to TestNG Reporter (emailable-report.html & index.html)
        try {
            Reporter.log("[" + level + "] " + message + "<br>");
        } catch (Exception ignored) {}

        // Log to ExtentReports
        try {
            if (ExtentListener.getTest() != null) {
                switch (level) {
                    case "INFO":
                        ExtentListener.getTest().info(message);
                        break;
                    case "WARN":
                        ExtentListener.getTest().warning(message);
                        break;
                    case "ERROR":
                        ExtentListener.getTest().fail(message);
                        break;
                }
            }
        } catch (Exception ignored) {}

        // Log to Allure
        try {
            Allure.step("[" + level + "] " + message);
        } catch (Exception ignored) {}
    }
}




