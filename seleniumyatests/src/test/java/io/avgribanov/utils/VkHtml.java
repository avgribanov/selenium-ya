package io.avgribanov.utils;

import io.avgribanov.elements.AuthPage;
import io.avgribanov.elements.DialogPage;
import io.avgribanov.elements.PopupDialog;
import io.avgribanov.elements.UserPage;
import io.qameta.htmlelements.WebPageFactory;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;



import static org.assertj.core.api.Assertions.assertThat;

public class VkHtml {

    private WebDriver driver;

    LocalTime timenow = LocalTime.now();
    LocalDate datenow = LocalDate.now();

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testWebYa() {

        VkConfigs cfg = ConfigFactory.create(VkConfigs.class);

        WebPageFactory factory = new WebPageFactory();
        AuthPage auth = factory.get(driver, AuthPage.class);

        driver.get("https://vk.com/");
        checkTitle(driver, "Добро пожаловать | ВКонтакте");
        /* Ввод логина и пароля */
        auth.emailInput().sendKeys(cfg.email());
        auth.passwordInput().sendKeys(cfg.password());
        auth.buttonLogin().click();
        /* Проверка на открытие страницы Новости */
        checkTitle(driver, "Новости");
        /* Переход на страницу конекретного пользователя */
        driver.get("https://vk.com/id51030271");
        checkTitle(driver, "Дмитрий Кузнецов");
        /* Открытие попапа для ввода сообщения */
        UserPage userPage = factory.get(driver, UserPage.class);
        userPage.buttonDialog().click();
        /* Проверка на открытие попапа для ввода и отправки */
        checkWebElement(driver, "//*[@class='box_layout']");
        /* Проверка на появление ссылки для перехода к диалогу*/
        checkWebElement(driver, "//*[@class='mail_box_header_link']");
        /* Переход к диалогу */
        PopupDialog popupDialog = factory.get(driver, PopupDialog.class);
        popupDialog.nextDialog().click();
        /* Проверка на переход страницы диалогов */
        checkTitle(driver, "Диалоги");
        /* Проверка на переход к определенному юзеру */
        checkWebElement(driver, "//*[@class='im-page--title-main' and @title='Дмитрий Кузнецов']");

        /* Ввод текста */
        DialogPage dialogPage = factory.get(driver, DialogPage.class);
        dialogPage.inputMessageDialog().clear();
        dialogPage.inputMessageDialog().sendKeys(datenow.toString() + " " + timenow.toString());

        /* Поиск кнопки */
        dialogPage.buttonSendMessage().click();

        /* Проверка на отправку */
        checkWebElement(driver, "//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='" + datenow + " " + timenow + "']");
        assertThat(driver.findElement(By.xpath("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='" + datenow + " " + timenow + "']")).isDisplayed()).isTrue();

    }

    private void checkTitle(WebDriver driver, final String text) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith(text);
            }
        });
    }

    private void checkWebElement(WebDriver driver, final String xpathKey) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath(xpathKey)).isDisplayed();
            }
        });
    }
}
