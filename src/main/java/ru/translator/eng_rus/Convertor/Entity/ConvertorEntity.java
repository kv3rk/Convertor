package ru.translator.eng_rus.Convertor.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "convertor_engtorus")
public class ConvertorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime time = LocalDateTime.now(
            ZoneId.of("Europe/Moscow")
    );

    private String wrongString;
    private String rightString;

}
