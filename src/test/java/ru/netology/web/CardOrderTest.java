package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.Color;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

class CardOrderTest {
    @Test
    void shouldTest1() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldTest2() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("John Doe");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTest3() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+792700000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] [class=input__sub]").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTest4() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
//        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        String actual = $(".input_invalid").getCssValue("color");
        String expected = "#ff5c5c";
        org.junit.jupiter.api.Assertions.assertEquals(actual, Color.fromString(expected).asRgba());
    }
}

