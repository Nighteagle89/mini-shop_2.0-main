package com.anton.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Integer amount;
    private double summa;
    private List<ItemDTO> items = new ArrayList<>();

    public void aggregate() {
        this.amount = items.size();
        this.summa = items.stream().map(ItemDTO::getPrice)
                .mapToDouble(BigDecimal::doubleValue).sum();
        System.out.println("summa: " + summa);
    }
}
