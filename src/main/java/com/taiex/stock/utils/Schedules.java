package com.taiex.stock.utils;

import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.services.StockDayService;
import com.taiex.stock.services.TaiexRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Schedules extends GlobeLogger {
    private final StockDayService stockDayService;
    private final TaiexRequestService taiexRequestService;

    public Schedules(StockDayService stockDayService, TaiexRequestService taiexRequestService) {
        this.stockDayService = stockDayService;
        this.taiexRequestService = taiexRequestService;
    }


//        @Scheduled(cron = "0 44 14 * * ?")
    @Scheduled(cron = "0 0 23 ? * MON-FRI")
    private void stockDayScheduled() {
        try {
            final ResponseEntity<ResponseStockDay> response = taiexRequestService.requestStockDay();
            final String result = stockDayService.saveAll(response.getBody());
            logger.info("Scheduled Save:{}", result);
        } catch (Exception e) {
            logger.error("Scheduled Fail:{}", e.getMessage());
        }
    }
}
