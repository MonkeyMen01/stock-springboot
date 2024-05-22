package com.taiex.stock.entities.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseStockDay {
    String state;
    String date;
    String title;
    List<String> fields = new ArrayList();
    List<List<String>> data = new ArrayList(new ArrayList<>());

}
