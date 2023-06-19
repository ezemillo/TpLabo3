package sistemaHotelUtn.generales;

import java.util.ArrayList;

abstract public class Gestion<T>
{
    private ArrayList<T> lista = new ArrayList<>();
    private String pathJson = ""; //esto lo define luego cada gestion con su json

    public void agregar(T elem)
    {
        this.lista.add(elem);
    }

    public String listar()
    {
        String listaString = "";

        for(T elem: lista)
        {
            listaString += elem.toString() + "\n";
        }

        return listaString;
    }

    public void eliminar(T target)
    {
        for(T elem: lista)
        {
            if( elem == target )
            {
                lista.remove(elem);
                break;
            }
        }
    }

    public void modificar(T target, String campo)
    {
        for(T elem: lista)
        {
            if( elem == target )
            {
                System.out.println("Ingrese el campo a modificar --> ");
                //se cambia el atributo.
                break;
            }
        }
    }

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }

    public void setPathJson(String pathJson) {
        this.pathJson = pathJson;
    }

    public ArrayList<T> getLista()
    {
        return lista;
    }

}
