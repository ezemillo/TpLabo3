package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.CrearElementos;
import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleados extends Gestion<Empleado> implements CrearElementos <Empleado> {
    public GestionEmpleados() {
        cargarJson();
    }

    public Empleado crearElemento() {
        /*Pide informacion por la consola y crea un nuevo empleado listo para agregar a la lista*/

        Scanner scanner = new Scanner(System.in);
        String entrada = "";

        Empleado nuevo = new Empleado();

        System.out.println("**Crear Nuevo Empleado**");
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
        nuevo.generarUsuario(username, password, TipoUsuario.EMPLEADO);

        nuevo.setId(this.getUltimoIdDisponible());

        return nuevo;
    }

    public Empleado buscarEmpleadoPorUsername(String username) {

        for (Empleado empleado : this.getLista()) {
            if (empleado.getUsuario().getUsername().equals(username)) {
                //si lo encuentra lo retorna
                return empleado;
            }
        }

        // si no lo encuentra retorna null
        return null;
    }

    public void guardarJson() {
        ArrayList<Empleado> empleadosList = this.getLista();
        JsonRepo<Empleado> empleadosJson = new JsonRepo<>("empleados", empleadosList, Empleado.class);
        empleadosJson.guardar();
    }

    public void cargarJson() {
        ArrayList<Empleado> empleadosList = new ArrayList<>();
        JsonRepo<Empleado> empleadosJson = new JsonRepo<>("empleados", empleadosList, Empleado.class);
        empleadosList = empleadosJson.cargar();
        this.setLista(empleadosList);
    }

    public void gananciaTotal() {

        double ganancia = 0.00;
        GestionReservas gestionReservas = new GestionReservas();
        for (Reserva reserva : gestionReservas.getLista()) {
            ganancia = ganancia + reserva.getMontoPagar();
        }
        System.out.println("La ganancia de todas las reservas asciende a $ " + ganancia);
    }

}
