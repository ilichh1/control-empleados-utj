/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.util;

import com.empresa.abstracts.Empleado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author ilichh1
 */
public class ValidadorDeDatos {
    
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "empleado0";
    
    public static boolean validarAcceso(String login, String password) {
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            InterfazConsola.imprimirMensajeDeExito("Acceso correcto.");
            return true;
        } else {
            InterfazConsola.imprimirMensajeDeError("Usuario y/o contraseña invalidos. Por favor intente de nuevo.");
            return false;
        }
    }
    
    public static boolean validarNombre(String valor) {
        String regexValidador = "^[\\p{L} .'-]+$";
        return Pattern.matches(regexValidador, valor);
    }
    
    public static boolean validarEdad(int edad) {
        return edad >= 18;
    }
    
    public static boolean validarAreaDeTrabajo(int area) {
        Integer[] arregloDeAreas = new Integer[] {
            Empleado.ADMINISTRADOR,
            Empleado.ALMACENISTA,
            Empleado.ASEO,
            Empleado.DESPACHADOR,
            Empleado.GERENTE,
            Empleado.SUPERVISOR
        };
        ArrayList<Integer> areasValidas = new ArrayList<>(Arrays.asList(arregloDeAreas));
        return areasValidas.contains(area);
    }
    
    public static boolean validarDiaDeLaSemana(String[] dias) {
        ArrayList<String> diasValidos = new ArrayList<>(Arrays.asList(new String [] {
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
        return diasValidos.containsAll(Arrays.asList(dias));
    }
}
