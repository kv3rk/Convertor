package ru.translator.eng_rus.Service.TranslateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;
import ru.translator.eng_rus.DTO.DTOTranslate;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.Scopes.IdPerRequest;
import ru.translator.eng_rus.util.TranslateWord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TranslateService {
    private final TranslateWord translateWord;
    private final ObjectFactory<IdPerRequest> id;
    private final DTOTranslate dtoTranslate;

    public TranslateService(TranslateWord translateWord,
                            DTOTranslate dtoTranslate,
                            ObjectFactory<IdPerRequest> id) {
        this.translateWord = translateWord;
        this.dtoTranslate = dtoTranslate;
        this.id = id;
    }

    public String get(String str, String uuid) {
        IdPerRequest newId = id.getObject();
        log.info("Got value {} for work", str);
        String newString = translateWord.translateWord(str);
        String totalId = uuid + "#" + newId.getId();
        log.info("Registered totalId [{}]", totalId);
        dtoTranslate.save(new WrongStringPOJO(totalId, LocalDateTime.now(), str,
                newString));
        return newString;
    }

    public List<WrongStringPOJO> getAll() {
        //log.info("Handed over the list of all pojos to model");
        return dtoTranslate.findAll();
    }
    public List<WrongStringPOJO> getRecentHistory(){
        //log.info("Handed over the list of recent 2 pojos to model");
        return dtoTranslate.findRecent();
    }
}
