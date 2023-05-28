package br.com.orderbp.domain.usecase;

import br.com.orderbp.domain.adapter.CreatedOrderAdapter;
import br.com.orderbp.domain.adapter.event.CreatedOrderEvent;
import br.com.orderbp.domain.helper.DiscountHelper;
import br.com.orderbp.domain.helper.ProductHelper;
import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.OrderItem;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidItemException;
import br.com.orderbp.domain.port.dto.CreateOrderDTO;
import br.com.orderbp.domain.port.dto.OrderItemDTO;
import br.com.orderbp.domain.repository.DiscountRepository;
import br.com.orderbp.domain.repository.OrderRepository;
import br.com.orderbp.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static br.com.orderbp.domain.order.OrderHelper.createSavedOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {
    @InjectMocks
    CreateOrderUseCase useCase;
    @Mock
    CreatedOrderAdapter createdOrderAdapter;
    @Mock
    OrderRepository repository;
    @Mock
    DiscountRepository discountRepository;
    @Mock
    ProductRepository productRepository;

    @Test
    void shouldCreateOrder(){
        var productIdentifier1 = "product1";
        var productIdentifier2 = "product2";

        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItemDTO1 = new OrderItemDTO(productIdentifier1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItemDTO2 = new OrderItemDTO(productIdentifier2, BigDecimal.TEN, BigDecimal.ONE);

        var expectedTotal = BigDecimal.valueOf(20);

        var savedOrder = createSavedOrder();
        var dto = new CreateOrderDTO("10000000000", null, Arrays.asList(orderItemDTO1, orderItemDTO2));

        when(productRepository.getByIdentifier(productIdentifier1)).thenReturn(product1);
        when(productRepository.getByIdentifier(productIdentifier2)).thenReturn(product2);
        doNothing().when(createdOrderAdapter).publish(any());
        when(repository.create(any())).thenReturn(savedOrder);

        useCase.create(dto);

        var orderCaptor = ArgumentCaptor.forClass(Order.class);
        var eventCaptor = ArgumentCaptor.forClass(CreatedOrderEvent.class);

        verify(repository).create(orderCaptor.capture());
        assertNull(orderCaptor.getValue().getId());
        assertEquals(orderCaptor.getValue().getStatus(), OrderStatus.CREATED);
        assertEquals(orderCaptor.getValue().totalValue(), expectedTotal);

        verify(createdOrderAdapter).publish(eventCaptor.capture());
        assertNotNull(eventCaptor.getValue());
        assertEquals(eventCaptor.getValue().getOrderId(), savedOrder.getId());

        verify(discountRepository, times(0)).findByCupon(any());
        verify(productRepository, times(2)).getByIdentifier(any());
    }

    @Test
    void shouldCreateOrderWithDiscount(){
        var productIdentifier1 = "product1";
        var productIdentifier2 = "product2";

        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItemDTO1 = new OrderItemDTO(productIdentifier1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItemDTO2 = new OrderItemDTO(productIdentifier2, BigDecimal.TEN, BigDecimal.ONE);

        var discount = DiscountHelper.createValidPercentDiscount();
        var expectedTotal = discount.apply(BigDecimal.valueOf(20));

        var savedOrder = createSavedOrder();
        var dto = new CreateOrderDTO("10000000000", "CUPOUM", Arrays.asList(orderItemDTO1, orderItemDTO2));

        when(productRepository.getByIdentifier(productIdentifier1)).thenReturn(product1);
        when(productRepository.getByIdentifier(productIdentifier2)).thenReturn(product2);
        when(discountRepository.findByCupon("CUPOUM")).thenReturn(discount);
        doNothing().when(createdOrderAdapter).publish(any());
        when(repository.create(any())).thenReturn(savedOrder);

        useCase.create(dto);

        var orderCaptor = ArgumentCaptor.forClass(Order.class);
        var eventCaptor = ArgumentCaptor.forClass(CreatedOrderEvent.class);

        verify(repository).create(orderCaptor.capture());
        assertNull(orderCaptor.getValue().getId());
        assertEquals(orderCaptor.getValue().getStatus(), OrderStatus.CREATED);
        assertEquals(orderCaptor.getValue().totalValue(), expectedTotal);

        verify(createdOrderAdapter).publish(eventCaptor.capture());
        assertNotNull(eventCaptor.getValue());
        assertEquals(eventCaptor.getValue().getOrderId(), savedOrder.getId());

        verify(discountRepository).findByCupon(any());
        verify(productRepository, times(2)).getByIdentifier(any());
    }

    @Test
    void shouldThrowItemExceptionOnCreateOrderWithInvalidQuantityProduct(){
        var productIdentifier1 = "product1";
        var product1 = ProductHelper.createValidProduct();
        var orderItemDTO1 = new OrderItemDTO(productIdentifier1, BigDecimal.valueOf(-10), BigDecimal.ONE);

        var dto = new CreateOrderDTO("10000000000", "CUPOUM", List.of(orderItemDTO1));

        when(productRepository.getByIdentifier(productIdentifier1)).thenReturn(product1);

        assertThrows(InvalidItemException.class, () -> useCase.create(dto));

        verify(repository, times(0)).create(any());
        verify(createdOrderAdapter, times(0)).publish(any());
        verify(productRepository, times(1)).getByIdentifier(any());
    }

    @Test
    void shouldThrowItemExceptionOnCreateOrderWithInvalidValueProduct(){
        var productIdentifier1 = "product1";
        var product1 = ProductHelper.createValidProduct();
        var orderItemDTO1 = new OrderItemDTO(productIdentifier1, BigDecimal.TEN, BigDecimal.valueOf(-10));

        var dto = new CreateOrderDTO("10000000000", "CUPOUM", List.of(orderItemDTO1));

        when(productRepository.getByIdentifier(productIdentifier1)).thenReturn(product1);

        assertThrows(InvalidItemException.class, () -> useCase.create(dto));

        verify(repository, times(0)).create(any());
        verify(createdOrderAdapter, times(0)).publish(any());
        verify(productRepository, times(1)).getByIdentifier(any());
    }

}