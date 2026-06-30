package ru.translator.eng_rus.Convertor.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConvertorDTO(

        UUID id,

        LocalDateTime time,

        @NotBlank(message = "Text cannot be empty")
        String wrongString,

        String rightString,

        String username

) {
}
