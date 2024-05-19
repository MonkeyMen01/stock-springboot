package com.taiex.stock.services;

import com.taiex.stock.entities.StockDay;
import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.repository.StockDayRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TaiexRequestService {

    @Value("${taiex.stock.days.url}")
    private String stockRequestUrl;
    private StockDayRepository stockDayRepository;
    private RestTemplate restTemplate;

    public TaiexRequestService(StockDayRepository stockDayRepository,RestTemplate restTemplate) {
        this.stockDayRepository = stockDayRepository;
        this.restTemplate = restTemplate;
    }

    public void requestStockDay() {
        String url = UriComponentsBuilder.fromHttpUrl(stockRequestUrl).build().toUriString();
        try {
            ResponseEntity<ResponseStockDay> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseStockDay.class);
            System.out.println(response);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

}
