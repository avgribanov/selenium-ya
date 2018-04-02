package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface NewsPage extends WebPage {

    @FindBy("//div[@id='submit_post_box']")
    HtmlElement blockSendNewPost();

}