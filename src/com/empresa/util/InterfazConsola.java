/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.util;

import com.empresa.empleados.Aseo;
import com.empresa.empleados.Owner;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilichh1
 */
public class InterfazConsola {
    
    public static final Scanner LECTOR_TECLADO = new Scanner(System.in);
    
    
    private Owner owner = null;
    
    public InterfazConsola() {
        
        String username;
        String password;
        
        // TODO: Mejorar la ejecución de esta función
        // this.imprimirMensajeBienvenida("Control de empleados v0.1");
        
        System.out.println("==== ¡Bienvenido! ==== \n Por favor ingrese su usuario y contraseña.\n\n");
        
        do {
            System.out.print("usuario: ");
            username = LECTOR_TECLADO.nextLine();
            System.out.print("contraseña: ");
            password = LECTOR_TECLADO.nextLine();
        } while (!ValidadorDeDatos.validarAcceso(username, password));
        
        try {
            this.owner = new Owner();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
            Logger.getLogger(InterfazConsola.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n Bienvenido " + owner.getNombres() + " \n ¿Que desea hacer hoy?");
        this.menuInicio();
    }
    
    private void menuInicio() {
        System.out.println("\n\n\n ====== Menú de inicio ===== \n");
        
        System.out.println("*  CONSULTAR EMPLEADOS  .........  A)");
        System.out.println("*  REGISTRAR NUEVO EMPLEADO  ....  B)");
        System.out.println("*  EDITAR EMPLEADO EXISTENTE  ...  C)");
        System.out.println("*  ELIMINAR EMPLEADO  ...........  D)");
        
//        TODO: Manejar las demas opciones del menú
//        TODO: Valdiar que unicamente sean las opciones correctas
        LECTOR_TECLADO.nextLine();
        this.menuRegistroEmpleados();
    }
    
    private void menuRegistroEmpleados () {
        System.out.println("\n\n\n ====== Registro de empleados ===== \n");
        
        System.out.println("¿Que tipo de empleado quiere registrar? \n");    
        
        System.out.println("*  ASEO  ............  A)");
        System.out.println("*  ALMACENISTA  .....  B)");
        System.out.println("*  DESPACHADOR  .....  C)");
        System.out.println("*  GERENTE  .........  D)");
        System.out.println("*  SUPERVISOR  ......  E)");
        System.out.println("*  ADMINISTRADOR  ...  F)");
        
//        TODO: Manejar las demas opciones del menú
//        TODO: Valdiar que unicamente sean las opciones correctas
        LECTOR_TECLADO.nextLine();
        this.registrarEmpleado("aseo");
    }
    
    public void registrarEmpleado(String tipoEmpleado) {
        switch (tipoEmpleado) {
            case "aseo":
                Aseo empleadoAseo = new Aseo();
                empleadoAseo.pedirDatos();
                System.out.println("\n\n=========\n" + Arrays.toString(empleadoAseo.getDiasDeAseo().toArray()));
            break;
            default:
                imprimirMensajeDeError("No se especifico el tipo de empleado.");
        }
    }
    
    private void imprimirMensajeBienvenida (String mensaje) {
        char[] mensajeArray = mensaje.toCharArray();
        
        try {
            System.out.print("\t");
            for(char letra : mensajeArray) {
                Thread.sleep(250);
                System.out.print(letra);
            }
            System.out.print("\n\t");
            for (int i = 0; i < mensajeArray.length; i++) {
                System.out.print("_");
                Thread.sleep(185);
            }
        } catch (InterruptedException ex) {
            System.out.println("No se pudo pausar el hilo.");
            System.exit(0);
        } finally {
            System.out.println("\n\n\nPor Webtix Software\n\n\n");
        }
    }
    
    public static void imprimirMensajeDeError(String mensaje) {
        System.out.println(generarMensajeDeError(mensaje));
    }
    
    public static void imprimirMensajeDeExito(String mensaje) {
        System.out.println(generarMensajeDeExito(mensaje));
    }
    
    private static String generarMensaje (String mensaje, int espacioHorizontal, int espacioVertical, String borde, String prefijo) {
        // TODO: Generar mensaje para los demas tipos de mensaje como:
        // Error, Advertencia y Exito
        int espacioEnBlanco = espacioHorizontal;
        int lineasDeEspacio = espacioVertical;
        String caracterDeRelleno = borde;
        
        // La variable 'espacioEnBlanco' se multiplica por dos por que se debe
        // tener relleno a la izquierda y derecha del mensaje (dos veces)
        int largoHorizontalDelContenido = (espacioEnBlanco * 2) + mensaje.length() + prefijo.length() + 2;
        
        String lineasVerticales = "";
        
        // La constante de '2' es para las esquinas del rectangulo
        for (int i = 0; i < largoHorizontalDelContenido + 2; i++) {
            lineasVerticales += caracterDeRelleno;
        }
        
        String lineaDeSaltos = caracterDeRelleno;
        for (int i = 0; i < largoHorizontalDelContenido; i++) {
            lineaDeSaltos += " ";
        }
        lineaDeSaltos += caracterDeRelleno;
        
        String rellenoHorizontal = "";
        for (int i = 0; i < espacioEnBlanco; i++) rellenoHorizontal += " ";
        
        // El verdadero contenido del mensaje a imprimir
        String rellenoVertical = "";
        for (int i = 0; i < lineasDeEspacio; i++) {
            rellenoVertical += "\n" + lineaDeSaltos;
        }
        
        // Linea que contendrá el texto del mensaje
        String lineaDelMensaje =
                caracterDeRelleno
                + rellenoHorizontal
                + prefijo + ": " + mensaje
                + rellenoHorizontal
                + caracterDeRelleno;
        
        return  lineasVerticales
                + rellenoVertical
                + "\n" + lineaDelMensaje
                + rellenoVertical
                + "\n" + lineasVerticales;
    }
    
    private static String generarMensajeDeError (String mensaje) {
        // Más espacio y todo el texto en mayusculas para llamar más la atención
        return generarMensaje(mensaje.toUpperCase(), 4, 2, "#", "ERROR");
    }
    
    private static String generarMensajeDeExito(String mensaje) {
        // El texto normal y un cuadro reducido para notificar una operación exitosa
        return generarMensaje(mensaje, 5, 1, "*", "Éxito");
    }
    
}
