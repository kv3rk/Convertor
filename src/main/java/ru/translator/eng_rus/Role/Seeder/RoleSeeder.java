package ru.translator.eng_rus.Role.Seeder;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.translator.eng_rus.Role.Entity.Role;
import ru.translator.eng_rus.Role.Enum.RoleEnum;
import ru.translator.eng_rus.Role.Repository.RoleRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        this.loadRoles();
    }

    private void loadRoles() {

        RoleEnum[] roleNames = new RoleEnum[]{

                RoleEnum.ADMIN, RoleEnum.USER
        };

        Map<RoleEnum, String> roleDescription = Map.of(

                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.USER, "User role"

        );

        Arrays.stream(roleNames).forEach((roleName) -> {

            Optional<Role> optionalRole = Optional.ofNullable(
                    roleRepository.findByRole(roleName)
            );
            optionalRole.ifPresentOrElse(System.out::println, () -> {

                Role roleToCreate = new Role();
                roleToCreate.setRole(roleName);
                roleToCreate.setDescription(roleDescription.get(roleName));
                roleRepository.save(roleToCreate);

            });

        });

    }
}
