package ru.translator.eng_rus.Authentification.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.translator.eng_rus.User.Entity.User;
import ru.translator.eng_rus.User.Repository.UserRepository;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repository.findByNickname(username);

        log.info("Found user with nickname: [{}] and role [{}]",
                user.getUsername(), user.getAuthorities());

        return user;
    }
}
