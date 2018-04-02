package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.HtmlElement;

public interface DialogsPage extends WebPage {

    @FindBy("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']")
    HtmlElement buttonSendMessage();

    @FindBy("//*[@class='im_editable im-chat-input--text _im_text']")
    HtmlElement inputMessageDialog();

    @FindBy("//*[@class='im-page--title-main' and @title='Дмитрий Кузнецов']")
    HtmlElement userDialog();

    @FindBy("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='{{ datenow }} {{ timenow }}']")
    HtmlElement messageNext(@Param("datenow") String datenow, @Param("timenow") String timenow);
}