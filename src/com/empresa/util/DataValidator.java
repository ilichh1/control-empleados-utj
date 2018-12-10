/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author ilichh1
 */
public class DataValidator {
    
    private static final String LOGIN = "wizard";
    private static final String PASSWORD = "toystore";
    
    public static boolean validateCredentials(String login, String password) {
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            ConsoleUtils.printSuccessMessage("Acceso correcto.");
            return true;
        } else {
            ConsoleUtils.printErrorMessage("Credenciales incorrectas.");
            return false;
        }
    }
    
    public static boolean validateName(String value) {
        if (value.length() < 2) return false;
        String regex = "^[\\p{L} .'-]+$";
        return Pattern.matches(regex, value);
    }
    
    public static boolean validateEmail(String value) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return Pattern.matches(regex, value);
    }
    
    
    public static boolean validateDayOfWeek(String[] dias) {
        ArrayList<String> validDays = new ArrayList<>(Arrays.asList(new String [] {
            "DOMINGO",
            "LUNES",
            "MARTES",
            "MIERCOLES",
            "MIÉRCOLES",
            "JUEVES",
            "VIERNES",
            "SABADO",
            "SÁBADO"
        }));
        return validDays.containsAll(Arrays.asList(dias));
    }
    
    public static boolean validateClientExists(int clientID) throws Exception {
        throw new Exception("Sin soporte aún.");
    }
}
