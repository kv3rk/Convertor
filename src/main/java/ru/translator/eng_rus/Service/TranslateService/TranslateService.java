package ru.translator.eng_rus.Service.TranslateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.translator.eng_rus.Component.DictionaryForMap;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.util.TranslateWord;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class TranslateService {
    private final TranslateWord translateWord;
    private final AtomicInteger id = new AtomicInteger(1);

    public TranslateService(TranslateWord translateWord) {
        this.translateWord = translateWord;
    }

    public String get(String str) {
        log.info("Got value {} for work", str);
        String newString = translateWord.translateWord(str);
        new WrongStringPOJO(id.getAndIncrement(), LocalDateTime.now(),
                str, newString);
        return newString;
    }
}
