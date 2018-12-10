/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.abstracts;

import com.empresa.interfaces.TablePrintable;
import com.empresa.util.InterfazConsola;
import com.empresa.util.ValidadorDeDatos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public abstract class Empleado extends Persona implements TablePrintable {
    
    public static final int ASEO          = 0;
    public static final int ALMACENISTA   = 1;
    public static final int DESPACHADOR   = 1;
    public static final int GERENTE       = 2;
    public static final int SUPERVISOR    = 3;
    public static final int ADMINISTRADOR = 4;
    
    private static final String[] AREAS = new String[] {
        "Aseo",
        "Almacenista o Despachador",
        "Gerente",
        "Supervisor",
        "Administrador"
    };
    
    private static final String[] JERARQUIAS = { "Bajo", "Normal", "Intermedio", "Alto", "Administrativo"};
    
    // Solo para el duseño de todo el establecimiento
    // Solo puede existir un UNICO dueño
    public static final int OWNER      = 5;
    public static boolean ownerExists = false;
    
    // Metodo para saber si el dueño ya ha sido creado
    public static boolean sePuedeCrearOwner() {
        if (ownerExists) {
            return false;
        }
        ownerExists = true;
        return ownerExists;
    }
    
    protected LocalDate fechaIngreso;
    protected int area;
    
   
    public Empleado(String nom, String ap, int edad, LocalDate fechaIngreso, int area) throws Exception {
        super(nom, ap, edad);
        
        if (area == OWNER && !sePuedeCrearOwner()) {
            throw new Exception("NO PUEDE EXISITIR MÁS DE UN DUEÑO");
        }
        
        this.fechaIngreso = fechaIngreso;
        this.area = area;
    }
    
    public Empleado () {
        super();
        
        this.fechaIngreso = null;
        this.area = Empleado.ASEO;
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
            switch (nombreDato) {
                case "area":
                    System.out.print("Area de labores del empleado: ");
                    System.out.println("*  ASEO ............ " + ASEO);
                    System.out.println("*  ALMACENISTA ..... " + ALMACENISTA);
                    System.out.println("*  DESPACHADOR ..... " + DESPACHADOR);
                    System.out.println("*  GERENTE ......... " + GERENTE);
                    System.out.println("*  SUPERVISOR ...... " + SUPERVISOR);
                    System.out.println("*  ADMINISTRADOR ... " + ADMINISTRADOR);
                    this.setArea(Integer.parseInt(
                                        InterfazConsola.LECTOR_TECLADO.nextLine()
                                ));
                break;
                case "fechaIngreso":
                    System.out.print("Fecha de entrada (DD/MM/AAAA): ");
                    String[] datosFechaString = InterfazConsola.LECTOR_TECLADO
                                          .nextLine().split("/");
                    
                    // Convertir la entrada de String a int
                    int[] datosFechaInt = new int[3];
                    datosFechaInt[0] = Integer.parseInt(datosFechaString[0]);
                    datosFechaInt[1] = Integer.parseInt(datosFechaString[1]);
                    datosFechaInt[2] = Integer.parseInt(datosFechaString[2]);
                    this.setFechaIngreso(
                            LocalDate.of(datosFechaInt[2],
                                         datosFechaInt[1],
                                         datosFechaInt[0]) );
                break;
            }
        } catch (Exception ex) {
            InterfazConsola.imprimirMensajeDeError(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    private String[] nombreDeDatos() {
        ArrayList<String> datosSinInicializar = new ArrayList<>();
        
        if (this.area == -1)
            datosSinInicializar.add("area");
        
        if (this.fechaIngreso == null)
            datosSinInicializar.add("fechaIngreso");
        
        return datosSinInicializar.toArray(new String[0]);
    }
    
    public String getNivelJerarquico () {
        // TODO: Completar este switch cuando recuerde como hacerm multiples case
        switch (this.area) {
            case ASEO:
                return JERARQUIAS[0];
            case 1: // El mismo nivel para ALMACENISTA y DESPACHADOR
                return JERARQUIAS[1];
            case GERENTE:
                return JERARQUIAS[2];
            case SUPERVISOR:
                return JERARQUIAS[3];
            case ADMINISTRADOR:
                return JERARQUIAS[4];
            default:
                return "INDETERMINADO";
        }
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getArea() {
        return area;
    }
    
    public String getAreaString() {
        return AREAS[this.area];
    }
    
    public void setArea(int area) throws java.lang.Exception {
        if (area == OWNER) {
            InterfazConsola.imprimirMensajeDeError("SOLO PUEDE EXISITR UN DUEÑO.");
            return;
        }
        if (!ValidadorDeDatos.validarAreaDeTrabajo(area))
            throw new Exception("El area de trabajo no existe");
        this.area = area;
    }
    
    @Override
    public String[] toStringArray() {
        return new String[] {
            this.getNombreCompleto(),
            Integer.toString(this.getEdad()),
            this.getFechaIngreso().toString(),
            this.getAreaString(),
            this.getNivelJerarquico()
        };
    }
    
    public boolean contains(String busqueda) {
        return Arrays.toString(this.toStringArray()).contains(busqueda);
    }
    
}
