package stepDefinitions;

import io.cucumber.java.en.*;
import pojos.RoomPojo;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static stepDefinitions.UI_MedunnaRoomStepDefinitions.roomId;
import static stepDefinitions.UI_MedunnaRoomStepDefinitions.roomNumberFaker;

public class DataBase_RoomStepDefinitions {
    Connection connection;
    Statement statement;
    @Given("connect the database")
    public void connect_the_database() throws SQLException {
        //jdbc'den DriverManager ve Connection aldik

        //1.connection olustur
        connection= DriverManager.
                getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user","Medunna_pass_@6");

        //2.statement olustur
        statement = connection.createStatement();




    }
    @Then("read room and validate")
    public void read_room_and_validate() throws SQLException {
        //3.query calistir

        /*
        Ornek: description: Ardahan manzaralı olan room'a ulasmak icin asagidaki ornek kodlari yazdik.

        ResultSet resultSet = statement.executeQuery("SELECT * FROM room ORDER BY created_date DESC");
        resultSet.next(); //next pointer'i bir alt satira atar
        resultSet.next();
        resultSet.next();
        resultSet.next();
        System.out.println("resultSet.getString(\"description\") = " + resultSet.getString("description"));
 */


        /*
        Ornek: id: 55196 olan room'a ulasmak icin asagidaki ornek kodlari yazdik.

        ResultSet resultSet = statement.executeQuery("SELECT * FROM room WHERE id= 55196");
        resultSet.next(); //next pointer'i bir alt satira atar. bir alt satira gitmek zorundayiz
         System.out.println("resultSet.getString(\"price\") = " + resultSet.getString("price"));
        System.out.println("resultSet.getString(\"id\") = " + resultSet.getString("id"));
         */
        String query = "SELECT * FROM room WHERE id="+roomId;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next(); //next pointer'i bir alt satira atar.

       RoomPojo expectedData= new RoomPojo(roomNumberFaker, "PREMIUM_DELUXE", true, 500.00, "Ardahan manzaralı");


        assertEquals(expectedData.getRoomNumber(), resultSet.getInt("room_number"));
        assertEquals(expectedData.getRoomType(), resultSet.getString("room_type"));
        assertEquals(expectedData.getDescription(), resultSet.getObject("description")); //data type sorun olacaksa object kullaniriz
        assertEquals(expectedData.getStatus(), resultSet.getString("status"));
        assertEquals(expectedData.getPrice()+"0", resultSet.getString("price")+"");

    }

}
