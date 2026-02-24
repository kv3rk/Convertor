package ru.translator.eng_rus.Controller.TranslateController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import ru.translator.eng_rus.Service.TranslateService.TranslateService;

@Slf4j
@RestController
@RequestMapping("/translate")
public class TranslateController {
    private final TranslateService translateService;

    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/{str}")
    public String get(@PathVariable String str) {
        log.info("Got value {}", str);
        return translateService.get(str);
    }
}
