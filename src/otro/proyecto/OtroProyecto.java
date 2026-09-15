/*Este es el que tenemos correcto con Agie sin Arreglos.
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package otro.proyecto;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Escenario 1: Registro de huéspedes y reservas
 * @author angie
 */
public class OtroProyecto{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaración de Variables
        String nombreHuesped = "";
        int cantidadNoches = 0;
        int tipoHabitacion = 0;
        char confirmacion = '@';
        double precioHabitacion = 0;
        double totalReserva = 0;
        int dia = 0;
        int mes = 0;
        int anio = 0;

        // Variables de fecha - Librería LocalDate
        LocalDate fechaEntrada = null;
        LocalDate fechaSalida = null;
        boolean fechaValida = false;

        
        // Arreglos para almacenar las reservas
        String[] nombresHuespedes = new String[5];
        int[] cantidadesNoches = new int[5];
        int[] tiposHabitacion = new int[5];
        double[] preciosHabitacion = new double[5];
        double[] totalesReserva = new double[5];
        int contadorReservas = 0;

        // Variables de hora - Librería LocalTime
        LocalTime horaEntrada = LocalTime.of(15, 0);
        LocalTime horaSalida = LocalTime.of(12, 0);

        System.out.println("======== SISTEMA DE REGISTRO DE HUESPED =======");

        System.out.println("Ingrese el nombre del huesped: ");
        nombreHuesped = sc.nextLine();

        // LLAMADA A LA FUNCIÓN PARA SWITCH 1 TIPO DE HABITACION
        
        //LLAMADA A LA FUNCIÓN PARA SWITCH 2 PRECIO DE HABITACION
        tipoHabitacion = TipoHabi(sc);
        precioHabitacion = obtenerPrecio(tipoHabitacion);
        

                // FUNCION Registro de Noches
        cantidadNoches = pedirEnteroValido(sc, "Ingrese la cantidad de noches:", "Cantidad de noches no valida", 1, 31);
        dia = pedirEnteroValido(sc, "Ingrese el dia de entrada:", "Dia no valido, debe ser entre 1 y 31", 1, 31);
        mes = pedirEnteroValido(sc, "Ingrese el mes de entrada:", "Mes no valido, tiene que ser entre 1 y 12", 1, 12);
        anio = pedirEnteroValido(sc, "Ingrese el anio de entrada:", "Anio no valido, tiene que ser el año actual (2026)", 2026, 2026);

           do{
        try {
            fechaEntrada = LocalDate.of(anio, mes, dia);
            fechaValida = true;
        } catch (Exception e) {
            System.out.println("Fecha no valida, ingrese ano, mes y dia conforme a calendario");
            fechaValida = false;
        }
        } while (!fechaValida);
            
            //Hora entrada y salida
        fechaSalida = fechaEntrada.plusDays(cantidadNoches);
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        totalReserva = precioHabitacion * cantidadNoches;

        System.out.println("\n¿Desea confirmar la reserva? (S/N):");
confirmacion = sc.next().toUpperCase().charAt(0);

if (confirmacion == 'S') {
    System.out.println("Reserva Confirmada\n");
    // ... llamadas a las funciones
    

    // 1. Llamada a la función de impresión
    mostrarDetallesReserva(nombreHuesped, cantidadNoches, tipoHabitacion, 
    precioHabitacion, totalReserva, fechaEntrada, 
    fechaSalida, horaEntrada, horaSalida);

    // 2. Llamada a la función del Check-in
    procesarCheckIn(sc, horaEntrada);

} else {
    System.out.println("Reserva no confirmada");
}//fin else
        
    } // FIN MAIN

    // FUNCIÓN PARA SELECCIONAR TIPO DE HABITACIÓN
    public static int TipoHabi(Scanner sc) {
        int tipo = 0;
        do {
            System.out.println("\nSeleccione el tipo de habitacion");
            System.out.println("1. Habitacion Sencilla");
            System.out.println("2. Habitacion Doble");
            System.out.println("3. Suite");
            System.out.print("Respuesta: ");

            tipo = sc.nextInt();

            switch (tipo) {
                case 1:
                    System.out.println("Selecciono Habitacion Sencilla");

                    break;

                case 2:
                    System.out.println("Selecciono Habitacion Doble");

                    break;

                case 3:
                    System.out.println("Selecciono Habitacion Suite");

                    break;

                default:
                    System.out.println("Tipo de habitacion no valido, la opcion es de 1 al 3");
                    break;
            } //Fin de Switch

          
        } while (tipo < 1 || tipo > 3);

        return tipo;
        
    } // fin funcion TipoHabi

    // Método independiente para calcular el precio
    public static double obtenerPrecio(int tipo) {
        switch (tipo) {
            case 1: 
                return 50.0;
            case 2: 
                return 80.0;
            case 3: 
                return 120.0;
            default: 
                return 0.0;
        }
    }//fin funcion
    
// Funcion de Cantidad Noches,dia, Mes y anio
    public static int pedirEnteroValido(Scanner sc, String mensaje, String mensajeError, int min, int max) {
        int valor;
        do {
            System.out.println(mensaje);
            valor = sc.nextInt();
            if (valor < min || valor > max) {
                System.out.println(mensajeError);
            }
        } while (valor < min || valor > max);
        return valor;
    }//fin funcion
    public static void mostrarDetallesReserva(String nombre, int noches, int tipoHab, 
                                          double precio, double total, 
                                          LocalDate entrada, LocalDate salida, 
                                          LocalTime hEntrada, LocalTime hSalida) { // <-- Cambiado a LocalTime
    
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm"); // Formato bonito para la hora (ej. 14:30)

    System.out.println("Detalles de la reserva:");
    System.out.println("Nombre: " + nombre);
    System.out.println("Cantidad de noches: " + noches);
    System.out.println("Tipo de habitacion: " + tipoHab);
    System.out.println("Precio por noche: $" + precio);
    System.out.println("Total a pagar: $" + total);
    System.out.println("Fecha de entrada: " + entrada.format(formatoFecha));
    System.out.println("Fecha de salida: " + salida.format(formatoFecha));
    
    // Al concadenar con +, Java llama internamente a .toString(), pero también puedes formatearlo
    System.out.println("Horario de entrada: " + hEntrada.format(formatoHora)); 
    System.out.println("Horario de salida: " + hSalida.format(formatoHora));


}//fin funcion
    public static void procesarCheckIn(Scanner sc, LocalTime horaEntrada) {
    System.out.println("\nDesea realizar el Check-in? (S/N)");
    System.out.print("Respuesta: ");

    char realizarCheckIn = sc.next().toUpperCase().charAt(0);

    if (realizarCheckIn == 'S') {
        System.out.println("Check-in realizado correctamente.");
        System.out.println("Hora de entrada: " + horaEntrada);
    } else {
        System.out.println("Check-in no realizado.");
    }
}//fin funcion

} // Fin de class