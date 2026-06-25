package ru.translator.eng_rus.User.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.translator.eng_rus.Role.Entity.Role;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

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

    @Override
    @JsonIgnore
    public String getPassword() {

        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {

        return this.nickname;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(roleName));
    }
}
