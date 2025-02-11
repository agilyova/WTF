package com.teamcity.example.api.requests;

import com.teamcity.example.api.enums.Endpoint;
import com.teamcity.example.api.models.BaseModel;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

@SuppressWarnings("unchecked")
public class CheckedBase<T extends BaseModel> extends Request implements CrudInterface{
  private final UncheckedBase uncheckedBase = new UncheckedBase(spec, endpoint);

  public CheckedBase(RequestSpecification spec, Endpoint endpoint) {
    super(spec, endpoint);
  }

  @Override
  public T create(BaseModel model) {
    return (T) uncheckedBase
      .create(model)
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .as(endpoint.getModelClass());
  }

  @Override
  public T read(String id) {
    return (T) uncheckedBase
      .read(id)
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .as(endpoint.getModelClass());
  }

  @Override
  public T update(String id, BaseModel model) {
    return (T) uncheckedBase
      .update(id, model)
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .as(endpoint.getModelClass());
  }

  @Override
  public String delete(String id) {
    return uncheckedBase
      .delete(id)
      .then().assertThat().statusCode(HttpStatus.SC_OK)
      .extract().asString();
  }
}
