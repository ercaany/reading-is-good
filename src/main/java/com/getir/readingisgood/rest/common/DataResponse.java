package com.getir.readingisgood.rest.common;

import java.util.List;

public record DataResponse<T>(List<T> itemList) {
}
