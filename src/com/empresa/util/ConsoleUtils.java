package com.empresa.util;

import com.empresa.interfaces.TablePrintable;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ilichh1
 */
public class ConsoleUtils {
    
    public static final Scanner LECTOR_TECLADO = new Scanner(System.in);
    
    public static String pedirString() {
        return LECTOR_TECLADO.nextLine();
    }
    
    public static int pedirEntero() {
        return Integer.parseInt(pedirString());
    }
    
    public static double pedirDoble () {
        return Double.parseDouble(pedirString());
    }
    
    public static char pedirCaracter() {
        String lineTyped = pedirString();
        return lineTyped.length() > 0 ? lineTyped.charAt(0) : ' ';
    }
    
    public static void printErrorMessage(String message) {
        System.out.println(generateErrorMessage(message));
    }
    
    public static void printSuccessMessage(String message) {
        System.out.println(generateSuccessMessage(message));
    }
    
    private static String generateMessage (String message, int horizontalPadding, int verticalPadding, String borderChar, String prefix) {
        // TODO: Generar mensaje para los demas tipos de mensaje como:
        // Error, Advertencia y Exito
        int espacioEnBlanco = horizontalPadding;
        int lineasDeEspacio = verticalPadding;
        String caracterDeRelleno = borderChar;
        
        // La variable 'espacioEnBlanco' se multiplica por dos por que se debe
        // tener relleno a la izquierda y derecha del mensaje (dos veces)
        int largoHorizontalDelContenido = (espacioEnBlanco * 2) + message.length() + prefix.length() + 2;
        
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
                + prefix + ": " + message
                + rellenoHorizontal
                + caracterDeRelleno;
        
        return  lineasVerticales
                + rellenoVertical
                + "\n" + lineaDelMensaje
                + rellenoVertical
                + "\n" + lineasVerticales;
    }
    
    private static String generateErrorMessage (String mensaje) {
        // Más espacio y todo el texto en mayusculas para llamar más la atención
        return generateMessage(mensaje.toUpperCase(), 4, 2, "#", "ERROR");
    }
    
    private static String generateSuccessMessage (String mensaje) {
        // El texto normal y un cuadro reducido para notificar una operación exitosa
        return generateMessage(mensaje, 5, 1, "*", "Éxito");
    }
    
    public static String fillRemainingSpace(int currentLength, int finalLength, String filler) {
        int remainingSpace = finalLength - currentLength;
        if (remainingSpace < 0) return "";
        
        String filledSpace = "";
        for (int i = 0; i < remainingSpace; i++)
            filledSpace += filler;
        
        return filledSpace;
    }
    
    public static void printWelcomeMessage() {
        System.out.print("\n\nBienvenido al sistema de control de empleados.");
    }
    
    public static void validateUsername() {
        String username;
        String password;
        printWelcomeMessage();
        System.out.println("\n\n");
        do {
            System.out.print("Usuario: ");
            username = ConsoleUtils.pedirString();
            System.out.print("Contraseña: ");
            password = ConsoleUtils.pedirString();
        } while(!DataValidator.validateCredentials(username, password));
    }
    
    // Function to print any ArrayList as a Table
    public static void printAsTable(TablePrintable[] controllerArray, String[] headers) {
        String tableContent = "";
        
        // Get the max columnd widths for this table
        int[] maxColumnsLenghts = new int[headers.length];
        // Init the default lengths
        for (int i = 0; i < headers.length; i++) {
            maxColumnsLenghts[i] = headers[i].length();
        }
        // Actually measure lengths
        for (TablePrintable arrayObject : controllerArray) {
            maxColumnsLenghts = arrayObject.columnsLenghts(maxColumnsLenghts);
        }
        
        // DETERMINE THE TABLE MAX WIDTH
        int tableMaxWith = 0;
        for (int colWidth : maxColumnsLenghts)
            tableMaxWith += colWidth;
        tableMaxWith += (headers.length * 2) + (headers.length + 1);
        
        String tableVerticalLine = "";
        for (int i = 0; i < tableMaxWith; i++)
            tableVerticalLine += "-";
        tableVerticalLine += "\n";
        
        // FIRST LINE AT THE TOP OF THE TABLE
        tableContent += tableVerticalLine;
        
        // TABLE HEADERS
        String headersLine = "";
        for (int i = 0; i < headers.length; i++) {
            headersLine += "| " + headers[i]
                + ConsoleUtils.fillRemainingSpace(headers[i].length(), maxColumnsLenghts[i], " ")
                + " ";
        }
        headersLine += "|\n" + tableVerticalLine;
        tableContent += headersLine;
        
        // GENERATE THE TABLE BODY (rows)
        String tableBody = "";
        for (int idx = 0; idx < controllerArray.length; idx++) {
            String tableRow = "";
            String[] columnsWithSpace = controllerArray[idx].generateRow(maxColumnsLenghts);
            
            
            // THE ID OF THE ROW
            tableRow += "| "
                + ConsoleUtils.fillRemainingSpace(
                        Integer.toString(idx).length(),
                        maxColumnsLenghts[0],
                        " ")
                + idx;
            for (String column : columnsWithSpace) {
                tableRow += " | " + column;
            }
            tableRow += " |\n";
            tableBody += tableRow + tableVerticalLine;
        }
        tableContent += tableBody;
        
        System.out.println(tableContent);
    }
    
}
