package com.teamcity.example.api;

import com.teamcity.example.api.models.User;
import com.teamcity.example.api.spec.Specifications;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DummyTest extends BaseApiTest {

  @Test
  void getEndpointsShouldReturnAllMainEndPoints() {
    RestAssured
      .given()
        .spec(Specifications.authSpec(User.builder().userName("admin").password("admin").build()))
      .when()
        .get("/app/rest/server")
      .then()
        .statusCode(HttpStatus.SC_OK);
  }
}
