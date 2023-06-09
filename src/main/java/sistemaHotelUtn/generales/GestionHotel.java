package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEmpleados.GestionEmpleados;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionServiciosGastronomicos.GestionServiciosGastronomicos;
import sistemaHotelUtn.gestionServiciosGastronomicos.ServicioGastronomia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class GestionHotel
{
    private GestionEmpleados gestionEmpleados;
    private GestionClientes gestionClientes;
    private GestionReservas gestionReservas;
    private GestionEventos gestionEventos;
    private GestionHabitaciones gestionHabitaciones;

    private GestionServiciosGastronomicos gestionServiciosGastronomicos;
    private Usuario usuarioActual = null;

    Scanner scanner = new Scanner(System.in);

    public GestionHotel()
    {
        this.gestionClientes = new GestionClientes();
        this.gestionClientes.cargarJson();

        this.gestionHabitaciones = new GestionHabitaciones();
        this.gestionHabitaciones.cargarJson();

        this.gestionEventos = new GestionEventos();
        this.gestionEventos.cargarJson();

        this.gestionEmpleados = new GestionEmpleados();
        this.gestionEmpleados.cargarJson();

        this.gestionReservas = new GestionReservas();

        this.gestionServiciosGastronomicos = new GestionServiciosGastronomicos();
    }


    public void mostrarMenuPrincipal() {
        /*Muestra el menu principal, y llama a todos los demás menús*/
        String entrada = "";
        int opcion = 0;
        boolean salir = false;

        while (!salir)
        {
            System.out.println("---------------------------- Sistema Gestion Hotel -------------------------------------");
            System.out.println("\n\t[1] Login\n\t[2] Regitrarse\n\t[3] Salir");
            System.out.println("\nUsuario actual: " +
                    ((usuarioActual == null) ? "No ha hecho login" : usuarioActual.getUsername()));

            while( opcion == 0)
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                    entrada = scanner.nextLine();

                    opcion = Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 3)
                    {

                        throw new IllegalArgumentException();
                    }

                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1: //login
                    //verificar si es cliente o empleado
                    //mostrar menu que corresponde
                    mostrarMenuLoginGeneral();
                    opcion = 0; //se resetea la opcion para que luego vuelva al menu principal
                    break;

                case 2: //registrarse
                    //puede registrarse como cliente o empleado
                    mostrarMenuRegistroGeneral();
                    opcion = 0; //se resetea la opcion para que luego vuelva al menu principal
                    break;

                case 3: //salir
                    salir = true;
                    break;

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }

    }

    /*---------------------MENUS REGISTRO-----------------*/

    private void mostrarMenuRegistroGeneral() {
        String entradaRegistro = "";
        int opcionRegistro = 0;

        System.out.println("\n-------------------------------- Registro -------------------------------------");
        System.out.println("\t[1] Registrar Cliente");
        System.out.println("\t[2] Registrar Empleado.");
        System.out.println("\t[3] Volver");

        while( opcionRegistro == 0 )
        {
            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                entradaRegistro = scanner.nextLine();

                opcionRegistro = Integer.parseInt(entradaRegistro);

                if( opcionRegistro < 1 || opcionRegistro > 3)
                {

                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionRegistro = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionRegistro = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }
        }

        switch(opcionRegistro)
        {
            case 1: //instanciar un nuevo cliente con entrada por usuario
                registrarNuevoCliente();
                break;

            case 2: //instanciar un nuevo empleado con entrada por usuario
                registrarNuevoEmpleado();
                break;

            case 3: //salir
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }

    }

    private void registrarNuevoCliente()
    {
        System.out.println("**** Registrar Nuevo Cliente **** ");
        Cliente nuevoCliente = gestionClientes.crearElemento();
        System.out.println("\nSe agrego el nuevo cliente:");
        System.out.println(nuevoCliente);
        gestionClientes.agregar(nuevoCliente);
        gestionClientes.guardarJson();
    }

    private void registrarNuevoEmpleado()
    {
        System.out.println("**** Registrar Nuevo Empleado **** ");
        Empleado nuevoEmpleado = gestionEmpleados.crearElemento();
        System.out.println("\nSe agrego el nuevo empleado:");
        System.out.println(nuevoEmpleado);
        gestionEmpleados.agregar(nuevoEmpleado);
        gestionEmpleados.guardarJson();
    }

    /*--------------------- MENUS LOGIN -----------------*/

    private void mostrarMenuLoginGeneral() {
        String entradaLogin = "";
        int opcionLogin = 0;


        System.out.println("\n-------------------------------- Login -------------------------------------");
        System.out.println("\t[1] Login Cliente");
        System.out.println("\t[2] Login Empleado");
        System.out.println("\t[3] Volver");

        while( opcionLogin == 0 )
        {
            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                entradaLogin = scanner.nextLine();

                opcionLogin = Integer.parseInt(entradaLogin);

                if( opcionLogin < 1 || opcionLogin > 3)
                {

                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionLogin = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionLogin = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }
        }


        switch (opcionLogin) {
            case 1: //login cliente
                mostrarMenuLoginCliente();
                break;

            case 2: //login empleado
                mostrarMenuLoginEmpleado();
                break;
                

            default:
                System.out.println("Opción incorrecta");
        }
    }

    private void mostrarMenuLoginCliente() {
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login Cliente -------------------------------------");
        //solicitar username y password
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        //busca que exista ese cliente por username
        try{
        Cliente cliente = gestionClientes.buscarClientePorUsername(username);
        if(!cliente.getUsuario().getPassword().equals(password)) cliente=null;
        if (cliente != null) {
            this.usuarioActual = cliente.getUsuario();
            System.out.println("Cliente hizo login: " + usuarioActual.getUsername() );
            mostrarMenuCliente( cliente );
        }
        else System.out.println("La contraseña no es correcta");

        }catch(NullPointerException e){
            System.out.println("No se ha encontrado un empleado con username: " + username);
        }

    }





    private void mostrarMenuLoginEmpleado() {
        //solicitar username y password
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login Empleado -------------------------------------");
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        try{
            Empleado empleado = gestionEmpleados.buscarEmpleadoPorUsername(username); //BUSCA ESE EMPLEADO

            if(!empleado.getUsuario().getPassword().equals(password)) empleado=null;
            if (empleado != null) {
                this.usuarioActual = empleado.getUsuario();
                System.out.println("Empleado hizo login: " + usuarioActual.getUsername());
                mostrarMenuEmpleado(empleado);

        }else System.out.println("La contraseña no es correcta");
        }
            catch(NullPointerException e){
                System.out.println("No se ha encontrado un empleado con username: " + username);
        }


    }


    private void mostrarMenuCliente(Cliente cliente) {
        boolean retener = true;
        int opcion = 0;
        String entrada = "";

        while (retener) {
            System.out.println("\n ----------------------------- Menu Cliente -------------------------------------- ");
            System.out.println("[1] Detalles de Cuenta Cliente");
            System.out.println("[2] Modificar cuenta");
            System.out.println("[3] Menu Reservas");
            System.out.println("[4] Eventos");
            System.out.println("[5] Salir");



                try {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");
                    entrada = scanner.nextLine();
                    scanner.reset();
                    opcion = Integer.parseInt(entrada);

                    if (opcion < 1 || opcion > 5) {

                        throw new IllegalArgumentException();
                    }

                } catch (NumberFormatException e) {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                } catch (IllegalArgumentException e) {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }


            switch (opcion) {
                case 1:
                    System.out.println("Detalles de su cuenta: ");
                    System.out.println(cliente.toString());

                    break;
                case 2:
                    gestionClientes.modificar(cliente,scanner);
                    break;

                case 3:
                    mostrarMenuReserva(cliente);

                    break;

                case 4: //eventos
                    //el cliente solo puede ver los eventos
                    System.out.println("Eventos Proximos: ");
                    System.out.println(gestionEventos.listar()); // LOS MUESTRA

                    break;

                case 5:
                    retener = false;
                    break;


            }
        }
    }

    private void mostrarMenuEmpleado(Empleado empleado) {
        boolean retener = true;
        int opcion = 0;
        String entrada = "";

        while (retener) {
            System.out.println("\n ----------------------------- Menu Empleado -------------------------------------- ");
            System.out.println("[1] Detalles de Cuenta Empleado");
            System.out.println("[2] Habitaciones");
            System.out.println("[3] Eventos");
            System.out.println("[4] Mostrar Balance");
            System.out.println("[5] Ver todos los clientes");
            System.out.println("[6] Dar de baja a un cliente");
            System.out.println("[7] Ver todos los empleados");
            System.out.println("[8] Ver todas las reservas");
            System.out.println("[9] Ver todos los Servicios Gastronomicos");
            System.out.println("[10] Agregar Servicio Gastronomico");
            System.out.println("[11] Modificar Servicio Gastronomico");
            System.out.println("[12] Eliminar Servicio Gastronomico");
            System.out.println("[13] Salir");

            while( opcion == 0 )
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13) --> ");
                    scanner.reset();
                    entrada = scanner.nextLine();

                    opcion = Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 13)
                    {

                        throw new IllegalArgumentException();
                    }

                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Detalles de la cuenta empleado:");
                    System.out.println(empleado.toString());
                }
                case 2 -> mostrarMenuHabEmpleado();
                case 3 -> //eventos
                    //el empleado puede ver y ademas modificar eventos
                        mostrarMenuEventosEmpleado();

                case 4 -> gestionEmpleados.gananciaTotal();

                case 5 -> gestionClientes.mostrarClientes();

                case 6 -> {
                    System.out.println("Ingrese el Id del cliente al que desea dar de baja");
                    entrada=scanner.nextLine();
                    Cliente aux=gestionClientes.buscarCliente(entrada);
                    if (aux!=null)
                    {
                        if(aux.isEstaActivo()){
                            System.out.println("¿Esat seguro que desea dar de baja a este cliente?");

                        }else {
                            System.out.println("Este cliente ya esta dado de baja");
                            System.out.println("¿Desea darlo de alta?");
                        }
                        System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                        entrada=scanner.nextLine();

                        if(entrada.equals("s")){
                            aux.setEstaActivo(!aux.isEstaActivo());
                            gestionClientes.guardarJson();
                        }
                    }else
                    {
                        System.out.println("ID no valido");
                    }
                }
                case 7 -> System.out.println(gestionEmpleados.getLista());

                case 8 -> System.out.println(gestionReservas.getLista());

                case 9 -> System.out.println(gestionServiciosGastronomicos.listar());//fijarseeee

                case 10 ->{
                    ServicioGastronomia servicioGastronomia = gestionServiciosGastronomicos.crearElemento();
                    gestionServiciosGastronomicos.agregar(servicioGastronomia);
                    gestionServiciosGastronomicos.guardarJson();
                }

                case 11 ->{
                    boolean bool = gestionServiciosGastronomicos.modificar("Canelones");
                    if(bool){
                        System.out.println("Modificacion realizada");
                    }
                    else{
                        System.out.println("No se encontro servicio");
                    }
                }

                case 12 -> {
                    boolean bool = gestionServiciosGastronomicos.eliminar("Trago");
                    if(bool){
                        System.out.println("Se ha eliminado");
                    }
                    else{
                        System.out.println("No se encontro servicio");
                    }
                }

                case 13 -> retener = false;


            }
            opcion=0;
        }
    }

    private void mostrarMenuEventosEmpleado() {
        int opcionEventos = 0;
        String entradaEventos = "";
        boolean retener=true;

        while (retener) {
            System.out.println("------------------------------ Eventos ------------------------------------");
            System.out.println("[1] Ver eventos proximos");
            System.out.println("[2] Agregar un evento");
            System.out.println("[3] Modificar un evento");
            System.out.println("[4] Eliminar un evento");
            System.out.println("[5] Salir");


            while (opcionEventos == 0) {


                try {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");
                    entradaEventos = scanner.nextLine();

                    opcionEventos = Integer.parseInt(entradaEventos);

                    if (opcionEventos < 1 || opcionEventos > 5) {

                        throw new IllegalArgumentException();
                    }

                } catch (NumberFormatException e) {
                    opcionEventos = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                } catch (IllegalArgumentException e) {
                    opcionEventos = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcionEventos) {
                case 1 -> {
                    System.out.println("Eventos Proximos: ");
                    System.out.println(gestionEventos.listar()); // LOS MUESTRA
                }
                case 2 -> { //crear un nuevo evento
                    Evento nuevo = gestionEventos.crearElemento();
                    System.out.println("** Se agregara el nuevo evento ** ");
                    System.out.println(nuevo);
                    gestionEventos.agregar(nuevo);
                    gestionEventos.guardarJson(); //GUARDA EN EL JSON EL NUEVO EVENTO
                }
                case 3 -> { //Modificar un evento por nombre de evento (SEGUIR)
                    System.out.print("Ingrese el nombre del evento a modificar --> ");
                    entradaEventos = scanner.nextLine();
                    gestionEventos.modificarEventoPorNombre(entradaEventos);
                    gestionEventos.guardarJson(); //GUARDA LAS MODIFICACIONES
                }
                case 4 -> { //eliminar un evento por nombre de evento
                    System.out.print("Ingrese el nombre del evento a eliminar --> ");
                    entradaEventos = scanner.nextLine();
                    gestionEventos.eliminarEventoPorNombre(entradaEventos);
                    gestionEventos.guardarJson(); //GUARDA LAS MODIFICACIONES
                }
                case 5 -> //salir
                        retener = false;
                default -> System.out.println("Opción inválida");
            }
            opcionEventos = 0;
        }
    }



    private void mostrarMenuHabEmpleado() {
        int opcion = 0, confirma = 0;
        String entrada; //= "" cambiado de String a in
        boolean retener = true;
        Habitacion aux;
        while (retener)
        {
            System.out.println("------------------------------ Habitaciones (empleado) ------------------------------------");
            System.out.println("[1] Agregar Habitacion");
            System.out.println("[2] Eliminar Habitacion");
            System.out.println("[3] Modificar Habitacion");
            System.out.println("[4] Reservar habitacion a un cliente");
            System.out.println("[5] Salir");

            while( opcion == 0 )
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");
                    scanner.reset();
                    entrada = scanner.nextLine();
                    opcion=Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 5)
                    {

                        throw new IllegalArgumentException();
                    }
                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }

            }

            switch (opcion) {
                case 1 -> {
                    Habitacion habitacion = gestionHabitaciones.crearElemento();
                    gestionHabitaciones.agregar(habitacion);
                    gestionHabitaciones.guardarJson();
                    System.out.println("Habitacion agregada con exito");
                }
                case 2 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(gestionHabitaciones.listar());
                    opcion = scanner.nextInt();
                    aux = gestionHabitaciones.buscarHabitacion(opcion);
                    if (aux != null) {
                        System.out.println("¿Esta seguro que quiere eliminar esta habitacion? Ingrese 0 para confirmar");
                        confirma = scanner.nextInt();
                        if (confirma == 0) {
                            gestionHabitaciones.eliminar(aux);
                        }
                    }else{
                        System.out.println("Opcion incorrecta. Elegir nuevamente la habitacion ");
                    }
                }
                case 3 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea modificar");
                    System.out.println(gestionHabitaciones.listar());
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    aux = gestionHabitaciones.buscarHabitacion(opcion);
                    if (aux != null) {
                        gestionHabitaciones.modificar(aux, scanner);
                    }
                }
                case 4 -> {
                    System.out.println(gestionClientes.getLista());
                    System.out.println("Ingrese el dni del cliente que quiere realizar una reserva:");
                    String dni = scanner.next();
                    Cliente cliente = gestionClientes.buscarCliente(dni);
                    mostrarMenuReserva(cliente);
                }

                case 5 -> retener = false;
                default -> System.out.println("Opcion no valida");
            }
            gestionHabitaciones.guardarJson();
            opcion=0;
        }
    }

    private void mostrarMenuReserva(Cliente cliente) {


        System.out.println("------------------Reserva Habitaciones ----------------------");
        System.out.println("[1] Ver todas las habitaciones ");
        System.out.println("[2] Ver Habitaciones disponibles por fecha");
        System.out.println("[3] Realizar una nueva reserva");
        System.out.println("[4] Ver mis reservas activas");
        System.out.println("[5] Cancelar mis reservas");
        System.out.println("[6] Pagar - Ordenar servicio a la habitacion");
        System.out.println("[7] Salir");

        System.out.print("\nIngrese su opción (1, 2, 3, 4 ,5) --> ");
        scanner.reset();
        int opcionReserva = scanner.nextInt();


        switch (opcionReserva) {
            case 1:
                System.out.println("Habitaciones con las que cuenta el hotel: ");
                System.out.println(gestionHabitaciones.listar());
                break;
            case 2:
                System.out.println("Fecha de checkin:");
                LocalDate checkin = gestionReservas.solicitarLocalDate();
                System.out.println("Fecha de checkout:");
                LocalDate checkOut =gestionReservas.solicitarLocalDate();
                System.out.println("");
                System.out.println(gestionReservas.verHabitacionesDisponiblesPorFechas(checkin,checkOut));
                break;
            case 3:
                gestionReservas.generarReserva(cliente);
                gestionReservas.cargarJson();
                break;
            case 4:
                gestionReservas.verMisReservasActivas(cliente.getDni());
                break;
            case 5:
                gestionClientes.anularReserva(gestionReservas,cliente);
                break;
            case 6:
                cliente.modificarReserva(gestionClientes,cliente);
                break;
            case 7: //salir
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
        this.gestionReservas.cargarJson();
    }

}
