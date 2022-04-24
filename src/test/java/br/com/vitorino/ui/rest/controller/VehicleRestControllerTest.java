package br.com.vitorino.ui.rest.controller;

import br.com.vitorino.ui.rest.dto.VehicleListResponseDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveRequestDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class VehicleRestControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testIfGetReturnsAList() throws JsonProcessingException {
        var response = given()
                .when().get("/vehicle")
                .andReturn();
        response.then().statusCode(HttpStatus.OK.value());
        var responseBody = response.getBody().asString();

        List<VehicleListResponseDTO> list = mapper.readValue(responseBody, mapper.getTypeFactory().constructParametricType(List.class, VehicleListResponseDTO.class));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIfSaveReturnsAnId() throws JsonProcessingException {
        var requestDto = new VehicleSaveRequestDTO();
        requestDto.setName(UUID.randomUUID().toString());

        var response = given()
                .body(mapper.writeValueAsString(requestDto))
                .contentType("application/json")
                .when().post("/vehicle")
                .andReturn();

        var responseDTO = mapper.readValue(response.getBody().asString(), VehicleSaveResponseDTO.class);
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getId());

        delete(responseDTO.getId());
    }

    @Test
    public void testIfGetByIdReturnsOk() throws JsonProcessingException {
        var vehicle = create();

        var response = given().get("/vehicle/{id}", vehicle.getId().toString()).andReturn();
        response.then().statusCode(HttpStatus.OK.value());
        var responseDto = mapper.readValue(response.getBody().asString(), VehicleListResponseDTO.class);

        assertEquals(vehicle.getId(), responseDto.getId());
        assertEquals(vehicle.getName(), responseDto.getName());

        delete(vehicle.getId());
    }

    @Test
    public void testIfDeleteReturnsOk() throws JsonProcessingException {
        var vehicle = create();

        given().delete("/vehicle/{id}", vehicle.getId().toString()).then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testIfDeleteReturnsOkWhenVehicleNotExist() {
        given().delete("/vehicle/{id}", UUID.randomUUID().toString()).then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testIfDeleteAllReturnsOk() throws JsonProcessingException {
        create();
        create();

        List<VehicleListResponseDTO> list = mapper.readValue(given().get("/vehicle").andReturn().getBody().asString(), mapper.getTypeFactory().constructParametricType(List.class, VehicleListResponseDTO.class));

        assertFalse(list.isEmpty());
        assertEquals(2, list.size());

        given().delete("/vehicle").then().statusCode(HttpStatus.OK.value());

        list = mapper.readValue(given().get("/vehicle").andReturn().getBody().asString(), mapper.getTypeFactory().constructParametricType(List.class, VehicleListResponseDTO.class));

        assertTrue(list.isEmpty());
    }

    private VehicleListResponseDTO create() throws JsonProcessingException {
        var requestDto = new VehicleSaveRequestDTO();
        requestDto.setName(UUID.randomUUID().toString());

        var response = given()
                .body(mapper.writeValueAsString(requestDto))
                .contentType("application/json")
                .when().post("/vehicle")
                .andReturn();

        var responseDTO = mapper.readValue(response.getBody().asString(), VehicleSaveResponseDTO.class);
        return mapper.readValue(given().get("/vehicle/{id}", responseDTO.getId().toString()).andReturn().body().asString(), VehicleListResponseDTO.class);
    }

    private void delete(UUID id) {
        given().delete("/vehicle/{id}", id.toString());
    }

}
