package br.com.orderbp.domain.usecase;

import br.com.orderbp.domain.adapter.CreatedOrderAdapter;
import br.com.orderbp.domain.adapter.event.CreatedOrderEvent;
import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.OrderItem;
import br.com.orderbp.domain.port.CreateOrderPort;
import br.com.orderbp.domain.port.dto.CreateOrderDTO;
import br.com.orderbp.domain.port.dto.OrderItemDTO;
import br.com.orderbp.domain.repository.DiscountRepository;
import br.com.orderbp.domain.repository.OrderRepository;
import br.com.orderbp.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase implements CreateOrderPort {

    private final CreatedOrderAdapter createdOrderAdapter;
    private final OrderRepository repository;
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    @Override
    public void create(final CreateOrderDTO createOrderDTO) {
        final var builder = Order.builder().withConsumerDocument(createOrderDTO.getConsumerDocument());

        createOrderDTO
                .getItems()
                .stream()
                .map(this::createItem)
                .forEach(builder::withItem);

        if(nonNull(createOrderDTO.getDiscountCoupon())){
            final var discount = discountRepository.findByCupon(createOrderDTO.getDiscountCoupon());
            builder.withDiscount(discount);
        }

        final var order = builder.build();
        final var savedOrder = repository.create(order);

        createdOrderAdapter.publish(CreatedOrderEvent.fromOrder(savedOrder));
    }

    OrderItem createItem(final OrderItemDTO itemDTO){
        return OrderItem
                .builder()
                .withProduct(productRepository.getByIdentifier(itemDTO.getProductIdentifier()))
                .withQuantity(itemDTO.getQuantity())
                .withValue(itemDTO.getValue())
                .build();
    }
}
