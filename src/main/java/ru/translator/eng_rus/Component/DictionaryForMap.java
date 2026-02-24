package ru.translator.eng_rus.Component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DictionaryForMap {
    private final Map<Character, Character> dictionaryList = new HashMap<>();

    public DictionaryForMap() {
        dictionaryList.put('q', 'й');
        dictionaryList.put('w', 'ц');
        dictionaryList.put('e', 'у');
        dictionaryList.put('r', 'к');
        dictionaryList.put('t', 'е');
        dictionaryList.put('y', 'н');
        dictionaryList.put('u', 'г');
        dictionaryList.put('i', 'ш');
        dictionaryList.put('o', 'щ');
        dictionaryList.put('p', 'з');
        dictionaryList.put('[', 'х');
        dictionaryList.put(']', 'ъ');
        dictionaryList.put('a', 'ф');
        dictionaryList.put('s', 'ы');
        dictionaryList.put('d', 'в');
        dictionaryList.put('f', 'а');
        dictionaryList.put('g', 'п');
        dictionaryList.put('h', 'р');
        dictionaryList.put('j', 'о');
        dictionaryList.put('k', 'л');
        dictionaryList.put('l', 'д');
        dictionaryList.put(';', 'ж');
        dictionaryList.put('\'', 'э');
        dictionaryList.put('z', 'я');
        dictionaryList.put('x', 'ч');
        dictionaryList.put('c', 'с');
        dictionaryList.put('v', 'м');
        dictionaryList.put('b', 'и');
        dictionaryList.put('n', 'т');
        dictionaryList.put('m', 'ь');
        dictionaryList.put(',', 'б');
        dictionaryList.put('.', 'ю');
        log.info("Initialized dictionary");
    }

    public Character getEquivalent(Character letter) {
        log.info("Swapped the letter {}", letter);
        return dictionaryList.get(letter);
    }

}
