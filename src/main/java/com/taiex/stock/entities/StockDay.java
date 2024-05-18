package com.taiex.stock.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "stock_day")
@IdClass(StockDayId.class)
@Schema(description = "個股資訊")
public class StockDay {
    @Schema(description = "股票代碼")
    @Id
    @Column(name = "code",nullable = false,length = 50)
    private String code;

    @Schema(description = "日期")
    @Id
    @Column(name = "date",nullable = false)
    private LocalDate date;

    @Schema(description = "股票名稱")
    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Schema(description = "成交金額")
    @NotNull
    @Column(name = "trade_value", nullable = false)
    private Long tradeValue;

    @Schema(description = "成交股數")
    @NotNull
    @Column(name = "trade_volume", nullable = false)
    private Long tradeVolume;

    @Schema(description = "開盤價")
    @NotNull
    @Column(name = "opening_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal openingPrice;

    @Schema(description = "最高價")
    @NotNull
    @Column(name = "highest_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal highestPrice;


    @Schema(description = "最低價")
    @NotNull
    @Column(name = "lowest_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal lowestPrice;

    @Schema(description = "收盤價")
    @NotNull
    @Column(name = "closing_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal closingPrice;

    @Schema(description = "漲跌價差")
    @Size(max = 50)
    @NotNull
    @Column(name = "change_price", nullable = false, length = 50)
    private String changePrice;

    @Schema(description = "成交筆數")
    @NotNull
    @Column(name = "transaction", nullable = false)
    private Integer transaction;

}