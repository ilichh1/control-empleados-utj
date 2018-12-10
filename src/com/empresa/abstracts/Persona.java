/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.abstracts;

import com.empresa.util.InterfazConsola;
import com.empresa.util.ValidadorDeDatos;
import java.util.ArrayList;

/**
 *
 * @author ilichh1
 */
public abstract class Persona {
    
    private String nombres;
    private String apellidos;
    private int edad;
    
    
    public Persona (String nombre, String apellidos, int edad) {
        this.nombres = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    
    public Persona () {
        this.nombres = null;
        this.apellidos = null;
        this.edad = -1;
    }
    
    public String getNombreCompleto() {
        return this.getNombres() + " " + this.getApellidos();
    }
    
    @SuppressWarnings("empty-statement")
    public void pedirDatos() {
//        TODO: Definir si se vuelve a diseñar la manera de pedir datos o sea crea
//        objeto exclusivo para pedir datos
        for(String datoAPedir : this.nombreDeDatos()) {
            while(!this.pedirYValidarDato(datoAPedir));
        }
    }
    
    private boolean pedirYValidarDato(String nombreDato) {
        try {
            switch(nombreDato) {
                case "nombres":
                    System.out.print("Ingrese nombre: ");
                    this.setNombres(InterfazConsola.LECTOR_TECLADO.nextLine());
                break;
                case "apellidos":
                    System.out.print("Ingrese apellidos: ");
                    this.setApellidos(InterfazConsola.LECTOR_TECLADO.nextLine());
                break;
                case "edad":
                    System.out.print("Edad (Mayor de 18): ");
                    this.setEdad(Integer.parseInt(InterfazConsola.LECTOR_TECLADO.nextLine()));
                break;
                default:
                    InterfazConsola.imprimirMensajeDeError("No se especificó un valor a pedir");
            }
        } catch (Exception ex) {
            InterfazConsola.imprimirMensajeDeError(ex.getMessage());
            return false;
        }

        return true;
    }
    
    private String[] nombreDeDatos() {
        ArrayList<String> datosSinInicializar = new ArrayList<>();
        
        if (this.nombres == null)
            datosSinInicializar.add("nombres");
        
        if(this.apellidos == null)
            datosSinInicializar.add("apellidos");
        
        if (this.edad == -1)
            datosSinInicializar.add("edad");
        
        return datosSinInicializar.toArray(new String[0]);
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) throws java.lang.Exception {
        if (!ValidadorDeDatos.validarNombre(nombres))
            throw new Exception("El nombre es incorrecto.");
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws java.lang.Exception {
        if (!ValidadorDeDatos.validarNombre(apellidos))
            throw new Exception("El apellido es incorrecto.");
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws java.lang.Exception {
        if (!ValidadorDeDatos.validarEdad(edad))
            throw new Exception("La edad no es válida.");
        this.edad = edad;
    }
}
