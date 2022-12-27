package LearningTestNG;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.security.SecureRandom;

public class Utility {
    public static void captureScreenshot (WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            SecureRandom random = new SecureRandom();
            Integer randomValue = random.nextInt();
            String fileName = Integer.toString(randomValue, 5) + ".png";
            FileUtils.copyFile(source, new File("./Screenshots/Screenshot-" + fileName));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
