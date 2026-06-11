/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package file;

import java.util.Scanner;

public class Empresa {

    static void main() {
        Scanner lea = new Scanner(System.in);
        EmpleadoManager mg = new EmpleadoManager();

        int opcion = 0;

        System.out.println("\n\nMENU\n");
        System.out.println("1- Agregar Empleado");
        System.out.println("2- Listar Empleados No Despedidos");
        System.out.println("3- Agregar Venta a Empleado");
        System.out.println("4- Pagar Empleado");
        System.out.println("5- Despedir Empleado");
        System.out.println("6- Salir");
        System.out.println("Escoja una opción: ");

    }
}