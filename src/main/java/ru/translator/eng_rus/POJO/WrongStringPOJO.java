package ru.translator.eng_rus.POJO;

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
    private String wrongString;
    private String rightString;
}
