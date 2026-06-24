package ru.translator.eng_rus.Role.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.translator.eng_rus.Role.Entity.Role;
import ru.translator.eng_rus.Role.Enum.RoleEnum;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRole(RoleEnum roleEnum);
}
