package com.teamcity.example.api.requests;

import com.teamcity.example.api.enums.Endpoint;
import com.teamcity.example.api.models.BaseModel;
import io.restassured.specification.RequestSpecification;

import java.util.EnumMap;

public class CheckedRequests {
  private final EnumMap<Endpoint, CheckedBase<?>> requests = new EnumMap<>(Endpoint.class);

  public CheckedRequests(RequestSpecification spec) {
    for (var endpoint : Endpoint.values()) {
      requests.put(endpoint, new CheckedBase<>(spec, endpoint));
    }
  }

  public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
    return (CheckedBase<T>) requests.get(endpoint);
  }
}
