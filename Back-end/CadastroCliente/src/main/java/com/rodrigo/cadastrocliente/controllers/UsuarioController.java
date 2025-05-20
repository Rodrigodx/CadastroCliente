package com.rodrigo.cadastrocliente.controllers;

import com.rodrigo.cadastrocliente.dtos.request.LoginRequest;
import com.rodrigo.cadastrocliente.dtos.response.LoginResponse;
import com.rodrigo.cadastrocliente.models.Usuario;
import com.rodrigo.cadastrocliente.security.JWTTokenAutenticacaoService;
import com.rodrigo.cadastrocliente.services.ImplUserDetailsService;
import com.rodrigo.cadastrocliente.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsuarioController {

    private static final String USER_REGISTRATION = "/api/registration";
    private static final String USER_LOGIN = "/api/login";
    private final UsuarioService usuarioService;
    private final ImplUserDetailsService userDetailsService;
    private final JWTTokenAutenticacaoService jwtTokenAutenticacaoService;

    public UsuarioController(UsuarioService usuarioService, ImplUserDetailsService userDetailsService){
        this.usuarioService = usuarioService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenAutenticacaoService = new JWTTokenAutenticacaoService();
    }



    @PostMapping(path = USER_REGISTRATION)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario newUserRegistration(@Valid @RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @PostMapping(path = USER_LOGIN)
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        this.usuarioService.autenticarUsuario(loginRequest.getLogin(),  loginRequest.getSenha());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getLogin());

        String token = this.jwtTokenAutenticacaoService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(userDetails.getUsername(), token));
    }

}
