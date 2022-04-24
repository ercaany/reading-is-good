package com.getir.readingisgood.rest.common;

import java.util.List;

public record PagedDataResponse<T>(List<T> itemList, Integer page, Integer size) {
}
