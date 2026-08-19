package com.digicart.order.service;

import com.digicart.order.dto.OrderItemRequest;
import com.digicart.order.dto.OrderRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import com.digicart.order.exception.EntityNotFoundException;
import com.digicart.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void findByIdThrowsWhenMissing() {
        when(orderRepository.findById("x")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> orderService.findById("x")).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createAttachesLineItems() {
        OrderRequest req = new OrderRequest();
        req.setStoreId("s1");
        req.setUserId("u1");
        req.setTotal(99.0);
        req.setShippingAddress("{\"city\":\"Pune\"}");
        OrderItemRequest item = new OrderItemRequest();
        item.setProductId("p1");
        item.setProductName("Mug");
        item.setQty(2);
        item.setPriceAtOrder(49.5);
        req.setItems(List.of(item));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order order = orderService.create(req);
        assertThat(order.getItems()).hasSize(1);
        assertThat(order.getItems().get(0).getProductName()).isEqualTo("Mug");
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
    }
}
