package com.taiex.stock.repository;

import com.taiex.stock.entities.StockDay;
import com.taiex.stock.entities.StockDayId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface StockDayRepository extends JpaRepository<StockDay, StockDayId>, JpaSpecificationExecutor<StockDay> {


    /**
     * 拉取最後日期
     * @return
     */
    @Query("select sd.date from StockDay sd order by sd.date desc  limit 1")
     LocalDate findLastDate();

}