package com.teamcity.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step extends BaseModel{
  private String id;
  private String name;
  private String type = "simpleRunner";
}
