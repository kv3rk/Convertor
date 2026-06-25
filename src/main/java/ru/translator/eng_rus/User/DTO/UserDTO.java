package ru.translator.eng_rus.User.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.translator.eng_rus.Role.Entity.Role;

public record UserDTO(
        String nickname,
        String password,
        @JsonIgnore
        Role role,
        String roleName
) {
}
