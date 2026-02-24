package ru.translator.eng_rus.Controller.TranslateController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import ru.translator.eng_rus.Service.TranslateService.TranslateService;

@Slf4j
@Controller
@RequestMapping("/translate")
public class TranslateController {
    private final TranslateService translateService;

    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/{str}")
    public String get(@PathVariable String str, Model model) {
        log.info("Got value {}", str);
        model.addAttribute("wrongString", str);
        model.addAttribute("rightString", translateService.get(str));
        return "translate-page";
    }
}
