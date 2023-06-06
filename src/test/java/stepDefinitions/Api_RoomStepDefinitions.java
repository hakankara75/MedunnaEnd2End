package stepDefinitions;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojos.RoomPojo;

import static base_Urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static stepDefinitions.UI_MedunnaRoomStepDefinitions.roomId;
import static stepDefinitions.UI_MedunnaRoomStepDefinitions.roomNumberFaker;

public class Api_RoomStepDefinitions {
    Response response;
    RoomPojo  expectedData;
    @Given("send get request to url")
    public void send_get_request_to_url() {
        //set the url
        spec.pathParams("first","api", "second", "rooms")
                .queryParams("sort", "createdDate,desc");

        //set the expected data


        //send the request and get the response
        response=given(spec).get("{first}/{second}");
        response.prettyPrint();


    }
    @When("validate body")
    public void validate_body() {
        //Gelen body içinden bizim oluşturduğumuz odanın numarası ile filtreleme yapıyoruz.
    Object actualRoomType= response.jsonPath().getList("findAll{it.roomNumber==1234}.roomType").get(0);

        //Filtrelenen bodyden gerekli datayı nokta sonrasına belirterek alıyoruz.
    Object actualStatus= response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.status").get(0);


    Object actualPrice= response.jsonPath().getList("findAll{it.roomNumber==\"+roomNumberFaker+\"}.price").get(0);
    Object actualDescription= response.jsonPath().getList("findAll{it.roomNumber==\"+roomNumberFaker+\"}.description").get(0);
    Object actualRoomNumber= response.jsonPath().getList("findAll{it.roomNumber==\"+roomNumberFaker+\"}.roomNumber").get(0);


    assertEquals("PREMIUM_DELUXE", actualRoomType);
    assertEquals(true, actualStatus);
    assertEquals("500.0", actualPrice+"");
    assertEquals("Ardahan manzaralı", actualDescription);
    }

    @Given("send get request to url by id")
    public void sendGetRequestToUrlById() {
        //set the url  --> https://medunna.com/api/rooms/5512
        spec.pathParams("first", "api", "second", "rooms", "third", roomId);

        //set the expected data
      expectedData= new RoomPojo(roomNumberFaker, "PREMIUM_DELUXE", true, 500.00, "Ardahan manzaralı");

      //send the request and get the response
        response= given(spec).get("{first}/{second}/{third}");
        //response.prettyPrint();
    }

    @When("validate response body")
    public void validateResponseBody() throws JsonProcessingException {
        RoomPojo actualData= new ObjectMapper().readValue(response.asString(),RoomPojo.class);

       assertEquals(200, response.statusCode());

       assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
       assertEquals(expectedData.getRoomType(), actualData.getRoomType());
       assertEquals(expectedData.getDescription(), actualData.getDescription());
       assertEquals(expectedData.getStatus(), actualData.getStatus());
       assertEquals(expectedData.getPrice()+"", actualData.getPrice()+"");
    }
}
