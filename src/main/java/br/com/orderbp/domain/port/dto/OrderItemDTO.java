package br.com.orderbp.domain.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private String productIdentifier;
    private BigDecimal quantity;
    private BigDecimal value;
}
