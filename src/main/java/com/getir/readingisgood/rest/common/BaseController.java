package com.getir.readingisgood.rest.common;

import java.util.List;

public class BaseController {

  public <T> ApiResponse<T> respond(T data) {
    return ResponseBuilder.build(data);
  }

  public <T> ApiResponse<DataResponse<T>> respond(List<T> list) {
    return ResponseBuilder.build(list);
  }

  public <T> ApiResponse<PagedDataResponse<T>> respond(List<T> list, int page, int pageSize) {
    return ResponseBuilder.build(list, page, pageSize);
  }

  public <T> ApiResponse<ErrorResponse> respond(ErrorResponse errorResponse) {
    return ResponseBuilder.build(errorResponse);
  }
}
