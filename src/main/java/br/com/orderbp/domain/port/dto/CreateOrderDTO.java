package br.com.orderbp.domain.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderDTO {
    private String consumerDocument;

    private List<OrderItemDTO> items;
}
