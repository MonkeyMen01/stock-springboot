package com.taiex.stock.repository;

import com.taiex.stock.entities.StockDay;
import com.taiex.stock.entities.StockDayId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockDayRepository extends JpaRepository<StockDay, StockDayId>, JpaSpecificationExecutor<StockDay> {
}