package ru.translator.eng_rus.User.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.translator.eng_rus.Role.Entity.Role;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;

    private String nickname;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Role role;

    private String roleName;
}
