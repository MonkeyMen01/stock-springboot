package com.taiex.stock.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class StockDayId implements Serializable {

    private static final long serialVersionUID = 7738274290460903153L;
    private LocalDate date;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockDayId entity = (StockDayId) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.code, entity.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, code);
    }

}