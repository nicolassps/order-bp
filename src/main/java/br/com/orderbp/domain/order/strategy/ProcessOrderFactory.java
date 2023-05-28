package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class ProcessOrderFactory {

    private final Map<OrderStatus, ProcessOrderStrategy> strategyMap = new HashMap<>();

    public ProcessOrderFactory(Set<ProcessOrderStrategy> strategies) {
        strategies.forEach(this::createStrategy);
    }

    void createStrategy(ProcessOrderStrategy strategy){
        strategyMap.put(strategy.compatibility(), strategy);
    }

    public void process(final Order order){
        Optional.ofNullable(strategyMap.get(order.getStatus()))
                .orElseThrow(IllegalStateException::new)
                .process(order);
    }
}
