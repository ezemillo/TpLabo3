package sistemaHotelUtn.generales.Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonRepo <T> {

    private String nombreArchivo;
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<T> lista;
    private Class dato;

    public JsonRepo(String nombreArchivo, ArrayList<T> lista, Class dato) {
        this.nombreArchivo = nombreArchivo;
        this.lista = lista;
        this.dato=dato;
    }

    public JsonRepo() {
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }

    public ArrayList<T> cargar() {

        ArrayList<T> lista = null;
        try {
            File archivo = new File("src\\main\\java\\sistemaHotelUtn\\jsonFiles\\"+ this.nombreArchivo +".json");
            mapper.registerModule(new JavaTimeModule());
            mapper.findAndRegisterModules();
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, dato);
            lista = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            System.out.println("No se cargaron registros desde " + this.nombreArchivo );
            lista = new ArrayList<T>();
        }
        return lista;
    }
    public void guardar() {

        try {
            File archivo = new File("src\\main\\java\\sistemaHotelUtn\\jsonFiles\\"+ this.nombreArchivo +".json");
            mapper.registerModule(new JavaTimeModule());
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
