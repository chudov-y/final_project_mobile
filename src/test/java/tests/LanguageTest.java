package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class LanguageTest {
    @Owner("chudov-y")
    @Test
    @DisplayName("Add language")
    void addLanguageTest() {
        back();
        step("Type search", () ->
                $(AppiumBy.accessibilityId("Search Wikipedia")).click());
        step("Add new language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_lang_button")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).findBy(text("ADD LANGUAGE")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name")).findBy(text("Español")).click();
        });
        step("Verify added language", () ->
                $$(AppiumBy.className("android.widget.TextView")).findBy(text("Español")).shouldBe(visible));
    }
}
