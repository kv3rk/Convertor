package ru.translator.eng_rus.User.Builder;

import org.springframework.stereotype.Component;
import ru.translator.eng_rus.Role.Entity.Role;
import ru.translator.eng_rus.Role.Enum.RoleEnum;
import ru.translator.eng_rus.User.Entity.User;

import java.util.Optional;

@Component
public class UserBuilder {

    private String nickname = "anton";
    private String password = "123";
    private Role role = null;
    private String roleName = "none";


    public UserBuilder withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role;
        this.roleName = String.valueOf(
                Optional.ofNullable(role.getRole()).orElse(RoleEnum.USER)
        );
        return this;
    }

    public User build() {

        return User.builder()
                .nickname(this.nickname)
                .password(this.password)
                .role(this.role)
                .roleName(this.roleName)
                .build();
    }
}
