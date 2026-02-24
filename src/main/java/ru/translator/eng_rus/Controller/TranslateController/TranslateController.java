package ru.translator.eng_rus.Controller.TranslateController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.Service.TranslateService.TranslateService;

@Slf4j
@Controller
@RequestMapping("/translate")
public class TranslateController {
    private final TranslateService translateService;

    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/")
    public String showPage(Model model) {
        if (!model.containsAttribute("pojo")) {
            model.addAttribute("pojo", new WrongStringPOJO());
        }
        log.info("Initialized absent POJO object");
        return "translate-page";
    }

    @PostMapping("/convert")
    public String save(@ModelAttribute("pojo") WrongStringPOJO pojo,
                       RedirectAttributes redirectAttributes) {
        log.info("Got necessary wrong string {}", pojo.getWrongString());
        String result = translateService.get(pojo.getWrongString());
        pojo.setRightString(result);
        redirectAttributes.addFlashAttribute("pojo", pojo);
        return "redirect:/translate/";
    }
}
