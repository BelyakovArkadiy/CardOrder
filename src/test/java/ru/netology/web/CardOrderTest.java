package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

class CardOrderTest {

@BeforeEach
public void setUp() {
        open("http://localhost:9999");
        }


    @Test
    void shouldTestAllFields() {
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").
                shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestNameInLatin() {
        $("[data-test-id=name] input").setValue("John Doe");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestPhoneIsNotValid() {
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+7927000000555");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestWithoutCheckbox() {
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").
                shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldTestWithoutName() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestWithoutPhone() {
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").
                shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldTestAllFieldsButOnlyName() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").
                shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNameInRussianWithNumbers() {
        $("[data-test-id=name] input").setValue("Василь1 Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestNameInRussianWithSpecialCharacters() {
        $("[data-test-id=name] input").setValue("Василь%#! Алибабаевич");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestPhoneIsNotValid2() {
        $("[data-test-id=name] input").setValue("Василий Алибабаевич");
        $("[data-test-id=phone] input").setValue("+###927000%####!!!!");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

