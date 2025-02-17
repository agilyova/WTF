package com.teamcity.example.api.enums;

import com.teamcity.example.api.models.BaseModel;
import com.teamcity.example.api.models.BuildType;
import com.teamcity.example.api.models.Project;
import com.teamcity.example.api.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
  BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
  PROJECTS("/app/rest/projects", Project.class),
  USERS("/app/rest/users", User.class);

  private final String url;
  private final Class<? extends BaseModel> modelClass;
}
