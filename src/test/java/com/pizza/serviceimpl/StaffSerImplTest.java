package com.pizza.serviceimpl;

import com.pizza.model.Staff;
import com.pizza.repo.StaffRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StaffSerImplTest {

    @Mock
    private StaffRepo repo;

    @InjectMocks
    private StaffSerImpl staffSerImpl;

    @Mock
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStaffById() {
        Long id = 1L;
        Staff staff = new Staff();
        when(repo.getStaffById(id)).thenReturn(staff);

        Staff result = staffSerImpl.getStaffById(id);
        verify(repo, times(1)).getStaffById(id);
        assertSame(staff, result);
    }


    @Test
    void testGetAllStaffs() {
        List<Staff> staffList = Collections.singletonList(new Staff());
        when(repo.getAllStaffs()).thenReturn(staffList);

        List<Staff> result = staffSerImpl.getAllStaffs();
        verify(repo, times(1)).getAllStaffs();
        assertEquals(staffList, result);
    }

    @Test
    void testUpdateStaff() {
        Staff staff = new Staff();
        when(repo.updateStaff(staff)).thenReturn(staff);

        Staff result = staffSerImpl.updateStaff(staff);
        verify(repo, times(1)).updateStaff(staff);
        assertSame(staff, result);
    }
}
