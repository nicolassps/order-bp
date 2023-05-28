package br.com.orderbp.domain.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeOrderStateDTO {
    private Long orderId;
}
