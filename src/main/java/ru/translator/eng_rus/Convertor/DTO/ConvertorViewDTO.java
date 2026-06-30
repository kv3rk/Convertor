package ru.translator.eng_rus.Convertor.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ConvertorViewDTO(

        UUID id,

        String time,

        @NotBlank(message = "Propagated text cannot be empty")
        String wrongString,

        @NotBlank(message = "Requested text cannot be empty")
        String rightString
) {
}
