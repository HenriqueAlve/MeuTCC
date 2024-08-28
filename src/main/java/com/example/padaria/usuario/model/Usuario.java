package com.example.padaria.usuario.model;


import com.example.padaria.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Usuario(UserDTO data, String password){
        this.username = data.username();
        this.password = password;
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.endereco = data.endereco();
        this.role = data.role();
    }

    public Usuario(UserDTO data, String encryptedPassword, UserRole userRole) {
        this.username = data.username();
        this.password = encryptedPassword;
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.endereco = data.endereco();
        this.role = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ROOT) return List.of(new SimpleGrantedAuthority("ROLE_ROOT"), new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.role == UserRole.EMPLOYEE) return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
