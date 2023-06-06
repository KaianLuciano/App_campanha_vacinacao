package br.com.uniceplac.appvacina.service;

import br.com.uniceplac.appvacina.DTO.UsuarioDTO;
import br.com.uniceplac.appvacina.DTO.VacinasDTO;
import br.com.uniceplac.appvacina.controller.utils.ApiResponse;
import br.com.uniceplac.appvacina.models.LoteModel;
import br.com.uniceplac.appvacina.models.UsuarioModel;
import br.com.uniceplac.appvacina.models.VacinasModel;
import br.com.uniceplac.appvacina.repository.LoteRepository;
import br.com.uniceplac.appvacina.repository.VacinasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VacinasService {

    final VacinasRepository vacinasRepository;
    final LoteService loteService;

    public List<VacinasDTO> findAll() {
        List<VacinasModel> vacinasModels = vacinasRepository.findAll();
        return vacinasModels.stream().map(x -> new VacinasDTO(x)).toList();
    }

    public VacinasDTO findById(Long idVacinas) {
        return new VacinasDTO(vacinasRepository.findById(idVacinas).get());
    }

    public Optional<VacinasModel> findByIdPrivate(Long idVacinas) {
        return vacinasRepository.findById(idVacinas);
    }

    public VacinasDTO saveVacina(VacinasModel vacinasModel) {

        try {
            if (vacinasModel.getLote() == null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateTemp = dateFormat.format(new Date());
                Date data = dateFormat.parse(dateTemp);

                List<VacinasModel> vacina = new ArrayList<>();
                vacina.add(vacinasModel);
                LoteModel loteModel = LoteModel.builder()
                        .dataFabricacao(data)
                        .dataValidade(data)
                        .composicao("componentes")
                        .fabricante("pfizer")
                        .informacoesArmazenamento("16g")
                        .informacoesControleQualidade("OK")
                        .registroSanitario("OK")
                        .vacinas(vacina)
                        .build();

                vacinasModel.setLote(loteModel);
                loteService.saveLote(loteModel);
                vacinasRepository.save(vacinasModel);
                return new VacinasDTO(vacinasModel);
            } else {
                vacinasRepository.save(vacinasModel);
                return new VacinasDTO(vacinasModel);
            }


        } catch (ParseException e) {
            return null;
        }
    }

    public VacinasDTO deleteVacina(Long idVacina) {
        VacinasModel vacina = vacinasRepository.findById(idVacina).get();
        vacinasRepository.deleteById(idVacina);
        return new VacinasDTO(vacina);
    }

}
