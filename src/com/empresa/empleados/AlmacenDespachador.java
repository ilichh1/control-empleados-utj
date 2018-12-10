package com.empresa.empleados;

import com.empresa.abstracts.Empleado;
import com.empresa.util.ConsoleUtils;
import com.empresa.util.DataValidator;
import com.empresa.util.InterfazConsola;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ilichh1
 */
public class AlmacenDespachador extends Empleado {
    
    private String almacen;
    
    public AlmacenDespachador(String nombre, String apellidos, int edad, LocalDate fechaIngreso, String almacen) throws Exception {
        super(nombre, apellidos, edad, fechaIngreso, Empleado.ALMACENISTA);
        this.almacen = almacen;
    }
    
    public AlmacenDespachador() throws Exception {
        super();
        
        this.area = Empleado.ALMACENISTA;
        this.almacen = null;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void pedirDatos() {
        super.pedirDatos();
        
        for(String datoAPedir : this.nombreDeDatos()) {
            while (!this.pedirYValidarDato(datoAPedir));
        }
    }
    
    private boolean pedirYValidarDato(String nombreDato) {
        try {
            switch (nombreDato) {
                case "almacen":
                    System.out.print("¿En que almacen trabaja este empleado? ");
                    this.setAlmacen(ConsoleUtils.pedirString());
                break;
            }
        } catch (Exception ex) {
            InterfazConsola.imprimirMensajeDeError(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    private String[] nombreDeDatos() {
        return new String[] {
            "almacen"
        };
    }
    
    public void setAlmacen(String almacen) throws Exception {
        if(!DataValidator.validateName(almacen))
            throw new Exception("No es un nombre de almacén válido.");
        this.almacen = almacen;
    }
    
    public String getAlmacen() {
        return this.almacen;
    }
    
}
