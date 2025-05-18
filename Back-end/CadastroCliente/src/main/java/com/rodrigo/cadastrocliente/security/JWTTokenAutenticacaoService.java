package com.rodrigo.cadastrocliente.security;

import com.rodrigo.cadastrocliente.ApplicationContextLoad;
import com.rodrigo.cadastrocliente.models.Usuario;
import com.rodrigo.cadastrocliente.repositories.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticacaoService {

    private static final long EXPIRATION_TIME = 259200000;

    private static final String SECRET = "bsfdkgalhfskh998783#";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws IOException {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;

        response.addHeader(HEADER_STRING, token);

        liberarCors(response);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

public Authentication getAuthentication(HttpServletRequest req, HttpServletResponse res) {
        String token = req.getHeader(HEADER_STRING);

        if(token != null) {
            String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJwt(tokenLimpo)
                    .getBody()
                    .getSubject();

            if(user != null) {
                Usuario usuario = ApplicationContextLoad
                        .getApplicationContext()
                        .getBean(UsuarioRepository.class)
                        .findByLogin(user);

                if(usuario != null) {
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getSenha(),
                            usuario.getAuthorities());
                }
            }
        }

        liberarCors(res);
        return null;

    }

    private void liberarCors(HttpServletResponse response) {

        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }

        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }

        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }
}
