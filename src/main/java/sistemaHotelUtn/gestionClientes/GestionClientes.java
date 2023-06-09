package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.CrearElementos;
import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;
import sistemaHotelUtn.gestionServiciosGastronomicos.GestionServiciosGastronomicos;
import sistemaHotelUtn.gestionServiciosGastronomicos.ServicioGastronomia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionClientes extends Gestion<Cliente> implements CrearElementos<Cliente> {
    public GestionClientes() {
        this.cargarJson();
    }

    public Cliente crearElemento() {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        /*Pide informacion por la consola y crea un nuevo cliente listo para agregar a la lista*/

        Cliente nuevo = new Cliente();

        System.out.println("**Crear Nuevo Cliente**");
        System.out.print("Ingrese su nombre --> ");
        entrada = scanner.nextLine();
        nuevo.setNombre(entrada);

        System.out.print("Ingrese su apellido --> ");
        entrada = scanner.nextLine();
        nuevo.setApellido(entrada);

        System.out.print("Ingrese su dni --> ");
        entrada = scanner.nextLine();
        nuevo.setDni(entrada);

        System.out.print("Ingrese su domicilio --> ");
        entrada = scanner.nextLine();
        nuevo.setDomicilio(entrada);

        System.out.print("Ingrese su telefono --> ");
        entrada = scanner.nextLine();
        nuevo.setTelefono(entrada);

        System.out.print("Ingrese un username --> ");
        String username = scanner.nextLine();

        System.out.print("Ingrese un password --> ");
        String password = scanner.nextLine();
        nuevo.generarUsuario(username, password, TipoUsuario.CLIENTE);

        nuevo.setId(this.getUltimoIdDisponible());

        return nuevo;
    }


    public Cliente buscarClientePorUsername(String username) {
        for (Cliente cliente : this.getLista()) {
            if (cliente.getUsuario().getUsername().equals(username)) {
                //si lo encuentra lo retorna
                return cliente;
            }
        }

        // si no lo encuentra retorna null
        return null;
    }

    public void guardarJson() {
        ArrayList<Cliente> clientesList = this.getLista();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes", clientesList, Cliente.class);
        clientesJson.guardar();
    }

    public void cargarJson() {
        ArrayList<Cliente> clientesList = new ArrayList<>();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes", clientesList, Cliente.class);
        clientesList = clientesJson.cargar();
        this.setLista(clientesList);
    }


    public void mostrarClientes() {
        int i = 1;
        for (Cliente cliente : this.getLista()) {
            if(cliente.isEstaActivo())
            System.out.println(cliente);
        }
    }

    public Cliente buscarCliente(String dni) {
        for (Cliente cliente : this.getLista()) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }







    public void anularReserva(GestionReservas gestionReservas, Cliente cliente){
        System.out.println("Ingrese su dni");
        String dni = new Scanner(System.in).next();

        Reserva reserva=gestionReservas.buscarReserva(cliente.getDni());//recibo la reserva de la persona
        System.out.println(reserva);

        System.out.println("Ingrese s para confirmar la anulacion");
        char eleccion = new Scanner(System.in).next().charAt(0);

        if(eleccion=='s'){
            reserva.setEstaActiva(false);
            gestionReservas.guardarJson();
            System.out.println("Su reserva fue anulada con exito");
            System.out.println("reserva = " + reserva);
        }
    }

    @Override
    public void modificar(Cliente target, Scanner scanner) {
        boolean retener=true;
        int opcionI;
        String entrada, opcionS;
        while (retener) {
            System.out.println("Ingrese el campo que quiere modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Domicilio");
            System.out.println("4. Telefono");
            System.out.println("5. Usuario");
            System.out.println("6. Contraseña");
            System.out.println("7. Dar de baja");
            System.out.println("8. Cancelar");

            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3, 4, 5, 6, 7, 8) --> ");

                entrada = scanner.nextLine();

                opcionI = Integer.parseInt(entrada);

                if( opcionI < 1 || opcionI > 8)
                {

                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionI = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionI = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }

            switch (opcionI) {
                case 1:
                    System.out.println("Nombre actual: " + target.getNombre());
                    System.out.println("Ingrese el nuevo nombre: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.setNombre(opcionS);
                    }

                    break;
                case 2:
                    System.out.println("Apellido actual: " + target.getApellido());
                    System.out.println("Ingrese el nuevo apellido: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.setApellido(opcionS);
                    }
                    break;
                case 3:
                    System.out.println("Domicilio actual: " + target.getDomicilio());
                    System.out.println("Ingrese el nuevo domicio: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.setDomicilio(opcionS);
                    }
                    break;
                case 4:
                    System.out.println("Telefono actual: " + target.getTelefono());
                    System.out.println("ingrese el nuevo telofono: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.setTelefono(opcionS);
                    }
                    break;
                case 5:
                    System.out.println("Usuario actual: " + target.getUsuario().getUsername());
                    System.out.println("Ingrese el nuevo usuario: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.getUsuario().setUsername(opcionS);
                    }
                    break;
                case 6:
                    System.out.println("Contraseña actual: "+target.getUsuario().getPassword());
                    System.out.println("Ingrese la nueva cotraseña: ");
                    opcionS=scanner.nextLine();
                    System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                    entrada=scanner.nextLine();

                    if(entrada.equals("s")){
                        target.getUsuario().setPassword(opcionS);
                    }
                    break;
                case 7:
                    if(target.isEstaActivo()) {
                        System.out.println("¿Desea darse de baja?");
                        System.out.println("Ingres 's' para confirmar, ingrese cualquier otro simbolo para denegar");
                        entrada = scanner.nextLine();
                        if (entrada.equals("s")) {
                            target.setEstaActivo(false);
                        }
                    }else {
                        System.out.println("Su cuenta ya esta dada de baja");
                    }
                    break;
                case 8:
                    retener=false;
                    break;
            }
            guardarJson();
        }
    }
}
