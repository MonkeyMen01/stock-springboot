package com.taiex.stock.services;

import com.taiex.stock.entities.StockDay;
import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.repository.StockDayRepository;
import com.taiex.stock.utils.Functions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.taiex.stock.utils.Functions.cleanDecimal;
import static com.taiex.stock.utils.Functions.formatDate;

@Service
public class StockDayService {
    private StockDayRepository stockDayRepository;

    public StockDayService(StockDayRepository stockDayRepository) {
        this.stockDayRepository = stockDayRepository;
    }

    @Transactional
    public  void saveAll(ResponseStockDay responseStockDay) {
        List<StockDay> stockDayList = new ArrayList<>();
        responseStockDay.getData().forEach(item -> {
            StockDay stockDay = new StockDay();
            stockDay.setCode(item.get(0).trim());
            stockDay.setDate(formatDate(responseStockDay.getDate()));
            stockDay.setName(item.get(1).trim());
            stockDay.setTradeVolume((long) Integer.parseInt(item.get(2)));
            stockDay.setTradeValue((long) Integer.parseInt(item.get(3)));
            stockDay.setOpeningPrice(cleanDecimal(item.get(4)));
            stockDay.setHighestPrice(cleanDecimal(item.get(5)));
            stockDay.setLowestPrice(cleanDecimal(item.get(6)));
            stockDay.setClosingPrice(cleanDecimal(item.get(7)));
            stockDay.setChangePrice(item.get(8).replace(",", ""));
            stockDay.setTransaction(Integer.parseInt(item.get(9).replace(",","")));
        });
    }
}