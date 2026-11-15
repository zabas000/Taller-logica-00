package Parte2_VectoresDeObjetos.room;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    
    static Equipo[] equipos = new Equipo[4];
    

    public static void main(String[] args) {
        int actual = 0;
        equipos[0] = new Equipo("Equipo 1");
        equipos[1] = new Equipo("Equipo 2");
        equipos[2] = new Equipo("Equipo 3");
        equipos[3] = new Equipo("Equipo 4");

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n--- ESCAPE ROOM ---");
            System.out.println("1. Seleccionar equipo");
            System.out.println("2. Registrar respuesta");
            System.out.println("3. Solicitar pista");
            System.out.println("4. Consultar estado");
            System.out.println("5. Terminar jornada");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    seleccionarEquipo();
                    break;
                case 2:
                    //respuesta();
                    break;
                case 3:
                    //pista();
                    break;
                case 4:
                    System.out.println(equipos[actual].getEstado());
                    break;
                case 5:
                    System.out.println("Jornada terminada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        //1resultados();
    }

    static void seleccionarEquipo(){
        //muestra todos los equipos
        for(int i = 0 ; i < 4; i++){
            System.out.println(equipos[i].getNombre());
        }
        // pide seleccionar uno de esos mostrados 
        System.out.print("Seleccione un equipo: ");
        int selec = sc.nextInt()-1;
        if(selec <= 1 && selec >= 4){
            System.out.print("Equipo no valido");
        }else{
            System.out.print("Se selecciono el equipo: "+ equipos[selec].getNombre());
        }
        
    }



    
}