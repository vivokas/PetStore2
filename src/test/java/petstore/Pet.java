//1 - Pacote
package petstore;

//2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

//3 - Classes
public class Pet {
    //3.1 Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade

    //3.2 Métodose Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
    //Incluir - Create - POst
    @Test //Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Sintaxe Gherkin
        //Dado - Quando - Então
        //Give - When - Then

        given()
                .contentType("application/json") // Comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
        ;

    }
}
