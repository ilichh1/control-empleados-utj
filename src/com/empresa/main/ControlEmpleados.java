/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.main;

import com.empresa.utilerias.menu.MenuController;

/**
 *
 * @author ilichh1
 */
public class ControlEmpleados {
    
    public static MenuController menuController = new MenuController();
    
    public static boolean FIN_DE_LA_EJECUCION = false;
    
    /**
     * @param args los comandos de línea de consola
     */
    
    public static void main(String[] args) {
        do {
            menuController.iniciarMenuController();
        } while(!FIN_DE_LA_EJECUCION);
    }
    
}
