package ClaimService.ClaimService.Security.Model;

import ClaimService.ClaimService.Security.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

@Id
@GeneratedValue(strategy = GenerationType.TABLE)
private long id;
@Column(name = "user_name")
private String Username;
private String Password;
private String Name;
private String Surname;
private String Email;
@Enumerated(EnumType.STRING)
private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername(){
        return Username;
    }
    @Override
    public String getPassword(){
        return Password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
