package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.models.Usuario;
import com.rodrigo.cadastrocliente.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImplUserDetailsService implUserDetailsService;
    AuthenticationManager authenticationManager;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ImplUserDetailsService implUserDetailsService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.implUserDetailsService = implUserDetailsService;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            throw new RuntimeException("Usuário já existe");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void autenticarUsuario(String login, String senha) {
        UserDetails userDetails = implUserDetailsService.loadUserByUsername(login);

        boolean senhaCorreta = passwordEncoder.matches(senha, userDetails.getPassword());

        if (!senhaCorreta) {
            throw new RuntimeException("Credenciais inválidas");
        }
        System.out.println("passou na verificação");
    }

}
