package io.avgribanov.utils;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class SelYaTest {

    @Test
    public void testWebYa() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.yandex.ru");
        WebElement element = driver.findElement(By.id("text"));
        element.sendKeys("Купить машину на авто ру");
        element.submit();

        System.out.println("Заголовок: " + driver.getTitle());

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("купить машину на авто ру");
            }
        });

        assertThat(driver.getTitle().toLowerCase()).startsWith("купить машину на авто ру");
        System.out.println("Заголовок: " + driver.getTitle());
        driver.quit();

    }
}
