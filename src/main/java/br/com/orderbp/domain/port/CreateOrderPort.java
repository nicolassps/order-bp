package br.com.orderbp.domain.port;

import br.com.orderbp.domain.port.dto.CreateOrderDTO;

public interface CreateOrderPort {

    void create(CreateOrderDTO createOrderDTO);

}
