package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.security.user.LoginDto;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getSenha());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (UsuarioModel) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario);

    }
}
