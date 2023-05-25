package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.repository.VacinasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VacinasService {

    final VacinasRepository vacinasRepository;
}
