package com.pizza.serviceimpl;

import com.pizza.model.CartItems;
import com.pizza.repoimpl.SimCartItemsRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SimCartItemsSerImplTest {

    @Mock
    private SimCartItemsRepoImpl repo;

    @InjectMocks
    private SimCartItemsSerImpl simCartItemsSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CartItems cartItems = new CartItems();
        simCartItemsSerImpl.save(cartItems);
        verify(repo, times(1)).save(cartItems);
    }

    @Test
    void testUpdate() {
        CartItems cartItems = new CartItems();
        simCartItemsSerImpl.update(cartItems);
        verify(repo, times(1)).update(cartItems);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        simCartItemsSerImpl.delete(id);
        verify(repo, times(1)).delete(id);
    }

}
