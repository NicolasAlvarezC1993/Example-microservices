package com.formacionbdi.springboot.app.items.decoder;

import feign.Response;
import feign.Response.Body;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String s, Response response) {
    Body body = response.body();
    try (Reader reader = body.asReader()) {
      ErrorBody errorBody = JSONHelper.fromJSON(IOUtils.toString(reader), ErrorBody.class);
      return new BusinessException(errorBody.getMessage());
//      return new ResponseStatusException(HttpStatus.valueOf(response.status()),
//              errorBody != null ? errorBody.getMessage() : "Server Error");
    } catch (Exception e) {
      return new ResponseStatusException(HttpStatus.valueOf(response.status()),
          "Unexpected");
    }
  }


}
