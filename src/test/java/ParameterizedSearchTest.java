import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedSearchTest {
    @ValueSource(strings = {"Просто. Нереально.", "Купить"})
    @ParameterizedTest(name = "Тест на поиск элемента на странице: {0}")
    void commonSearch(String value) {
        open("https://www.apple.com/ru");
        $(".icon.icon-after.icon-chevronright.ab-key-features[href='/ru/iphone-13-pro/key-features/']").click();
        $(".lift-transition-sticky-content").shouldBe(visible);
        $(".lift-transition-sticky-content").shouldHave(text(value));
    }

    @CsvSource(value = {
           "iPhone 13 Pro, Небесно-голубой",
            "iPhone 13 Pro Max, Серебристый"
     })
    @ParameterizedTest(name = "Тест на поиск элемента на странице: {0}")
    void commonSearchCsv(String value, String result) {
        open("https://www.apple.com/ru/shop/buy-iphone/iphone-13-pro");
        $(".rf-flagship-productselection-section").$(byText(value)).click();
        $(".rc-dimension-selector-group.row.form-selector-group-withgutters").shouldBe(visible);
        $(".rc-dimension-selector-group.row.form-selector-group-withgutters").shouldHave(text(result));
    }

    static Stream<Arguments> commonSearchProvider(){
        return Stream.of(
                Arguments.of("iPhone 13 Pro", "Небесно-голубой"),
                Arguments.of("iPhone 13 Pro Max", "Серебристый")
        );
    }
    @MethodSource("commonSearchProvider")
    @ParameterizedTest(name = "Тест на поиск элемента на странице: {0}, {1}")
    void commonSearchProvider(String value, String result) {
        open("https://www.apple.com/ru/shop/buy-iphone/iphone-13-pro");
        $(".rf-flagship-productselection-section").$(byText(value)).click();
        $(".rc-dimension-selector-group.row.form-selector-group-withgutters").shouldBe(visible);
        $(".rc-dimension-selector-group.row.form-selector-group-withgutters").shouldHave(text(result));
    }
}
