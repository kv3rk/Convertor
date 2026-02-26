package ru.translator.eng_rus.Controller.TranslateController;

import com.sun.net.httpserver.HttpServer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.Scopes.UUIDPerSession;
import ru.translator.eng_rus.Service.TranslateService.TranslateService;

@Slf4j
@Controller
@RequestMapping("/translate")
public class TranslateController {
    private final TranslateService translateService;
    private final UUIDPerSession uuidPerSession;

    public TranslateController(TranslateService translateService,
                               UUIDPerSession uuidPerSession) {
        this.translateService = translateService;
        this.uuidPerSession = uuidPerSession;
    }

    @GetMapping("/")
    public String showPage(Model model) {
        log.info("Transfer to page translate-page.html");
        if (!model.containsAttribute("pojo")) {
            model.addAttribute("pojo", new WrongStringPOJO());
        }
        model.addAttribute("recentList", translateService
                .getRecentHistory());
        log.info("Initialized absent POJO object");
        return "translate-page";
    }

    @PostMapping("/convert")
    public String save(@Valid @ModelAttribute("pojo") WrongStringPOJO pojo, BindingResult
            bindingResult, Model model, RedirectAttributes redirectAttributes) {
        log.info("Redirected to main - translate-page.html");

        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("recentList", translateService
                    .getRecentHistory());
            return "translate-page";
        }
        log.info("Got necessary wrong string {}", pojo.getWrongString());
        String result = translateService.get(pojo.getWrongString(),
                uuidPerSession.getSessionId());
        pojo.setRightString(result);
        redirectAttributes.addFlashAttribute("pojo", pojo);
        return "redirect:/translate/";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        log.info("Transfer to page history.html");
        model.addAttribute("allList", translateService.getAll());
        return "history";
    }

    @GetMapping("/delete")
    public String deleteAll() {
        log.info("Redirected to sub - history.html");
        translateService.removeAll();
        return "redirect:/translate/history";
    }

    @GetMapping("/deleteNote/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        log.info("Redirected to sub - history.html");
        translateService.removeNote(id);
        return "redirect:/translate/history";
    }

}
