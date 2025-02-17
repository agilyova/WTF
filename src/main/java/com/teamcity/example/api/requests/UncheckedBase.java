package com.teamcity.example.api.requests;

import com.teamcity.example.api.enums.Endpoint;
import com.teamcity.example.api.models.BaseModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UncheckedBase extends Request implements CrudInterface {
  public UncheckedBase(RequestSpecification spec, Endpoint endpoint) {
    super(spec, endpoint);
  }

  @Override
  public Response create(BaseModel model) {
    return
      RestAssured
        .given()
        .spec(spec)
        .body(model)
        .when()
        .post(endpoint.getUrl());
  }

  @Override
  public Response read(String id) {
    return
      RestAssured
        .given()
        .spec(spec)
        .when()
        .get(endpoint.getUrl() + "/id:" + id);
  }

  @Override
  public Response update(String id, BaseModel model) {
    return
      RestAssured
        .given()
        .spec(spec)
        .body(model)
        .when()
        .put(endpoint.getUrl() + "/id:" + id);
  }

  @Override
  public Response delete(String id) {
    return
      RestAssured
        .given()
        .spec(spec)
        .when()
        .delete(endpoint.getUrl() + "/id:" + id);
  }
}
