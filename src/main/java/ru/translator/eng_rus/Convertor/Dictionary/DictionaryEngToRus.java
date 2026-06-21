package ru.translator.eng_rus.Convertor.Dictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DictionaryEngToRus {
    private final Map<Character, Character> dictionaryList = new HashMap<>();

    public DictionaryEngToRus() {
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
        dictionaryList.put('`', 'ё');
        dictionaryList.put('~', 'Ё');
        dictionaryList.put('<', 'Б');
        dictionaryList.put('>', 'Ю');
        dictionaryList.put(':', 'Ж');
        dictionaryList.put('\"', 'Э');
        dictionaryList.put('{', 'х');
        dictionaryList.put('}', 'ъ');
        dictionaryList.put('/', '.');
        dictionaryList.put('?', ',');
        dictionaryList.put('&', '?');
        dictionaryList.put('@', '\"');
        dictionaryList.put('#', '№');
        dictionaryList.put('$', ';');
        dictionaryList.put('^', ':');
        dictionaryList.put('|', '/');
        log.info("Initialized dictionary");
    }

    public Character getEquivalent(Character letter) {

        if (Character.isLetter(letter)) {
            if (Character.isLowerCase(letter)) {
                log.info("Swapped the letter {} to {}", letter, dictionaryList.get(
                        letter));
                return dictionaryList.get(letter);
            } else {
                letter = Character.toLowerCase(letter);
                log.info("Swapped the letter {} to {}", letter, Character.toUpperCase
                        (dictionaryList.get(letter)));
                return Character.toUpperCase(dictionaryList.get(letter));
            }
        } else {
            log.info("Swapped the letter {} to {}", letter, dictionaryList.getOrDefault(
                    letter, letter));
            return dictionaryList.getOrDefault(letter, letter);
        }
    }

}
