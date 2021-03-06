package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface UserPage extends WebPage {

    @FindBy("//button[@class='flat_button profile_btn_cut_left']")
    HtmlElement buttonDialog();

    @FindBy("//h2[@class='page_name' and text()='Дмитрий  Кузнецов']")
    //@FindBy("//h2[contains(@class, 'page_name' and text() = 'Дмитрий  Кузнецов')]")
    HtmlElement nameUser();

}


