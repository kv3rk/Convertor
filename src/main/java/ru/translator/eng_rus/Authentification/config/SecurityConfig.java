package ru.translator.eng_rus.Authentification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.translator.eng_rus.User.Repository.UserRepository;

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

        UserDetails user1 = User.builder()
                .username("Anton")
                .password(encoder.encode("java2905"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("Petya")
                .password(encoder.encode("java2905"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("Masha")
                .password(encoder.encode("java2905"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
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
