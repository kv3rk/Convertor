package ru.translator.eng_rus.Convertor.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.translator.eng_rus.Convertor.DTO.ConvertorDTO;
import ru.translator.eng_rus.Convertor.Service.ConvertorService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/translate")
public class ConvertorController {
    private final ConvertorService convertorService;

    public ConvertorController(ConvertorService convertorService) {
        this.convertorService = convertorService;
    }

    @GetMapping("/main")
    public String showPage(Model model) {

        log.info("Entered [/translate/main] endpoint");

        model.addAttribute("recentList", convertorService.getRecentHistory());

        return "translate-page";
    }

    @GetMapping("/get/recent-history")
    @ResponseBody
    public List<ConvertorDTO> getRecentHistory() {

        log.info("Entered [/translate/get/recent-history] endpoint");

        return convertorService.getRecentHistory();

    }

    @GetMapping("/get/full-history")
    @ResponseBody
    public List<ConvertorDTO> getFullHistory() {

        log.info("Entered [/translate/get/full-history] endpoint");

        return convertorService.getAll();

    }

    @PostMapping("/convert")
    @ResponseBody
    public ConvertorDTO save(@Valid @RequestBody ConvertorDTO convertorDTO) {

        log.info("Entered [/translate/convert] endpoint");

        return convertorService.save(convertorDTO);
    }

    @GetMapping("/history")
    public String showHistory(Model model) {

        log.info("Entered [/translate/history] endpoint");

        model.addAttribute("full_history", convertorService.getAll());

        return "history";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteAll() {
        log.info("Entered [/translate/delete] endpoint");

        convertorService.removeAll();
    }

    @DeleteMapping("/deleteNote")
    @ResponseBody
    public void delete(@Valid @RequestBody ConvertorDTO convertorDTO) {

        log.info("Entered [/translate/deleteNote] endpoint");

        convertorService.removeNote(convertorDTO.id());
    }

}
