package br.com.uniceplac.appvacina.security.auth;

import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email);

        return usuarioModel;
    }
}
