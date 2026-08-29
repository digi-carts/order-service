package com.digicart.order.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.digicart.order.exception.GlobalExceptionHandler;
import com.digicart.order.controller.HealthController;
import com.digicart.order.controller.OrderController;
import com.digicart.order.service.OrderService;

@CucumberContextConfiguration
@WebMvcTest(controllers = { HealthController.class, OrderController.class })
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class CucumberSpringConfiguration {
    @MockBean
    OrderService orderService;

}
