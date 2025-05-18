package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.models.Usuario;
import com.rodrigo.cadastrocliente.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplUserDetailsService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public ImplUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usario = usuarioRepository.findByLogin(username);

        if (usario == null) {
            throw new UsernameNotFoundException("Usuário foi encontrado");
        }

        return new User(usario.getLogin(), usario.getSenha(), usario.getAuthorities());
    }
}
