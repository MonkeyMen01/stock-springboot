package com.taiex.stock.services;

import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.repository.StockDayRepository;
import com.taiex.stock.utils.GlobeLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TaiexRequestService extends GlobeLogger {

    @Value("${taiex.stock.days.url}")
    private static String stockRequestUrl;
    private final RestTemplate restTemplate;

    public TaiexRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 對個股請求
     * @return
     */
    public ResponseEntity<ResponseStockDay> requestStockDay() {
        try{
            final String url = UriComponentsBuilder.fromHttpUrl(stockRequestUrl).build().toUriString();
            final ResponseEntity<ResponseStockDay> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseStockDay.class);
            return response;
        }catch (RestClientException e){
            logger.error("Get StockDay API Fail",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
