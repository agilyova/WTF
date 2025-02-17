package com.teamcity.example.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamcity.example.api.annotations.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends BaseModel {
  @Random
  private String id;
  @Random
  private String name;
  private String locator;
}
