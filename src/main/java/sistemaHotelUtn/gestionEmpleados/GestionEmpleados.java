package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.Gestion;

import java.util.Scanner;

public class GestionEmpleados extends Gestion<Empleado>
{
    public GestionEmpleados(){}

    public void menuEmpleado(Empleado empleado){
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
