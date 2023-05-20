package br.com.orderbp.domain.order;

import br.com.orderbp.domain.discount.Discount;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidOperationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Setter(AccessLevel.PACKAGE)
public class Order {
    private Long id;
    @Getter
    private String consumerDocument;
    private List<OrderItem> items;
    private Discount discount;
    @Getter
    private OrderStatus status;

    public void acceptItem(OrderItem item){
        if (isEmpty(items)){
            items = new ArrayList<>();
        }

        var alreadyExist = items.stream()
                .anyMatch(i -> i.getProduct().getIdentifier().equals(item.getProduct().getIdentifier()));

        if (alreadyExist) {
            final var alreadyItem = items.stream()
                    .filter(i -> i.getProduct().getIdentifier().equals(item.getProduct().getIdentifier()))
                    .filter(i -> i.getValue().equals(item.getValue()))
                    .findFirst()
                    .orElseThrow(InvalidOperationException::new);

            alreadyItem.add(item.getQuantity());
            return;
        }

        this.items.add(item);
    }

    public BigDecimal totalValue(){
        var total = items.stream()
                .map(OrderItem::totalPrice)
                .reduce(BigDecimal::add)
                .orElseThrow(InvalidOperationException::new);

        if (nonNull(discount)){
            return discount.apply(total);
        }

        return total;
    }

    public BigDecimal totalQuantity(){
        return items.stream()
                .map(OrderItem::getQuantity)
                .reduce(BigDecimal::add)
                .orElseThrow(InvalidOperationException::new);
    }

    public Integer distinctItems(){
        return items.size();
    }

    public static OrderBuilder builder(){
        return new OrderBuilder();
    }
}
