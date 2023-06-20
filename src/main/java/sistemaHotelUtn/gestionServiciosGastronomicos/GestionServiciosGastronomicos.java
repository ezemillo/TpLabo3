package sistemaHotelUtn.gestionServiciosGastronomicos;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;

import java.util.ArrayList;
import java.util.List;

public class GestionServiciosGastronomicos extends Gestion<ServicioGastronomia> {
    public GestionServiciosGastronomicos() {
        ArrayList<ServicioGastronomia> gastronomiasList = new ArrayList<>();
        JsonRepo<ServicioGastronomia> gastronomiaJson = new JsonRepo<>("serviciosGastronomicos", gastronomiasList, ServicioGastronomia.class);
        gastronomiasList = gastronomiaJson.cargar();
        this.setLista(gastronomiasList);
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

    public void mostrarServicio(List<ServicioGastronomia> listado) {
        int i = 0;
        for (ServicioGastronomia servicioGastronomia : listado) {
            System.out.println(i + ") " + servicioGastronomia);
            i++;
        }
    }
}
