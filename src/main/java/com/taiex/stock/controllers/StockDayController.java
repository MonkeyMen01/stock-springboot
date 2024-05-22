package com.taiex.stock.controllers;

import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.services.StockDayService;
import com.taiex.stock.services.TaiexRequestService;
import com.taiex.stock.utils.GlobeLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.hc.core5.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/manual")
@Tag(name = "手動更新")
public class StockDayController extends GlobeLogger {

    private final StockDayService stockDayService;
    private final TaiexRequestService taiexRequestService;
    private  final String  SAVE_FAIL_MESSAGE ="Save All StockDay Data Fail";

    public StockDayController(StockDayService stockDayService, TaiexRequestService taiexRequestService) {
        this.stockDayService = stockDayService;
        this.taiexRequestService = taiexRequestService;
    }

    @Operation(summary = "更新今日個股資訊")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> getTodayStockDayData(){
        try{
            final ResponseEntity<ResponseStockDay> response = taiexRequestService.requestStockDay();
            final String result =  stockDayService.saveAll(response.getBody());
            final LocalDateTime now = LocalDateTime.now();
            logger.info(" Successful", now);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            logger.error("Scheduled Fail",e.getMessage());
            return new ResponseEntity(SAVE_FAIL_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
