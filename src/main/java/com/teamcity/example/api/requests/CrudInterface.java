package com.teamcity.example.api.requests;

import com.teamcity.example.api.models.BaseModel;

public interface CrudInterface {
  Object create(BaseModel model);
  Object read(String id);
  Object update(String id, BaseModel model);
  Object delete(String id);
}
