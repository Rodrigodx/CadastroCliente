package com.rodrigo.cadastrocliente.models;

import com.rodrigo.cadastrocliente.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtualSenha;

    @OneToOne
    @JoinColumn(
            name = "cliente_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "cliente_fk")
    )
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == RoleEnum.ROLE_ADMIN) {
            return Arrays.asList(
                    new SimpleGrantedAuthority(RoleEnum.ROLE_ADMIN.name()),
                    new SimpleGrantedAuthority(RoleEnum.ROLE_USUARIO.name())
            );
        } else {
            return Collections.singletonList(
                    new SimpleGrantedAuthority(RoleEnum.ROLE_USUARIO.name())
            );
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
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
