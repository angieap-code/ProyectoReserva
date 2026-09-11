/*Este es el que tenemos correcto con Agie sin Arreglos.
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoreservadehotel;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Escenario 1: Registro de huéspedes y reservas
 * @author angie
 */
public class ProyectoReservadeHotel{

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
