package ru.translator.eng_rus.Service.TranslateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.translator.eng_rus.Component.DictionaryForMap;

@Slf4j
@Service
public class TranslateService {
    private final DictionaryForMap dictionary;

    public TranslateService(DictionaryForMap dictionary) {
        this.dictionary = dictionary;
    }

    public String get(String str) {
        StringBuilder newString = new StringBuilder();
        char[] list = str.toLowerCase().trim().toCharArray();
        for (int i = 0; i < list.length; i++) {
            list[i] = dictionary.getEquivalent(list[i]);
            newString.append(list[i]);
        }
        log.info("Translated full word {}", str);
        return newString.toString();
    }
}
