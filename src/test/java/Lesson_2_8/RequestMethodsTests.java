package Lesson_2_8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestMethodsTests extends BaseApiTest {

    @Test
    @DisplayName("Get /get - echo query param + status 200")
    void get_echo_query() {
        given()
             .queryParam("foo1", "bar1")
             .queryParam("foo2", "bar2")
        .when()
             .get("/get")
        .then()
             .statusCode(200)
             .body("args.foo1", equalTo("bar1"))
             .body("args.foo2", equalTo("bar2"))
             .body("url", containsString("/get"));
    }

    @Test
    @DisplayName("Post /post - echo JSON body + status 200")
    void post_echo_json() {
        Map<String, Object> payload = Map.of(
                "name", "Alice",
                "age", 30,
                "active", true
        );

        given()
             .contentType(ContentType.JSON)
             .body(payload)
        .when()
             .post("/post")
        .then()
             .statusCode(200)
             .body("json.name", equalTo("Alice"))
             .body("json.age", equalTo(30))
             .body("json.active", equalTo(true));
    }

    @Test
    @DisplayName("Put /put - echo JSON body + status 200")
    void put_echo_json() {
        Map<String, Object> payload = Map.of(
                "id",
                101,
                "status",
                "updated");

        given()
             .contentType(ContentType.JSON)
             .body(payload)
        .when()
             .put("/put")
        .then()
             .statusCode(200)
             .body("json.id", equalTo(101))
             .body("json.status", equalTo("updated"));
    }

    @Test
    @DisplayName("PATCH /patch — echo JSON body + status 200")
    void patch_echo_json(){
        Map<String, Object> payload = Map.of(
                "op",
                "patch",
                "ok",
                true);

        given()
             .contentType(ContentType.JSON)
             .body(payload)
        .when()
             .patch("/patch")
        .then()
             .statusCode(200)
             .body("json.op", equalTo("patch"))
             .body("json.ok", equalTo(true));
    }

    @Test
    @DisplayName("Delete /delete - echo query + status 200")
    void delete_echo_json(){
        given()
             .queryParam("id",777)
        .when()
             .delete("/delete")
        .then()
             .statusCode(200)
             .body("args.id", equalTo("777"));
    }

    @Test
    @DisplayName("HEAD /get - status 200 и пустое тело")
    void head_status_only(){
        given()
             .queryParam("x","y")
        .when()
             .head("/get")
        .then()
             .statusCode(200)
             .header("content-type", containsString("application/json"));
    }
}
