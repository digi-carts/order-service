package com.digicart.order.cucumber;

import com.digicart.order.entity.Order;
import com.digicart.order.service.OrderService;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.when;

public class OrderStepDefinitions {
    @Autowired
    OrderService orderService;

    @Before
    public void stubs() {
        when(orderService.findAll()).thenReturn(List.of(new Order()));
    }
}
