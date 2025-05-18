package com.rodrigo.cadastrocliente.repositories;

import com.rodrigo.cadastrocliente.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findByLogin(String login);

}
