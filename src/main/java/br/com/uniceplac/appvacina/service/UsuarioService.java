package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.PacienteDTO;
import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.models.PacienteModel;
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

    public List<UsuarioDTO> findAll() {
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioModels.stream().map(x -> new UsuarioDTO(x)).toList();
    }

    public UsuarioDTO findById(Long idUsuario) {
        return new UsuarioDTO(usuarioRepository.findById(idUsuario).get());
    }

    public Optional<UsuarioModel> findByIdPrivate(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public UsuarioDTO saveUsuario(UsuarioModel usuarioModel) {
        usuarioRepository.save(usuarioModel);
        return new UsuarioDTO(usuarioModel);
    }

    public UsuarioDTO deleteUsuario(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
        return new UsuarioDTO(usuarioModel);
    }

}
