package io.avgribanov.utils;

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
import java.time.LocalTime;



import static org.assertj.core.api.Assertions.assertThat;

public class Vkontakte {

    private WebDriver driver;

    LocalTime timenow = LocalTime.now();

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

        driver.get("https://vk.com/");
        checkTitle(driver, "Добро пожаловать | ВКонтакте");

        WebElement email = driver.findElement(By.xpath("//input[@type='text' and @id='index_email']"));
        email.sendKeys(cfg.email());
        WebElement password = driver.findElement(By.xpath("//input[@type='password' and @id='index_pass']"));
        password.sendKeys(cfg.password());
        WebElement buttonLogin = driver.findElement(By.xpath("//*[@class='index_login_button flat_button button_big_text']"));
        buttonLogin.click();
        checkTitle(driver, "Новости");
        //assertThat(driver.getTitle()).startsWith("Новости");

        driver.get("https://vk.com/id51030271");
        checkTitle(driver, "Дмитрий Кузнецов");
        //assertThat(driver.getTitle()).startsWith("Артем Ерошенко");

        /* Открытие попапа для ввода сообщения */
        WebElement buttonSendMessage = driver.findElement(By.xpath("//*[@class='flat_button profile_btn_cut_left']"));
        buttonSendMessage.click();

        /* Проверка на открытие попапа для ввода и отправки */
        checkWebElement(driver, "//*[@class='box_layout']");
        /* Проверка на появление ссылки для перехода к диалогу и переход */
        checkWebElement(driver, "//*[@class='mail_box_header_link']");
        WebElement buttonDialog = driver.findElement(By.xpath("//*[@class='mail_box_header_link']"));
        buttonDialog.click();
        /* Проверка на переход к определенному юзеру */
        checkWebElement(driver, "//*[@class='im-page--title-main' and @title='Дмитрий Кузнецов']");
        WebElement inputMessageDialog = driver.findElement(By.xpath("//*[@class='im_editable im-chat-input--text _im_text']"));
        checkTitle(driver, "Диалоги");
        /* Ввод даты */
        inputMessageDialog.clear();
        inputMessageDialog.sendKeys(timenow.toString());
        /* Поиск кнопки */
        WebElement sendButton = driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']"));
        sendButton.click();
        /* Проверка на отправку */
        checkWebElement(driver, "//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='" + timenow + "']");
        assertThat(driver.findElement(By.xpath("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='" + timenow + "']")).isDisplayed()).isTrue();

        /* Ввод и отправка сообщения */
        //WebElement inputMessage = driver.findElement(By.xpath("//*[@id='mail_box_editable']"));
        //checkPopup(driver, "//*[@id='mail_box_editable']");
        //inputMessage.sendKeys("Привет");
        //WebElement buttonSend = driver.findElement(By.xpath("//*[@class='flat_button fl_r mail_box_send_btn']"));
        //buttonSend.click();

        /* Проверка на уведомление об успешной отправке сообщения */
        //checkPopup(driver, "//*[@class='top_result_baloon']");
        //assertThat(driver.findElement(By.xpath("//*[@class='top_result_baloon']")).isDisplayed()).isTrue();
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
