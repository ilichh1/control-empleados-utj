/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.empleados.controladores;

import com.empresa.abstracts.Empleado;
import com.empresa.empleados.AlmacenDespachador;
import com.empresa.empleados.Aseo;
import com.empresa.interfaces.TablePrintable;
import com.empresa.util.ConsoleUtils;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ilichh1
 */
public class ControladorEmpleados {
    
    public static final ArrayList<Empleado> EMPLEADOS = new ArrayList<>();
    
    private static final String[] HEADERS = new String[] {
        " ID ",
        "Nombre",
        "Edad",
        "Fecha de ingreso",
        "Area de trabajo",
        "Nivel Jerarquico"
    };
    
    public ControladorEmpleados() {
        try {
            this.inicializarControlador();
        } catch (Exception ex) {
            ConsoleUtils.printErrorMessage("No puede crear otro dueño.");
        }
    }
    
    private void inicializarControlador() throws Exception {
        EMPLEADOS.add(new Aseo(
                "Salma",
                "Hayek",
                43,
                LocalDate.of(2001, 06, 30),
                new String[] {"Lunes", "Martes", "Jueves", "Sábado"}
        ));
        EMPLEADOS.add(new AlmacenDespachador(
                "Ben",
                "Affleck",
                55,
                LocalDate.of(2000, 05, 19),
                "Almacén las conchitas"
        ));
    }
    
    public void impimirEmpleados() {
        TablePrintable[] empleadosTabla = EMPLEADOS.toArray(new TablePrintable[EMPLEADOS.size()]);
        ConsoleUtils.printAsTable(empleadosTabla, HEADERS);
    }
    
    public void buscarEmpleado(String valorBusqueda) {
        ArrayList<Empleado> empleadosABuscar = (ArrayList<Empleado>) EMPLEADOS.clone();
        
        empleadosABuscar.removeIf(empleado -> !empleado.contains(valorBusqueda));
        
        TablePrintable[] empleadosTabla = empleadosABuscar.toArray(new TablePrintable[empleadosABuscar.size()]);
        ConsoleUtils.printAsTable(empleadosTabla, HEADERS);
    }
    
    public void agregarEmpleado(String tipoEmpleado) {
        try {
            switch(tipoEmpleado) {
            case "almacenista":
                AlmacenDespachador empleadoAlmacen = new AlmacenDespachador();
                empleadoAlmacen.pedirDatos();
                EMPLEADOS.add(empleadoAlmacen);
            break;
            case "aseo":
                Aseo empleadoAseo = new Aseo();
                empleadoAseo.pedirDatos();
                EMPLEADOS.add(empleadoAseo);
            break;
            default:
                ConsoleUtils.printErrorMessage("Tipo de empleado no soportado.");
            }
        } catch(Exception ex) {
            ConsoleUtils.printErrorMessage("No puede crear otro empleado");
        }
    }
    
    public void eliminarEmpleado(int idx) {
        if (idx > 0 && idx < EMPLEADOS.size()) {
            EMPLEADOS.remove(idx);
            ConsoleUtils.printSuccessMessage("Empleado con el ID "+idx+" eliminado.");
        } else {
            ConsoleUtils.printErrorMessage("Empleado con el ID "+idx+" no existe.");
        }
    }
}
