package com.pizza.serviceimpl;

import com.pizza.model.Inventory;
import com.pizza.repoimpl.InventoryRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventorySerImplTest {

    @Mock
    private InventoryRepoImpl repo;

    @InjectMocks
    private InventorySerImpl inventorySerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStock() {
        Inventory inventory = new Inventory();
        inventorySerImpl.addStock(inventory);
        verify(repo, times(1)).addStock(inventory);
    }

    @Test
    void testUpdateStock() {
        Inventory inventory = new Inventory();
        inventorySerImpl.updateStock(inventory);
        verify(repo, times(1)).updateStock(inventory);
    }

    @Test
    void testDeleteStock() {
        Long id = 1L;
        inventorySerImpl.deleteStock(id);
        verify(repo, times(1)).deleteStock(id);
    }

    @Test
    void testGetStockById() {
        Long id = 1L;
        Inventory inventory = new Inventory();
        when(repo.getStockById(id)).thenReturn(inventory);

        Inventory result = inventorySerImpl.getStockById(id);
        verify(repo, times(1)).getStockById(id);
        assertSame(inventory, result);
    }

    @Test
    void testGetStockList() {
        List<Inventory> inventories = Collections.singletonList(new Inventory());
        when(repo.getStockList()).thenReturn(inventories);

        List<Inventory> result = inventorySerImpl.getStockList();
        verify(repo, times(1)).getStockList();
        assertEquals(inventories, result);
    }

}
