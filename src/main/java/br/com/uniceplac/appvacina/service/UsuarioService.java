package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.dto.UsuarioDto;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    final UsuarioRepository usuarioRepository;

    public List<UsuarioDto> findAll() {
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioModels.stream().map(x -> new UsuarioDto(x)).toList();
    }

    public UsuarioDto findById(Long idUsuario) {

        return new UsuarioDto(usuarioRepository.findById(idUsuario).get());
    }

    public Optional<UsuarioModel> findByIdPrivate(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public UsuarioDto saveUsuario(UsuarioModel usuarioModel) {
        usuarioRepository.save(usuarioModel);
        return new UsuarioDto(usuarioModel);
    }

    public UsuarioDto deleteUsuarioById(Long idUsuario) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(idUsuario);
        usuarioRepository.deleteById(idUsuario);
        if(usuario.isEmpty()) {
            return null;
        } else {
            return new UsuarioDto(usuario.get());

        }
    }

}
