package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class SimpleApiTest {
    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/");

    @Test
    public void testGetUserName() {
        assertEquals(response.getStatusCode(), 200);
        // Print response body
        System.out.println("Response: " + response.getBody().asString());
        String name = response.jsonPath().getString("name");
        System.out.println("Name: " + name);
    }
    @Test
    public void testGetUserAddress() {
        assertEquals(response.getStatusCode(), 200);
        String address = response.jsonPath().getString("address");
        String city = response.jsonPath().getString("address.city");
        String suite = response.jsonPath().getString("address.suite");
        String zipcode = response.jsonPath().getString("address.zipcode");
        String geolat = response.jsonPath().getString("address.geo.lat");

        System.out.println("City: " + city);
        System.out.println("State: " + suite);
        System.out.println("zipcode: " + zipcode);
        System.out.println("geo: " + geolat);



        System.out.println("Name: " + address.trim());

    }
    @Test
    public void testCreatePost() {
        String requestBody = """
        {
            "title": "Mahmoud's Post",
            "body": "This is a test post",
            "userId": 10
        }
    """;

        Response response = RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("%%%%%%%%%%%%%%%%Post ID: " + response.jsonPath().getInt("id"));
        int newPostId = response.jsonPath().getInt("id");
        Response getResponse = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + newPostId);

        System.out.println("Created Post: " + getResponse.getBody().asString());
    }

    @Test
    public void testUpdatePost() {
        String updatedBody = """
        {
            "id": 1,
            "title": "Updated Title",
            "body": "Updated body",
            "userId": 1
        }
    """;

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(updatedBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().body();
    }
    @Test
    public void testDeletePost() {
        RestAssured
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);
    }

}
