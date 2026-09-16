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
 * /**
 * * Escenario 1: Registro de huéspedes y reservas El hotel necesita llevar un
 * registro basico de los huespedes que desean realizar una reserva, asi como la
 * informacion relacionada con su estadia. Tenemos: Registrar el nombre del
 * huésped. Registrar la cantidad de noches de la estadía. Seleccionar el tipo
 * de habitación que desea reservar. Validar la información ingresada por el
 * huésped. Confirmar la reserva solicitada. Mostrar la información
 * correspondiente a la reserva realizada. Valor agregado: Colocar un Calendario
 * en donde se le pida al usuario el dia que entrada, y luego de eso genere el
 * de salida.
 *
 * //Colocar un ciclo While( Para la fecha en donde el usuario ingrese del 1 al
 * 31, y tambien tiene que coincidir con el mes) que se coloque error si la
 * persona coloca un 33 y un mes mayor a 12 y menor al ano 2026 En la fecha
 * colocar una funcion // Limite de la cantidad de noches
 *
 * @author angie
 */
public class OtroProyecto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Arreglos para almacenar hasta 5 reservas
        String[] nombresHuespedes = new String[5];
        int[] cantidadesNoches = new int[5];
        int[] tiposHabitacion = new int[5];
        double[] preciosHabitacion = new double[5];
        double[] totalesReserva = new double[5];
        int contadorReservas = 0;

        char ingresarOtraReserva = 'N';
        int opcionMenu = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("          SISTEMA DE RESERVAS");
            System.out.println("========================================");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas");
            System.out.println("4. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            opcionMenu = sc.nextInt();
            sc.nextLine();

            switch (opcionMenu) {

                case 1:
                    System.out.println("\nHa seleccionado: Registrar reserva");

                    // AQUÍ irá el código que ya tenemos
                    break;

                case 2:
                    System.out.println("\nHa seleccionado: Cancelar reserva");

                    contadorReservas = cancelarReserva(sc, nombresHuespedes,
                            cantidadesNoches, tiposHabitacion,
                            preciosHabitacion, totalesReserva,
                            contadorReservas);
                    break;

                case 3:
                    System.out.println("\nHa seleccionado: Mostrar reservas");

                    listarTodasLasReservas(nombresHuespedes,
                            cantidadesNoches, tiposHabitacion,
                            preciosHabitacion, totalesReserva,
                            contadorReservas);
                    break;

                case 4:
                    System.out.println("\nGracias por utilizar el sistema de reservas.");
                    break;

                default:
                    System.out.println("\nOpcion no valida. Seleccione una opcion del 1 al 4.");
                    break;
            }

        } while (opcionMenu != 4);

        // BUCLE PRINCIPAL: Permite registrar múltiples huéspedes
        do {
            System.out.println("\n======== SISTEMA DE REGISTRO DE HUESPED (Reserva #" + (contadorReservas + 1) + ") =======");

            // Variables locales para la reserva actual
            String nombreHuesped = "";
            int cantidadNoches = 0;
            int tipoHabitacion = 0;
            char confirmacion = '@';
            double precioHabitacion = 0;
            double totalReserva = 0;
            int dia = 0;
            int mes = 0;
            int anio = 0;

            LocalDate fechaEntrada = null;
            LocalDate fechaSalida = null;
            boolean fechaValida = false;

            LocalTime horaEntrada = LocalTime.of(15, 0);
            LocalTime horaSalida = LocalTime.of(12, 0);

            // 1. Lectura de datos del huésped
            System.out.println("Ingrese el nombre del huesped: ");
            nombreHuesped = sc.nextLine();

            // Selección de tipo de habitación y cálculo de precio
            tipoHabitacion = TipoHabi(sc);
            precioHabitacion = obtenerPrecio(tipoHabitacion);

            // Registro de Noches y Fechas
            cantidadNoches = pedirEnteroValido(sc, "Ingrese la cantidad de noches:", "Cantidad de noches no valida", 1, 31);
            dia = pedirEnteroValido(sc, "Ingrese el dia de entrada:", "Dia no valido, debe ser entre 1 y 31", 1, 31);
            mes = pedirEnteroValido(sc, "Ingrese el mes de entrada:", "Mes no valido, tiene que ser entre 1 y 12", 1, 12);
            anio = pedirEnteroValido(sc, "Ingrese el anio de entrada:", "Anio no valido, tiene que ser el año actual (2026)", 2026, 2026);

            // Validación de fecha correcta en el calendario
            do {
                try {
                    fechaEntrada = LocalDate.of(anio, mes, dia);
                    fechaValida = true;
                } catch (Exception e) {
                    System.out.println("Fecha no valida, ingrese ano, mes y dia conforme a calendario");
                    fechaValida = false;
                }
            } while (!fechaValida);

            fechaSalida = fechaEntrada.plusDays(cantidadNoches);
            totalReserva = precioHabitacion * cantidadNoches;

            System.out.println("\n¿Desea confirmar la reserva? (S/N):");
            confirmacion = sc.next().toUpperCase().charAt(0);

            if (confirmacion == 'S') {
                System.out.println("Reserva Confirmada\n");

                // Guardar la reserva en los arreglos usando el contador actual
                nombresHuespedes[contadorReservas] = nombreHuesped;
                cantidadesNoches[contadorReservas] = cantidadNoches;
                tiposHabitacion[contadorReservas] = tipoHabitacion;
                preciosHabitacion[contadorReservas] = precioHabitacion;
                totalesReserva[contadorReservas] = totalReserva;

                // Incrementar contador para la siguiente posición del arreglo
                contadorReservas++;

                // Mostrar detalles de la reserva individual
                mostrarDetallesReserva(nombreHuesped, cantidadNoches, tipoHabitacion,
                        precioHabitacion, totalReserva, fechaEntrada,
                        fechaSalida, horaEntrada, horaSalida);

                // Proceso de Check-in
                procesarCheckIn(sc, horaEntrada);

            } else {
                System.out.println("Reserva cancelada/no confirmada.");
            }

            // Verificar si el arreglo ya está lleno (máximo 5 reservas)
            if (contadorReservas >= 5) {
                System.out.println("\n[!] Se ha alcanzado el límite máximo de reservas (5/5).");
                break;
            }

            // Preguntar si desea ingresar otro huésped desde el principio
            System.out.println("\n¿Desea registrar otra reserva desde el principio? (S/N):");
            ingresarOtraReserva = sc.next().toUpperCase().charAt(0);
            sc.nextLine(); // Limpiar el buffer de teclado tras leer el carácter

        } while (ingresarOtraReserva == 'S');

        // IMPRESIÓN DEL LISTADO GENERAL DE RESERVAS GUARDADAS
        listarTodasLasReservas(nombresHuespedes, cantidadesNoches, tiposHabitacion, preciosHabitacion, totalesReserva, contadorReservas);

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
            }
        } while (tipo < 1 || tipo > 3);

        return tipo;
    }

    // Método para obtener precio según la habitación
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
    }

    // Función de validación de enteros
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
    }

    // Impresión de detalles individuales
    public static void mostrarDetallesReserva(String nombre, int noches, int tipoHab,
            double precio, double total,
            LocalDate entrada, LocalDate salida,
            LocalTime hEntrada, LocalTime hSalida) {

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("\nDetalles de la reserva:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad de noches: " + noches);
        System.out.println("Tipo de habitacion: " + tipoHab);
        System.out.println("Precio por noche: $" + precio);
        System.out.println("Total a pagar: $" + total);
        System.out.println("Fecha de entrada: " + entrada.format(formatoFecha));
        System.out.println("Fecha de salida: " + salida.format(formatoFecha));
        System.out.println("Horario de entrada: " + hEntrada.format(formatoHora));
        System.out.println("Horario de salida: " + hSalida.format(formatoHora));
    }

    // Procesar Check-in
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
    }

    // Listar todas las reservas guardadas
    public static void listarTodasLasReservas(String[] nombres, int[] noches, int[] tipos,
            double[] precios, double[] totales, int cantidadRegistros) {

        System.out.println("\n==================================================================================");
        System.out.println("                            LISTADO GENERAL DE RESERVAS                           ");
        System.out.println("==================================================================================");

        if (cantidadRegistros == 0) {
            System.out.println("   [!] No hay reservas registradas en el sistema actualmente.");
            System.out.println("==================================================================================\n");
            return;
        }

        System.out.printf("%-5s | %-20s | %-15s | %-8s | %-10s | %-10s \n",
                "ID", "Huésped", "Tipo Hab.", "Noches", "Precio/N", "Total");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < cantidadRegistros; i++) {
            String nombreTipo = obtenerNombreHabitacion(tipos[i]);

            System.out.printf("%-5d | %-20s | %-15s | %-8d | $%-9.2f | $%-9.2f \n",
                    (i + 1),
                    nombres[i],
                    nombreTipo,
                    noches[i],
                    precios[i],
                    totales[i]);
        }

        System.out.println("----------------------------------------------------------------------------------");

        double totalAcumulado = calcularTotalGeneral(totales, cantidadRegistros);
        System.out.printf(" TOTAL REGISTROS: %d                               TOTAL FACTURADO: $%.2f%n",
                cantidadRegistros, totalAcumulado);
        System.out.println("==================================================================================\n");
    }

    // Obtener el nombre descriptivo de la habitación
    public static String obtenerNombreHabitacion(int tipo) {
        switch (tipo) {
            case 1:
                return "Sencilla";
            case 2:
                return "Doble";
            case 3:
                return "Suite";
            default:
                return "Desconocido";
        }
    }

    // Calcular el total general acumulado
    public static double calcularTotalGeneral(double[] totales, int cantidadRegistros) {
        double sumaTotal = 0.0;
        for (int i = 0; i < cantidadRegistros; i++) {
            sumaTotal += totales[i];
        }
        return sumaTotal;
    }//Fin funcion CalcularTotalGeneral

    public static int cancelarReserva(Scanner sc, String[] nombres, int[] noches,
            int[] tipos, double[] precios, double[] totales, int cantidadRegistros) {

        if (cantidadRegistros == 0) {
            System.out.println("\nNo hay reservas para cancelar.");
            return cantidadRegistros;
        }

        System.out.println("\n======== RESERVAS REGISTRADAS ========");

        for (int i = 0; i < cantidadRegistros; i++) {
            System.out.println("ID: " + (i + 1)
                    + " | Huesped: " + nombres[i]
                    + " | Noches: " + noches[i]
                    + " | Total: $" + totales[i]);
        }

        int idReserva = pedirEnteroValido(sc,
                "Ingrese el ID de la reserva que desea cancelar:",
                "ID de reserva no valido.",
                1, cantidadRegistros);

        int posicion = idReserva - 1;

        System.out.println("\nReserva seleccionada: " + nombres[posicion]);

        System.out.println("¿Esta seguro de cancelar la reserva? (S/N):");
        char confirmacion = sc.next().toUpperCase().charAt(0);

        if (confirmacion == 'S') {

            for (int i = posicion; i < cantidadRegistros - 1; i++) {
                nombres[i] = nombres[i + 1];
                noches[i] = noches[i + 1];
                tipos[i] = tipos[i + 1];
                precios[i] = precios[i + 1];
                totales[i] = totales[i + 1];
            }

            cantidadRegistros--;

            nombres[cantidadRegistros] = null;
            noches[cantidadRegistros] = 0;
            tipos[cantidadRegistros] = 0;
            precios[cantidadRegistros] = 0;
            totales[cantidadRegistros] = 0;

            System.out.println("\nReserva cancelada correctamente.");

        } else {
            System.out.println("\nLa reserva no fue cancelada.");
        }

        return cantidadRegistros;
    }

}//fin class
