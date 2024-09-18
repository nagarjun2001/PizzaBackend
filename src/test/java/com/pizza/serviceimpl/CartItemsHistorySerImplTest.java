package com.pizza.serviceimpl;

import com.pizza.model.CartItemsHistory;
import com.pizza.repoimpl.CartItemsHistoryRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CartItemsHistorySerImplTest {

    @Mock
    private CartItemsHistoryRepoImpl historyRepository;

    @InjectMocks
    private CartItemsHistorySerImpl historySerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSnapshot() {
        historySerImpl.saveSnapshot(1L, 2L, 100.0, 5L, true, 3L);
        verify(historyRepository, times(1)).saveSnapshot(1L, 2L, 100.0, 5L, true, 3L);
    }

    @Test
    void testGetAllSnapshotsByCustomerId() {
        List<CartItemsHistory> historyList = Collections.singletonList(new CartItemsHistory());
        when(historyRepository.findAllByCustomerId(3L)).thenReturn(historyList);
        
        List<CartItemsHistory> result = historySerImpl.getAllSnapshotsByCustomerId(3L);
        verify(historyRepository, times(1)).findAllByCustomerId(3L);
        assertSame(historyList, result);
    }

    @Test
    void testGetSnapshotById() {
        CartItemsHistory history = new CartItemsHistory();
        when(historyRepository.findById(1L)).thenReturn(history);
        
        CartItemsHistory result = historySerImpl.getSnapshotById(1L);
        verify(historyRepository, times(1)).findById(1L);
        assertSame(history, result);
    }


    @Test
    void testFindByCartId() {
        List<CartItemsHistory> historyList = Collections.singletonList(new CartItemsHistory());
        when(historyRepository.findByCartId(2L)).thenReturn(historyList);
        
        List<CartItemsHistory> result = historySerImpl.findByCartId(2L);
        verify(historyRepository, times(1)).findByCartId(2L);
        assertSame(historyList, result);
    }
}
