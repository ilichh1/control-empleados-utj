/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.empleados;
import com.empresa.abstracts.Empleado;
import com.empresa.util.InterfazConsola;
import com.empresa.util.ValidadorDeDatos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public class Aseo extends Empleado {
    
    private ArrayList<String> diasDeAseo;
    
    public Aseo(String nom, String ap, int edad, LocalDate fechaIngreso, String[] diasDeAseo) throws Exception {
        super(nom, ap, edad, fechaIngreso, Empleado.ASEO);
        
        this.diasDeAseo = new ArrayList<>(Arrays.asList(diasDeAseo));
    }
    
    public Aseo() {
        super();
        
        this.area = Empleado.ASEO;
        this.diasDeAseo = new ArrayList<>();
    }

    public ArrayList<String> getDiasDeAseo() {
        return diasDeAseo;
    }

    public void setDiasDeAseo(String[] diasDeAseo) throws java.lang.Exception {
        if (diasDeAseo.length == 0)
            throw new Exception("No introdujo ningún día de aseo.");
        if (!ValidadorDeDatos.validarDiaDeLaSemana(diasDeAseo))
            throw new Exception("Los días que usted introdjuo no son válidos.");
        this.diasDeAseo = new ArrayList<>(Arrays.asList(diasDeAseo));
    }
    
    @SuppressWarnings("empty-statement")
    @Override
    public void pedirDatos() {
        super.pedirDatos();
        
        for(String datoAPedir : this.nombreDeDatos()) {
            while (!this.pedirYValidarDato(datoAPedir));
        }
    }

    private boolean pedirYValidarDato(String nombreDato) {
        try {
            switch(nombreDato) {
                case "diasAseo":
                    System.out.println(
                    "¿Que días realiza el aseo este empleado?"
                    + "\n (Ingrese los días separados por comas. "
                    + " Ejemplo: Lunes, Miercoles, Viernes)" );
                    String[] diasAseo = InterfazConsola.LECTOR_TECLADO.nextLine().split(",");
                    for (int i = 0; i < diasAseo.length; i++) {
                        diasAseo[i] = diasAseo[i].replaceAll(" ", "");
                        diasAseo[i] = diasAseo[i].toUpperCase();
                    }
                    this.setDiasDeAseo(diasAseo);
                break;
            }
        } catch (Exception ex) {
            InterfazConsola.imprimirMensajeDeError(ex.getMessage());
            return false;
        }
        return true;
    }
    
    private String[] nombreDeDatos() {
        ArrayList<String> datosSinInicializar = new ArrayList<>();
        
        if (this.diasDeAseo.isEmpty())
            datosSinInicializar.add("diasAseo");
        
        return datosSinInicializar.toArray(new String[0]);
    }
    
}
