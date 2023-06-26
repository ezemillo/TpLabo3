package sistemaHotelUtn.gestionServiciosGastronomicos;

import sistemaHotelUtn.generales.CrearElementos;
import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionServiciosGastronomicos extends Gestion<ServicioGastronomia> implements CrearElementos<ServicioGastronomia> {
    public GestionServiciosGastronomicos() {

        cargarJson();
    }

    public List<ServicioGastronomia> CargarServicios(String tipo) {
        List<ServicioGastronomia> listaGastronomia = new ArrayList<>();
        for (ServicioGastronomia servicioGastronomia : this.getLista()) {
            if (servicioGastronomia.getTipo().equals(tipo)) {
                listaGastronomia.add(servicioGastronomia);
            }
        }
        return listaGastronomia;
    }
    public void guardarJson(){
        ArrayList<ServicioGastronomia> gastronomiasList = this.getLista();
        JsonRepo<ServicioGastronomia> gastronomiaJson = new JsonRepo<>("serviciosGastronomicos",gastronomiasList,ServicioGastronomia.class);
        gastronomiaJson.guardar();
    }

    public void cargarJson(){
        ArrayList<ServicioGastronomia> gastronomiasList = new ArrayList<>();
        JsonRepo<ServicioGastronomia> gastronomiaJson = new JsonRepo<>("serviciosGastronomicos", gastronomiasList, ServicioGastronomia.class);
        gastronomiasList = gastronomiaJson.cargar();
        this.setLista(gastronomiasList);
    }

    public void mostrarServicio(List<ServicioGastronomia> listado) {
        int i = 1;
        for (ServicioGastronomia servicioGastronomia : listado) {
            System.out.println(i + ") " + servicioGastronomia);
            i++;
        }
    }
    public boolean modificar (String nombre){
        for (ServicioGastronomia servicioGastronomia : this.getLista()){
            if(servicioGastronomia.getNombre().equals(nombre)){
                System.out.println("ingrese tipo");
                servicioGastronomia.setTipo(new Scanner(System.in).nextLine());
                System.out.println("ingrese nombre");
                servicioGastronomia.setNombre(new Scanner(System.in).next());
                System.out.println("ingrese precio");
                servicioGastronomia.setPrecio(new Scanner(System.in).nextDouble());
                guardarJson();
                return true;
            }
        }
        return false;
    }

    public ServicioGastronomia crearElemento (){
        ServicioGastronomia servicioGastronomia = new ServicioGastronomia();
        System.out.println("ingrese tipo");
        servicioGastronomia.setTipo(new Scanner(System.in).nextLine());
        System.out.println("ingrese nombre");
        servicioGastronomia.setNombre(new Scanner(System.in).next());
        System.out.println("ingrese precio");
        servicioGastronomia.setPrecio(new Scanner(System.in).nextDouble());


        return servicioGastronomia;
    }

    public boolean eliminar (String nombre){
        for (ServicioGastronomia servicioGastronomia : this.getLista()){
            if(servicioGastronomia.getNombre().equals(nombre)){
                this.eliminar(servicioGastronomia);
                guardarJson();
                return true;
            }
        }
        return false;
    }

}
