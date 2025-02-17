package com.teamcity.example.api;

import com.teamcity.example.api.generators.TestDataStorage;
import com.teamcity.example.api.models.TestData;
import com.teamcity.example.api.requests.CheckedRequests;
import com.teamcity.example.api.spec.Specifications;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.teamcity.example.api.generators.TestDataGenerator.generate;

public class BaseTest {
  SoftAssertions softly;
  protected CheckedRequests superUserCheckRequests = new CheckedRequests(Specifications.superUserAuthSpec());
  protected TestData testData;

  @BeforeEach()
  public void beforeTest() {
    softly = new SoftAssertions();
    testData = generate();
  }

  @AfterEach()
  public void afterTest() {
    softly.assertAll();
    TestDataStorage.getStorage().deleteCreatedEntities();
  }
}
