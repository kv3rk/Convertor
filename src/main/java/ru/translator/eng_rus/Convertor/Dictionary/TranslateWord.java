package ru.translator.eng_rus.Convertor.Dictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TranslateWord {

    private final DictionaryEngToRus dictionary;

    public TranslateWord(DictionaryEngToRus dictionary) {
        this.dictionary = dictionary;
    }

    public String translateWord(String str) {
        StringBuilder newString = new StringBuilder();
        char[] list = str.trim().toCharArray();
        for (int i = 0; i < list.length; i++) {
            list[i] = dictionary.getEquivalent(list[i]);
            newString.append(list[i]);
        }
        log.info("Translated full line {} to {}", str, newString);
        return newString.toString();
    }
}
