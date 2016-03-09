package com.restapicep;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class RestApiCepApplicationTests {

	public RestApiCepApplicationTests() {
		baseURI = "http://localhost:8080/rest-api";
	}

	@Test
	/* Chama o serviço pelo metodo GET */
	public void testGetAddress() {

		given()
				.when()
				.get("/busca/15810070")
				.then()
				.statusCode(200)
				.body("status", equalTo("SUCESSO"))
				.body("data.cep", equalTo("15810-070"))
				.body("data.endereco", equalTo("Rua Queluz"))
				.body("data.bairro", equalTo("Vila Sotto"))
				.body("data.cidade", equalTo("Catanduva"))
				.body("data.estado", equalTo("SP"))

				.assertThat()
				.body(matchesJsonSchemaInClasspath("schema_modelo.json"));
	}


	@Test
	/* Chama o serviço pelo metodo GET */
	public void testAddressInvalid() {

		given()
				.when()
				.get("/busca/158100")
				.then()
				.statusCode(200)
				.body("status", equalTo("ERRO"))
				.body("error.mensagem", equalTo("O CEP informado é inválido."));
	}


	@Test
	/* Chama o serviço pelo metodo GET */
	public void testAddressNotFound() {

		given()
				.when()
				.get("/busca/15800000")
				.then()
				.statusCode(200)
				.body("status", equalTo("ERRO"))
				.body("error.mensagem", equalTo("O CEP informado não foi encontrado."));
	}


	@Test
	/* Chama o serviço pelo metodo POST */
	public void testCreateAddressSuccess() {

		String myJson = "{\"cep\":\"15810077\",\"endereco\": \"Rua Cananeia\","
				+ "\"bairro\": \"Vila Sotto\",\"cidade\": \"Catanduva\",\"estado\": \"SP\"}";

		given()
				.contentType("application/json")
				.body(myJson)
				.when()
				.post("/")
				.then().statusCode(200)
				.body("status", equalTo("SUCESSO"))
				.body("data.mensagem", equalTo("O endereço foi cadastrado com sucesso."));
	}

	@Test
	/* Chama o serviço pelo metodo POST */
	public void testCreateAddressFail() {

		String myJson = "{\"cep\":\"15810077\",\"endereco\": \"Rua Cananeia\","
				+ "\"bairro\": \"Vila Sotto\",\"cidade\": \"Catanduva\",\"estado\": \"SP\"}";

		given()
				.contentType("application/json")
				.body(myJson)
				.when()
				.post("/")
				.then().statusCode(200)
				.body("status", equalTo("ERRO"))
				.body("error.mensagem", equalTo("O endereço informado já foi cadastrado."));
	}


	@Test
	/* Chama o serviço pelo metodo DELETE */
	public void testDeleteAddress() {

		given()
				.contentType("application/json")
				.when()
				.delete("/15810077")
				.then().statusCode(200)
				.body("status", equalTo("SUCESSO"))
				.body("data.mensagem", equalTo("Endereço removido com sucesso."));
	}
}
