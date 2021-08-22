//1 - Pacote
package petstore;

//2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

//3 - Classes
public class Pet {
    //3.1 Atributos características
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade

    //3.2 Métodos e Funções
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
        //checagem se deu certo
                .body("name", is("Alice"))
                .body("status",is("available"))
                .body( "category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;

    }

}
