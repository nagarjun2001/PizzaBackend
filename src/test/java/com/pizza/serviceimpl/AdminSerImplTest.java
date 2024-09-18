package com.pizza.serviceimpl;

import com.pizza.model.Admin;
import com.pizza.model.Staff;
import com.pizza.repo.AdminRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AdminSerImplTest {

    @Mock
    private AdminRepo adminRepo;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private AdminSerImpl adminSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAdmin() {
        Admin admin = new Admin();
        adminSerImpl.addAdmin(admin);
        verify(adminRepo, times(1)).addAdmin(admin);
    }

    @Test
    void testGetAdminByUsername() {
        String username = "testUser";
        Admin admin = new Admin();
        when(adminRepo.getAdminByUsername(username)).thenReturn(admin);
        Admin result = adminSerImpl.getAdminByUsername(username);
        verify(adminRepo, times(1)).getAdminByUsername(username);
        assertSame(admin, result);
    }

    @Test
    void testAddStaff() {
        Staff staff = new Staff();
        adminSerImpl.addStaff(staff);
        verify(adminRepo, times(1)).addStaff(staff);
    }

    @Test
    void testSendCredentialsMail() {
        String to = "staff@example.com";
        String staffName = "John Doe";
        String orgemail = "johndoe@pizzaman.com";
        String role = "manager";
        String plainPassword = "password123";

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(to);
        expectedMessage.setSubject("PizzaMan Staff Account Credentials");
        expectedMessage.setText("Dear " + staffName + ",\n\n" +
                "We are pleased to inform you that a new account has been created for you. Your designated position is " + role.toUpperCase() + ".\n\n" +
                "To access your account, please use the following credentials:\n" +
                "Organization Email: " + orgemail + "\n" +
                "Temporary Password: " + plainPassword + "\n" +
                "Welcome to PizzaMan! We look forward to your contributions and wish you success in your new role.\n\n" +
                "Best regards,\n" +
                "The PizzaMan Team");

        adminSerImpl.sendCredentialsMail(to, staffName, orgemail, role, plainPassword);
        verify(mailSender, times(1)).send(expectedMessage);
    }
}
