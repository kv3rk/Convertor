package ru.translator.eng_rus.Authentification.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.translator.eng_rus.Role.Enum.RoleEnum;
import ru.translator.eng_rus.Role.Repository.RoleRepository;
import ru.translator.eng_rus.User.Builder.UserBuilder;
import ru.translator.eng_rus.User.DTO.RegisterUserDTO;
import ru.translator.eng_rus.User.Entity.User;
import ru.translator.eng_rus.User.Repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean registerUser(RegisterUserDTO registerUserDTO) {

        Optional<User> optionalUser = Optional.ofNullable(
                userRepository.findByNickname(registerUserDTO.nickname())
        );

        if (optionalUser.isPresent()) {
            log.info("Registration failed, user [{}] already exists", registerUserDTO.nickname());
            return false;
        }

        User user = new UserBuilder()
                .withNickname(registerUserDTO.nickname())
                .withPassword(passwordEncoder.encode(registerUserDTO.password()))
                .withRole(roleRepository.findByRole(RoleEnum.USER))
                .build();

        userRepository.save(user);

        log.info("Created user with credentials [{}], [{}]",
                user.getUsername(), user.getRoleName());

        return true;
    }
}
