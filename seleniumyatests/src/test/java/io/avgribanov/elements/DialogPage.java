package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface DialogPage extends WebPage {

    @FindBy("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']")
    HtmlElement buttonSendMessage();

    @FindBy("//*[@class='im_editable im-chat-input--text _im_text']")
    HtmlElement inputMessageDialog();

}