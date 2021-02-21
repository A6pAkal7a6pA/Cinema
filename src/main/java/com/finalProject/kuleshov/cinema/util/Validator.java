package com.finalProject.kuleshov.cinema.util;

public class Validator {
    public static boolean isValidPhoneNumber(String s) {
        String numberOnly = s.replaceAll("[^0-9]", "");
        String regex = "\\d{7,12}";
        return numberOnly.matches(regex);
    }

    public static boolean isValidLogin(String s) {
        String regex = "\\w{4,16}";
        return s.matches(regex);
    }

    public static boolean isValidPassword(String s) {
        String regex = "[A-Za-z0-9]{4,16}";
        return s.matches(regex);
    }

    public static boolean isValidEmail(String s) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+.+.[A-Za-z]{2,4}$";
        return s.matches(regex);
    }

}
