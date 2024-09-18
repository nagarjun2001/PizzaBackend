package com.pizza.serviceimpl;

import com.pizza.model.Orders;
import com.pizza.repoimpl.OrdersRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrdersSerImplTest {

    @Mock
    private OrdersRepoImpl repo;

    @InjectMocks
    private OrdersSerImpl ordersSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrder() {
        Orders order = new Orders();
        ordersSerImpl.addOrder(order);
        verify(repo, times(1)).addOrder(order);
    }

    @Test
    void testGetOrderByCustomerId() {
        Long custid = 1L;
        Orders order = new Orders();
        when(repo.getOrderByCustomerId(custid)).thenReturn(order);

        Orders result = ordersSerImpl.getOrderByCustomerId(custid);
        verify(repo, times(1)).getOrderByCustomerId(custid);
        assertSame(order, result);
    }

    @Test
    void testGetAllOrder() {
        List<Orders> orders = Collections.singletonList(new Orders());
        when(repo.getAllOrder()).thenReturn(orders);

        List<Orders> result = ordersSerImpl.getAllOrder();
        verify(repo, times(1)).getAllOrder();
        assertEquals(orders, result);
    }

    @Test
    void testGetOrderByOrderId() {
        Long id = 1L;
        Orders order = new Orders();
        when(repo.getOrderByOrderId(id)).thenReturn(order);

        Orders result = ordersSerImpl.getOrderByOrderId(id);
        verify(repo, times(1)).getOrderByOrderId(id);
        assertSame(order, result);
    }

    @Test
    void testAssignStaffToOrder() {
        Long orderId = 1L;
        Long staffId = 2L;
        String result = "Assigned";
        when(repo.assignStaffToOrder(orderId, staffId)).thenReturn(result);

        String assignmentResult = ordersSerImpl.assignStaffToOrder(orderId, staffId);
        verify(repo, times(1)).assignStaffToOrder(orderId, staffId);
        assertEquals(result, assignmentResult);
    }

}
