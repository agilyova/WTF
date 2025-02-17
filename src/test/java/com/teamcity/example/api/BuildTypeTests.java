package com.teamcity.example.api;

import com.teamcity.example.api.models.BuildType;
import com.teamcity.example.api.models.Project;
import com.teamcity.example.api.requests.CheckedRequests;
import com.teamcity.example.api.requests.UncheckedBase;
import com.teamcity.example.api.spec.Specifications;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.teamcity.example.api.enums.Endpoint.*;
import static com.teamcity.example.api.generators.TestDataGenerator.generate;

public class BuildTypeTests extends BaseApiTest {
  @Test()
  @DisplayName("User should be able to create build type")
  @Tags({@Tag("Positive"), @Tag("CRUD")})
  public void userCreatesBuildTypeTest() {
    superUserCheckRequests.getRequest(USERS).create(testData.getUser());
    var userCheckRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

    userCheckRequests.<Project>getRequest(PROJECTS).create(testData.getProject());

    userCheckRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

    var createdBuildType = userCheckRequests.<BuildType>getRequest(BUILD_TYPES).read(testData.getBuildType().getId());
    softly.assertThat(testData.getBuildType().getName()).withFailMessage("Build type name is not correct").isEqualTo(createdBuildType.getName());
  }

  @Test()
  @DisplayName("User should not be able to create two build types with the same id")
  @Tags({@Tag("Negative"), @Tag("CRUD")})
  public void userCreatesTwoBuildTypesWithTheSameIdTest() {
    var buildTypeWithSameId = generate(Arrays.asList(testData.getProject()), BuildType.class, testData.getBuildType().getId());

    superUserCheckRequests.getRequest(USERS).create(testData.getUser());

    var userCheckRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

    userCheckRequests.<Project>getRequest(PROJECTS).create(testData.getProject());

    userCheckRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

    Response response = new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES).create(buildTypeWithSameId);

    softly.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    softly.assertThat(response.then().extract().asString())
      .contains("The build configuration / template ID \"%s\" is already used by another configuration or template".formatted(buildTypeWithSameId.getId()));
  }
}
