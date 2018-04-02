package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface PopupDialog extends WebPage {

    @FindBy("//*[@class='mail_box_header_link']")
    HtmlElement nextDialog();

}