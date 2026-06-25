package ru.translator.eng_rus.Authentification.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/translate")
public class AuthentificationController {

    @GetMapping("/login")
    public String loginPage() {

        log.info("Entered [/translate/login] endpoint");

        return "/auth/login-page";
    }
}
