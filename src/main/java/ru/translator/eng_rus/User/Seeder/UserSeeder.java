package ru.translator.eng_rus.User.Seeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.translator.eng_rus.Role.Enum.RoleEnum;
import ru.translator.eng_rus.Role.Repository.RoleRepository;
import ru.translator.eng_rus.User.Builder.UserBuilder;
import ru.translator.eng_rus.User.DTO.UserDTO;
import ru.translator.eng_rus.User.Entity.User;
import ru.translator.eng_rus.User.Repository.UserRepository;
import ru.translator.eng_rus.common.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserBuilder userBuilder;

    public UserSeeder(UserRepository userRepository,
                      RoleRepository roleRepository,
                      UserMapper userMapper,
                      UserBuilder userBuilder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.userBuilder = userBuilder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        this.loadUsers();
    }

    private void loadUsers() {

        List<UserDTO> userDTOList = List.of(

                userMapper.convertorEntityToDTO(
                        userBuilder.withRole(roleRepository.findByRole(RoleEnum.USER)).build()
                ),
                userMapper.convertorEntityToDTO(
                        userBuilder.withRole(roleRepository.findByRole(RoleEnum.USER))
                                .withNickname("Petya").build()
                ),
                userMapper.convertorEntityToDTO(
                        userBuilder.withRole(roleRepository.findByRole(RoleEnum.ADMIN))
                                .withNickname("Masha").build()
                )
        );

        userDTOList.forEach((x) -> {

            Optional<User> optionalUser = Optional.ofNullable(
                    userRepository.findByNickname(x.nickname())
            );

            optionalUser.ifPresentOrElse((u) -> {

                        log.info("[{} user with password {} and role {}]",
                                u.getNickname(), u.getPassword(), u.getRoleName());

                    }, () -> {

                        User user = new UserBuilder()
                                .withNickname(x.nickname())
                                .withPassword(x.password())
                                .withRole(x.role())
                                .build();

                        userRepository.save(user);

                    }

            );

        });

    }
}
