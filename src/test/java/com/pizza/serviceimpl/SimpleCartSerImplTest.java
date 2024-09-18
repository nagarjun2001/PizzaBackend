package com.pizza.serviceimpl;

import com.pizza.model.Cart;
import com.pizza.repoimpl.SimpleCartRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCartSerImplTest {

    @Mock
    private SimpleCartRepoImpl repo;

    @InjectMocks
    private SimpleCartSerImpl simpleCartSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCart() {
        Cart cart = new Cart();
        simpleCartSerImpl.saveCart(cart);
        verify(repo, times(1)).saveCart(cart);
    }

    @Test
    void testUpdateCart() {
        Cart cart = new Cart();
        simpleCartSerImpl.updateCart(cart);
        verify(repo, times(1)).updateCart(cart);
    }

    @Test
    void testGetCartById() {
        Long id = 1L;
        Cart cart = new Cart();
        when(repo.getCartById(id)).thenReturn(cart);

        Cart result = simpleCartSerImpl.getCartById(id);
        verify(repo, times(1)).getCartById(id);
        assertSame(cart, result);
    }

    @Test
    void testGetCartByCustId() {
        Long custid = 1L;
        Cart cart = new Cart();
        when(repo.getCartByCustId(custid)).thenReturn(cart);

        Cart result = simpleCartSerImpl.getCartByCustId(custid);
        verify(repo, times(1)).getCartByCustId(custid);
        assertSame(cart, result);
    }

    @Test
    void testDeleteCart() {
        Long id = 1L;
        simpleCartSerImpl.deleteCart(id);
        verify(repo, times(1)).deleteCart(id);
    }
}
