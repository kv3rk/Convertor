package ru.translator.eng_rus.Authentification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.translator.eng_rus.Authentification.Service.InMemoryUserDetailsService;
import ru.translator.eng_rus.User.Repository.UserRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        List<ru.translator.eng_rus.User.Entity.User> dbUsers = userRepository.findAll();

        List<UserDetails> resultList = dbUsers.stream().map(x -> {
                    UserDetails user = org.springframework.security.core.userdetails.User
                            .withUsername(x.getUsername())
                            .password(encoder.encode(x.getPassword()))
                            .authorities(x.getAuthorities())
                            .build();
                    return user;
                }
        ).toList();

        return new InMemoryUserDetailsService(resultList);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {


        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService(new BCryptPasswordEncoder()));
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/translate/login",
                                "/css/**",
                                "/img/**",
                                "/js/**"
                        ).permitAll()

                        .requestMatchers(
                                "/translate/**"
                        ).authenticated()

                        .anyRequest().authenticated()

                )

                .formLogin(login -> login
                        .loginPage("/translate/login")
                        .defaultSuccessUrl("/translate/main")
                        .failureUrl("/translate/login")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/translate/login")
                );

        return http.build();

    }
}
