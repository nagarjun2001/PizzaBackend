package com.pizza.serviceimpl;

import com.pizza.model.Customer;
import com.pizza.repoimpl.CustomerRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerSerImplTest {

    @Mock
    private CustomerRepoImpl repo;

    @InjectMocks
    private CustomerSerImpl customerSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customerSerImpl.saveCustomer(customer);
        verify(repo, times(1)).saveCustomer(customer);
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customerSerImpl.updateCustomer(customer);
        verify(repo, times(1)).updateCustomer(customer);
    }

}
