/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package file;

import java.io.IOException;
import java.util.Scanner;

public class Empresa {

    static void main() {
        Scanner lea = new Scanner(System.in);
        EmpleadoManager mg = new EmpleadoManager();

        int opcion = 0;
       do{
        System.out.println("\n\nMENU\n");
        System.out.println("1- Agregar Empleado");
        System.out.println("2- Listar Empleados No Despedidos");
        System.out.println("3- Agregar Venta a Empleado");
        System.out.println("4- Pagar Empleado");
        System.out.println("5- Despedir Empleado");
        System.out.println("6- Ver empleado");
           System.out.println("7-Salir");
        System.out.println("Escoja una opción: ");
        opcion=lea.nextInt();
        lea.nextLine();
        
        try{
             switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = lea.nextLine();
                        System.out.print("Salario: ");
                        double salario = lea.nextDouble();
                        lea.nextLine();
                        mg.addEmployee(nombre, salario);
                        break;
                    case 2:
                        mg.employeeList();
                        break;
                    case 3:
                        System.out.print("Codigo del empleado: ");
                        int codVenta = lea.nextInt();
                        System.out.print("Monto de venta: ");
                        double monto = lea.nextDouble();
                        lea.nextLine();
                        mg.addSaleToEmployee(codVenta, monto);
                        break;
                    case 4:
                        System.out.print("Codigo del empleado: ");
                        int codPago = lea.nextInt();
                        lea.nextLine();
                        mg.payEmployee(codPago);
                        break;
                    case 5:
                        System.out.print("Codigo del empleado: ");
                        int codDesp = lea.nextInt();
                        lea.nextLine();
                        mg.fireEmployee(codDesp);
                        break;
                    case 6:
                        System.out.print("Codigo del empleado: ");
                        int codRep = lea.nextInt();
                        lea.nextLine();
                        mg.printEmployee(codRep);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }while(opcion!=7);
    }
}