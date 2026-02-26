package ru.translator.eng_rus.POJO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrongStringPOJO {
    private String id;
    private LocalDateTime localDateTime;
    @NotBlank (message = "Nothing to convert")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{Punct}]*$",
            message = "Working with english text")
    private String wrongString;
    private String rightString;
}
