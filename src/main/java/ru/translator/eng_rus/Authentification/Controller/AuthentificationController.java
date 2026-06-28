package ru.translator.eng_rus.Authentification.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.translator.eng_rus.Authentification.Service.RegistrationService;
import ru.translator.eng_rus.User.DTO.RegisterUserDTO;

@Slf4j
@Controller
@RequestMapping("/translate")
public class AuthentificationController {

    private final RegistrationService registrationService;

    public AuthentificationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        log.info("Entered [/translate/login] endpoint");
        return "auth/login-page";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerUserDTO", new RegisterUserDTO("", ""));
        return "auth/register-page";
    }

    @PostMapping("/getCredentials")
    public String getUserCredentials(@ModelAttribute @Valid RegisterUserDTO registerUserDTO,
                                     BindingResult bindingResult,
                                     Model model) {

        log.info("Entered [/translate/getCredentials] endpoint");

        if (bindingResult.hasErrors()) {



            return "auth/register-page";
        }

        boolean registered = registrationService.registerUser(registerUserDTO);

        if (!registered) {
            model.addAttribute("registrationError", "User with this nickname already exists");
            return "auth/register-page";
        }

        return "redirect:/translate/login";
    }
}