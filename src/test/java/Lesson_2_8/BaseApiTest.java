package Lesson_2_8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {
    protected static final String BASE_URL = "https://postman-echo.com";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
