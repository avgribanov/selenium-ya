package io.avgribanov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;

public interface AuthPage extends WebPage {

    @FindBy("//input[@type='text' and @id='index_email']")
    HtmlElement emailInput();

    @FindBy("//input[@type='password' and @id='index_pass']")
    HtmlElement passwordInput();

    @FindBy("//button[@id='index_login_button']")
    HtmlElement buttonLogin();

}

