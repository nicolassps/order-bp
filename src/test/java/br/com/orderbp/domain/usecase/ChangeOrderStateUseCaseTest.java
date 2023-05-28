package br.com.orderbp.domain.usecase;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.OrderHelper;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import br.com.orderbp.domain.order.strategy.ProcessOrderFactory;
import br.com.orderbp.domain.port.dto.ChangeOrderStateDTO;
import br.com.orderbp.domain.port.dto.CreateOrderDTO;
import br.com.orderbp.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.REFUSED;
import static br.com.orderbp.domain.order.enumeration.OrderStatus.RESERVED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeOrderStateUseCaseTest {

    @InjectMocks
    ChangeOrderStateUseCase useCase;

    @Mock
    OrderRepository repository;

    @Mock
    ProcessOrderFactory processOrderFactory;

    @Test
    void shouldProcess(){
        var order = OrderHelper.createOrderWithCreatedStatus();
        var dto = new ChangeOrderStateDTO(order.getId());

        when(repository.findById(anyLong())).thenReturn(order);

        useCase.execute(dto);

        verify(repository).update(any());
    }

    @Test
    void shouldRefuseOrderOnInvalidChangeState(){
        var order = OrderHelper.createOrderWithCreatedStatus();
        var dto = new ChangeOrderStateDTO(order.getId());

        when(repository.findById(anyLong())).thenReturn(order);
        doThrow(new InvalidChangeStateException("INVALID STATE")).when(processOrderFactory).process(any());

        useCase.execute(dto);

        var captor = ArgumentCaptor.forClass(Order.class);
        verify(repository).update(captor.capture());
        assertEquals(REFUSED, order.getStatus());
        assertEquals("INVALID STATE", order.getRefuseReason());
    }
}