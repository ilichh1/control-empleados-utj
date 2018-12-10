/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.util;

/**
 *
 * @author ilichh1
 */
public class ConsolaMenus {
    
    
    private static void imprimirMenuConFormato(EntradaMenu[] opcionesMenu) {
        int longestOptionChar = 0;
        
        for (EntradaMenu opcionMenu : opcionesMenu) {
            if (opcionMenu.opcionMenu.length() > longestOptionChar)
                longestOptionChar = opcionMenu.opcionMenu.length();
        }
        
        // Un espacio al final de la opcion, 3 lugres para los '...'
        // Y otro espacio para separar la palabra de la opcion
        longestOptionChar += 5;
        
        
        
    }
    
    private static void fillWithChar(int availableSpace, char charToFill) {
        
    }
    
    public static void imprimirMenuInicio() {
        EntradaMenu[] opcionesMenuInicio = new EntradaMenu[] {
            new EntradaMenu("CONSULTAR EMPLEADOS", 'A'),
            new EntradaMenu("REGISTRAR NUEVO EMPLEADO", 'B'),
            new EntradaMenu("EDITAR EMPLEADO EXISTENTE", 'C'),
            new EntradaMenu("ELIMINAR EMPLEADO", 'D'),
        };
        
        imprimirMenuConFormato(opcionesMenuInicio);
    }
    
}