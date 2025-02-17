package com.teamcity.example.api.spec;

import com.teamcity.example.api.config.Config;
import com.teamcity.example.api.models.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class Specifications {
  private static RequestSpecBuilder reqBuilder() {
    RequestSpecBuilder specBuilder = new RequestSpecBuilder();
    return specBuilder
      .setBaseUri("http://" + Config.getConfig().getProperty("host"))
      .setContentType(ContentType.JSON)
      .setAccept(ContentType.JSON)
      .addFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured()));
  }

  public static RequestSpecification superUserAuthSpec() {
    BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
    basicAuthScheme.setUserName("");
    basicAuthScheme.setPassword(Config.getConfig().getProperty("superUserToken"));
    return reqBuilder().setAuth(basicAuthScheme).build();
  }

  public static RequestSpecification unAuthSpec() {
    return reqBuilder().build();
  }

  public static RequestSpecification authSpec(User user) {
    BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
    basicAuthScheme.setUserName(user.getUsername());
    basicAuthScheme.setPassword(user.getPassword());
    return reqBuilder().setAuth(basicAuthScheme).build();
  }
}
