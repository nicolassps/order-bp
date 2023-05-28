package br.com.orderbp.domain.usecase;

import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import br.com.orderbp.domain.order.strategy.ProcessOrderFactory;
import br.com.orderbp.domain.port.ChangeOrderStatePort;
import br.com.orderbp.domain.port.dto.ChangeOrderStateDTO;
import br.com.orderbp.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeOrderStateUseCase implements ChangeOrderStatePort {

    private final OrderRepository repository;

    private final ProcessOrderFactory processOrderFactory;

    @Override
    public void execute(ChangeOrderStateDTO changeOrderStateDTO) {
        var order = repository.findById(changeOrderStateDTO.getOrderId());

        try {
            processOrderFactory.process(order);
        } catch (InvalidChangeStateException e) {
            order.refuse(e.getLocalizedMessage());
        }

        repository.update(order);
    }
}
