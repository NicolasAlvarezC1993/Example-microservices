package com.formacionbdi.springboot.app.items.decoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHelper {

  static public ObjectMapper mapper;

  static public <T> T fromJSON(String json, Class<T> clazz) {
    try {
      return getMapper().readValue(json.getBytes("UTF-8"), clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  static public <T> T fromJSON(String json, TypeReference<T> typeReference) {
    try {
      return getMapper().readValue(json.getBytes("UTF-8"), typeReference);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  static public String toJSON(Object object) {
    try {

      return getMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  static private ObjectMapper getMapper() {
    if (mapper == null) {
      mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    return mapper;
  }

}
