package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    final UsuarioRepository usuarioRepository;
}
