package Parte2_VectoresDeObjetos.room;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int selec = 0;
    static Equipo[] equipos = new Equipo[4];
    

    public static void main(String[] args) {
        equipos[0] = new Equipo("Equipo 1");
        equipos[1] = new Equipo("Equipo 2");
        equipos[2] = new Equipo("Equipo 3");
        equipos[3] = new Equipo("Equipo 4");

        int opcion = 0;
        while (opcion < 5) {
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
                    validarRespuesta();
                    break;
                case 3:
                    pedirPista();
                    break;
                case 4:
                    System.out.println(equipos[selec].getEstado());
                    break;
                case 5:
                    System.out.println("Jornada terminada.");
                    ganador();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        //1resultados();
    }

    //1
    static void seleccionarEquipo(){
        //muestra todos los equipos
        for(int i = 0 ; i < 4; i++){
            System.out.println(equipos[i].getNombre());
        }
        // pide seleccionar uno de esos mostrados
        System.out.print("Seleccione un equipo: ");
        selec = sc.nextInt()-1;
        if(selec< 0 || selec > 3){
            System.out.print("Equipo no valido");
        }else{
            System.out.print("Se selecciono el equipo: "+ equipos[selec].getNombre());
        }
    }

    //2
    static void validarRespuesta(){
        System.out.print("La respuesta es correcta ?(1: si, 2: no): ");
        int opcion = sc.nextInt();

        if(!equipos[selec].registrarRespuesta(opcion == 1 )){
            System.out.print("Equipo ya terminó");
        }
    }

    //3
    static void pedirPista(){
        if(equipos[selec].solicitarPista()){
            System.out.print("Pista utilizada en el "+equipos[selec].getNombre());
        }else{
            System.out.print("el "+equipos[selec].getNombre()+" no tiene pistas disponibles");
        }
    }

    //5
    static void ganador(){
        for(int i = 0 ; i < 4; i++){
            System.out.println(equipos[i].getEstado());
        }

        int mejorA = equipos[0].getAcertijosResueltos();
        int mejorP = equipos[0].getPuntaje();

        for(int i = 0;i<4; i++){
            int a = equipos[i].getAcertijosResueltos();
            int p = equipos[i].getPuntaje();

            if(a > mejorA || (a == mejorA && p > mejorP)){
                mejorA = a;
                mejorP = p;
            }
        }

        System.out.println("\nGanador(es): ");
        for (int i = 0; i <4; i++) {
            if (equipos[i].getAcertijosResueltos() == mejorA && equipos[i].getPuntaje() == mejorP) {
                System.out.println("- " + equipos[i].getNombre()+" con "+equipos[i].getPuntaje()+" puntos");
            }
        }
    }

    
}