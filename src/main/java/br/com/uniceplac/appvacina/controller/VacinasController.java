package br.com.uniceplac.appvacina.controller;

import br.com.uniceplac.appvacina.service.VacinasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacinas")
@AllArgsConstructor
public class VacinasController {

    final VacinasService vacinasService;
}
