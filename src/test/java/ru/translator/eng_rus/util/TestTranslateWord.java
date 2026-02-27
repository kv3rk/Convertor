package ru.translator.eng_rus.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.translator.eng_rus.Component.DictionaryForMap;

public class TestTranslateWord {

    TranslateWord translateWord = new TranslateWord(new DictionaryForMap());

    @ParameterizedTest(name = "Input: {0} => Expected: {1}")
    @CsvSource({
            "ghbdtn, привет",
            "rfr ltkf&, как дела?",
            "GHBDTN, ПРИВЕТ",
            "123!@, 123!\"",
            "'1 1', '1 1'"
    })
    @DisplayName("Must convert various strings in correct way")
    void testTranslateWord(String input, String expected) {
        String result = translateWord.translateWord(input);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Must return an empty string in empty input")
    void testBlankInput() {
        Assertions.assertEquals("", translateWord.translateWord(""));
    }
}
