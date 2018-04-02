package io.avgribanov.utils;

import io.avgribanov.elements.AuthPage;
import io.avgribanov.elements.DialogsPage;
import io.avgribanov.elements.NewsPage;
import io.avgribanov.elements.PopupDialog;
import io.avgribanov.elements.UserPage;
import io.qameta.htmlelements.WebPageFactory;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.LocalTime;

import static io.qameta.htmlelements.matcher.DisplayedMatcher.displayed;

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
        AuthPage authPage = factory.get(driver, AuthPage.class);
        NewsPage newsPage = factory.get(driver, NewsPage.class);
        UserPage userPage = factory.get(driver, UserPage.class);
        PopupDialog popupDialog = factory.get(driver, PopupDialog.class);
        DialogsPage dialogPage = factory.get(driver, DialogsPage.class);

        /* Переход на сайт */
        driver.get("https://vk.com/");

        /* Проверка на отображение блока логина */
        authPage.blockAuth().isDisplayed();

        /* Ввод логина и пароля */
        authPage.emailInput().sendKeys(cfg.email());
        authPage.passwordInput().sendKeys(cfg.password());
        authPage.buttonLogin().click();

        /* Проверка на отображение ввода поста на странице Новости */
        newsPage.blockSendNewPost().isDisplayed();

        /* Переход на страницу конекретного пользователя */
        driver.get("https://vk.com/id51030271");

        /* Проверка на конкретность пользователя */
        userPage.nameUser().isDisplayed();

        /* Открытие попапа для ввода сообщения */
        userPage.buttonDialog().click();

        /* Проверка на открытие попапа для ввода и отправки */
        popupDialog.blockPopup().isDisplayed();

        /* Проверка на появление ссылки для перехода к диалогу и переход к нему */
        popupDialog.urlDialog().isDisplayed();
        popupDialog.nextDialog().click();

        /* Проверка на переход к диалогу к определенному юзеру */
        dialogPage.userDialog().isDisplayed();

        /* Поиск поля и Ввод текста */
        dialogPage.inputMessageDialog().isDisplayed();
        dialogPage.inputMessageDialog().clear();
        dialogPage.inputMessageDialog().sendKeys(datenow.toString() + " " + timenow.toString());

        /* Поиск кнопки и нажатие */
        dialogPage.buttonSendMessage().isDisplayed();
        dialogPage.buttonSendMessage().click();

        /* Проверка на отправку */
        dialogPage.messageNext(datenow.toString(), timenow.toString()).should(displayed());

    }

}
