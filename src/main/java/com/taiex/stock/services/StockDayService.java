package com.taiex.stock.services;

import com.taiex.stock.entities.StockDay;
import com.taiex.stock.entities.response.ResponseStockDay;
import com.taiex.stock.repository.StockDayRepository;
import com.taiex.stock.utils.Functions;
import com.taiex.stock.utils.GlobeLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.taiex.stock.utils.Functions.cleanDecimal;
import static com.taiex.stock.utils.Functions.formatDate;

@Service
public class StockDayService extends GlobeLogger {
    private final StockDayRepository stockDayRepository;
    private static final String SAVE_SUCCESS_MESSAGE = "Save All StockDay Data Successfully";
    private static final String ALREADY_DATA_MESSAGE = "Data Already Exist";

    public StockDayService(StockDayRepository stockDayRepository) {
        this.stockDayRepository = stockDayRepository;
    }

    @Transactional
    public String saveAll(final ResponseStockDay responseStockDay) {
        List<StockDay> stockDayList = new ArrayList<>();
        final LocalDate lastDate = stockDayRepository.findLastDate();

        if (formatDate(responseStockDay.getDate()).equals(lastDate)) {
            return ALREADY_DATA_MESSAGE;
        }

        responseStockDay.getData().forEach(item -> {
            try {
                StockDay stockDay = new StockDay();
                stockDay.setCode(item.get(0).trim());
                stockDay.setDate(formatDate(responseStockDay.getDate()));
                stockDay.setName(item.get(1).trim());
                stockDay.setTradeVolume(Long.parseLong(item.get(2).replace(",", "")));
                stockDay.setTradeValue(Long.parseLong(item.get(3).replace(",", "")));
                stockDay.setOpeningPrice(cleanDecimal(item.get(4)));
                stockDay.setHighestPrice(cleanDecimal(item.get(5)));
                stockDay.setLowestPrice(cleanDecimal(item.get(6)));
                stockDay.setClosingPrice(cleanDecimal(item.get(7)));
                stockDay.setChangePrice(item.get(8).replace(",", ""));
                stockDay.setTransaction(Integer.parseInt(item.get(9).replace(",", "")));

                stockDayList.add(stockDay);
            } catch (NumberFormatException e) {
                logger.error("Error parsing data for stock day: {}", item, e);
            }
        });
        stockDayRepository.saveAll(stockDayList);
        return SAVE_SUCCESS_MESSAGE;
    }

}
