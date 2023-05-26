package br.com.uniceplac.appvacina;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Campanha de Vacinação API", version = "1", description = "" +
		"O projeto \"App_campanha_vacinacao\" é uma API desenvolvida utilizando o framework Spring Boot, versão 3.1.0. A API é projetada para ser utilizada em um aplicativo de vacinação, fornecendo funcionalidades relacionadas à gestão de campanhas de vacinação.\n" +
		"\n" +
		"O projeto utiliza a versão 17 do Java, além disso possui as seguintes dependências:\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-starter-data-jpa (versão 3.1.0): Dependência do Spring Boot para integração com o banco de dados utilizando a especificação JPA (Java Persistence API).\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-starter-validation (versão 3.1.0): Dependência do Spring Boot para validação de dados.\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-starter-web (versão 3.1.0): Dependência do Spring Boot para desenvolvimento de aplicativos web.\n" +
		"\n" +
		"    org.postgresql:postgresql (versão específica não fornecida): Dependência para integração com o PostgreSQL, um sistema de gerenciamento de banco de dados relacional.\n" +
		"\n" +
		"    org.projectlombok:lombok (versão não fornecida): Dependência que facilita o desenvolvimento eliminando a necessidade de escrever código repetitivo, como getters, setters e construtores.\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-starter-test (versão 3.1.0): Dependência do Spring Boot para testes.\n" +
		"\n" +
		"    com.fasterxml.jackson.core:jackson-databind (versão não fornecida): Dependência para mapeamento de objetos Java para JSON e vice-versa.\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-devtools (versão 3.1.0): Dependência para facilitar o desenvolvimento, fornecendo recursos de reinicialização automática da aplicação.\n" +
		"\n" +
		"    org.springdoc:springdoc-openapi-starter-webmvc-ui (versão 2.1.0): Dependência para geração da documentação da API utilizando o OpenAPI.\n" +
		"\n" +
		"    org.springframework.boot:spring-boot-starter-actuator (versão 3.1.0): Dependência do Spring Boot para monitoramento e exposição de métricas da aplicação.\n" +
		"\n" +
		"O projeto é construído e gerenciado pelo Apache Maven. O arquivo de configuração do projeto, chamado POM.xml, define as dependências, a versão do Java utilizada (versão 17) e os plugins do Maven necessários para o empacotamento e execução da aplicação.\n" +
		"\n" +
		"Em resumo, o projeto é uma API de campanha de vacinação, desenvolvida em Spring Boot 3.1.0, que utiliza várias dependências e tecnologias para fornecer funcionalidades de gerenciamento de campanhas de vacinação, integração com o banco de dados, validação de dados, documentação da API, entre outros recursos."))
@EnableScheduling
public class AppCampanhaVacinacaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppCampanhaVacinacaoApplication.class, args);
	}
	// http://localhost:8080/swagger-ui/index.html#/
}
