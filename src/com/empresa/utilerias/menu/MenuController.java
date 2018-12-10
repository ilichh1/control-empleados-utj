/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.utilerias.menu;

import com.empresa.empleados.controladores.ControladorEmpleados;
import com.empresa.interfaces.ControladorMenu;
import com.empresa.main.ControlEmpleados;
import com.empresa.util.ConsoleUtils;
import java.util.ArrayList;

/**
 *
 * @author ilichh1
 */
public class MenuController implements ControladorMenu {
    
    public static final ControladorEmpleados EMPLEADOS_CONTROLADOR = new ControladorEmpleados();
    
    private final ArrayList<Menu> menus = new ArrayList<>();
    
    private Menu startingMenu;
    private Menu empleadosMenu;
    
    public MenuController() {
        try {
            this.initializateMenus();
        } catch (Exception ex) {
            ConsoleUtils.printErrorMessage(ex.getLocalizedMessage());
        }
    }
    
    public void iniciarMenuController() {
        this.triggerLastMenu();
    }
    
    /*
     * Sets the static main variable true if the end of the execution is reached
     */
    @Override
    public void doSpecificAction(String actionName) {
        // TODO: Completar codigo para realizar cada acción en especifico
        switch(actionName) {
            // Menú de empleados
            case "irAEmpleadosMenu":
                this.moveToMenu("empleados");
            break;
            case "verEmpleados":
                EMPLEADOS_CONTROLADOR.impimirEmpleados();
            break;
            case "eliminarEmpleado":
                System.out.print("Ingrese el ID del empleado a eliminar: ");
                EMPLEADOS_CONTROLADOR.eliminarEmpleado(ConsoleUtils.pedirEntero());
            break;
            case "agregarEmpladoAseo":
                EMPLEADOS_CONTROLADOR.agregarEmpleado("aseo");
            break;
            case "agregarEmpladoAlmacenista":
                EMPLEADOS_CONTROLADOR.agregarEmpleado("almacenista");
            break;
            case "buscarEmpleado":
                System.out.print("Ingrese el valor de busqueda para encontrar al empleado: ");
                EMPLEADOS_CONTROLADOR.buscarEmpleado(ConsoleUtils.pedirString());
            break;
            // GENERAL ACTIONS
            case "irAtras":
                this.previousMenu();
            break;
            case "continue": break;
            case "exit":
                System.out.println("¡Hasta luego!");
                ControlEmpleados.FIN_DE_LA_EJECUCION = true;
            break;
            default:
                System.out.println("STILL WORKING ON: " + actionName);
            break;
        }
    }
    
    public boolean triggerLastMenu() {
        // TODO: Probablemente de un error aquí por el índice
        if (this.menus.size() == 1) {
            return this.menus.get(0).printMenu(this);
        } else {
            return this.menus.get(this.menus.size() - 1).printMenu(this);
        }
    }
    
    private void initializateMenus() throws Exception {
        // Menú de inicio
        this.startingMenu = new Menu("Menú de inicio", new MenuEntry[] {
            new MenuEntry("AGREGAR EMPLEADO", 'A', "irAEmpleadosMenu"),
            new MenuEntry("VER TODOS LOS EMPLEADOS", 'B', "verEmpleados"),
            new MenuEntry("BUSCAR EMPLEADO", 'C', "buscarEmpleado"),
            new MenuEntry("ELIMINAR EMPLEADO", 'D', "eliminarEmpleado"),
            new MenuEntry("SALIR", 'E', "exit")
        });
        this.menus.add(startingMenu);
        
        this.empleadosMenu = new Menu("Menú de empleados", new MenuEntry[] {
            new MenuEntry("ASEO", 'A', "agregarEmpladoAseo"),
            new MenuEntry("ALMACENISTA", 'B', "agregarEmpladoAlmacenista"),
            // new MenuEntry("DESPACHADOR", 'C', "agregarEmpladoDespachador"),
            // new MenuEntry("GERENTE", 'D', "agregarEmpladoGerente"),
            // new MenuEntry("SUPERVISOR", 'E', "agregarEmpladoSupervisor"),
            // new MenuEntry("ADMINISTRADOR", 'F', "agregarEmpladoAdministrador"),
            new MenuEntry("REGRESAR", 'E', "irAtras")
        });
    }
    
    public void moveToMenu(String menuName) {
        switch (menuName) {
            case "empleados":
                this.menus.add(this.empleadosMenu);
            break;
            default:
                ConsoleUtils.printErrorMessage("El menú " + menuName + " no exite.");
        }
        this.triggerLastMenu();
    }
    
    public void previousMenu() {
        this.menus.remove(this.menus.size() - 1);
        this.triggerLastMenu();
    }
}
