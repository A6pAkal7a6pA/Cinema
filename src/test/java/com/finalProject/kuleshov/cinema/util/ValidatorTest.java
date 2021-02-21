package com.finalProject.kuleshov.cinema.util;

import com.finalProject.kuleshov.cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.cinema.entity.Seance;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void isValidTest() throws Exception {
        assertTrue(Validator.isValidLogin("login"));
        assertTrue(Validator.isValidPassword("password"));
        assertTrue(Validator.isValidEmail("email@email.com"));
        assertTrue(Validator.isValidPhoneNumber("+380951644454"));

    }


}