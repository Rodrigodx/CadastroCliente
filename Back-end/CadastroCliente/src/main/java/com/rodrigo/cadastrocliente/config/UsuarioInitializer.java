package com.rodrigo.cadastrocliente.config;

import com.rodrigo.cadastrocliente.enums.RoleEnum;
import com.rodrigo.cadastrocliente.models.Usuario;
import com.rodrigo.cadastrocliente.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UsuarioInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if(usuarioRepository.count() == 0) {

            Usuario admin = new Usuario();
            admin.setLogin("admin");
            admin.setSenha(new BCryptPasswordEncoder().encode("123qwe!@#"));
            admin.setDataAtualSenha(new Date());
            admin.setRole(RoleEnum.ROLE_ADMIN);

            Usuario usuario = new Usuario();
            usuario.setLogin("padrão");
            usuario.setSenha(new BCryptPasswordEncoder().encode("123qwe123"));
            usuario.setDataAtualSenha(new Date());
            usuario.setRole(RoleEnum.ROLE_USUARIO);

            usuarioRepository.save(admin);
            usuarioRepository.save(usuario);
        }
    }
}
