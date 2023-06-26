package sistemaHotelUtn.generales;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Gestion<T> implements gestionClases{
    private ArrayList<T> lista = new ArrayList<>();

    public void agregar(T elem) {
        this.lista.add(elem);
    }

    public String listar() {
        String listaString = "";

        for (T elem : lista) {
            listaString += elem.toString() + "\n";
        }

        return listaString;
    }

    public void eliminar(T target) {
        for (T elem : lista) {
            if (elem == target) {
                lista.remove(elem);
                break;
            }
        }
    }

    public void modificar(T target, Scanner scanner) {}

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }

    public ArrayList<T> getLista() {
        return lista;
    }

}
