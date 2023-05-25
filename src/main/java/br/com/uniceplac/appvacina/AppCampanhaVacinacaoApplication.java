package br.com.uniceplac.appvacina;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Campanha de Vacinação API", version = "1", description = "API desenvolvido para os testes do Aplicativo"))
@EnableScheduling
public class AppCampanhaVacinacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppCampanhaVacinacaoApplication.class, args);
	}

}
