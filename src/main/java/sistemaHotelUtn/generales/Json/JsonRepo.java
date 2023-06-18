package sistemaHotelUtn.generales.Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonRepo <T> {

    private String nombreArchivo;

    private final ObjectMapper mapper = new ObjectMapper();

    private List<T> lista;

    private Class dato;


    public JsonRepo(String nombreArchivo, List<T> lista, Class dato) {
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

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public List<T> cargar() {

        List<T> lista = null;
        try {
            File archivo = new File("src\\main\\java\\sistemaHotelUtn\\jsonFiles\\"+ this.nombreArchivo +".json");
            mapper.registerModule(new JavaTimeModule());
            mapper.findAndRegisterModules();
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, dato);
            lista = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            System.out.println("entra en la excepcion " + e.getMessage() );
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
