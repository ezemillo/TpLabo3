package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Gestion;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionClientes extends Gestion<Cliente>
{
    public GestionClientes(){}

    public void menuCliente(Cliente cliente)
    {
        boolean retener = true;
        int opcion=0;
        Scanner scanner= new Scanner(System.in);
        while (retener)
        {
            System.out.println("Eliga una opcion:");
            System.out.println("1. Cuenta");
            System.out.println("2. Habitaciones");
            System.out.println("0. Salir");
            opcion=scanner.nextInt();
            switch (opcion)
            {
                case 1:

                    break;
                case 2:
                    break;
                case 0:
                    retener=false;
                    break;
            }
        }
    }
}
