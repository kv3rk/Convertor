package ru.translator.eng_rus.User.DTO;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
        @NotBlank(message = "Username cant be blank")
        String nickname,
        @NotBlank(message = "Password cant be blank")
        String password

) {
}
