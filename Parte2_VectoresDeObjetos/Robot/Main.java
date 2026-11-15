package Parte2_VectoresDeObjetos.Robot;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int opcion = 0;
    static Robot[] robots = new Robot[5];
    static int[] identificador = {1, 2, 3, 4, 5};
    static int[] x1 = {0, 3, 3, 10, -2};
    static int[] y1 = {0, 4, 4, 2, 5};
    static int[] energiaDispo = {50, 30, 80, 100, 10};
    public static void main(String[] args ){
        
        for (int i = 0; i < 5; i++) {
            robots[i] = new Robot(identificador[i], x1[i], y1[i], energiaDispo[i]);
        }

        opcion = 0;
        while(opcion < 5 ){
            System.out.println("\n--- ROBOTS MENSAJEROS ---");
            System.out.println("1. Mover Robot");
            System.out.println("2. Transferir energía ");
            System.out.println("3. Consultar estado");
            System.out.println("4. atendner Destino");
            System.out.println("5. Finalizar");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mover();
                break;

                case 2:
                    transferir();
                break;

                case 3:
                    consultar();
                break;

                case 4:
                    atenderDestino();
                break;

                case 5:
                    System.out.print("Finalizado...");
                break;
            
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    //mini-metdos#2
    static int buscarId(int id){
        for(int i = 0 ; i < 5 ;i++ ){
            if(identificador[i] == id){
                return i;
            }
        }
        return -1;
    }
    

    //1
    static void mover(){
        System.out.print("Ingrese el identificador del robot a mover: ");
        int id = buscarId(sc.nextInt());
        System.out.print("Ingrese la componente en x  y a mover: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        if(id== -1 ){
            System.out.println("El robot no existe");
        }
        if(robots[id].moverA(x, y)){
            System.out.println("Se pudo mover el robot"+ robots[id].getEstado());
        }else{
            System.out.println("No pudo moverse");
        }
    }

    //2
    static void transferir(){
        System.out.print("Ingrse el identificador del robot donante: ");
        int d = buscarId(sc.nextInt());

        System.out.print("Ingrese el identificador del robot receptor: ");
        int r = buscarId(sc.nextInt());

        System.out.print("Ingrese la cantidad de energía a transferir: ");
        int cantidad = sc.nextInt();

        if(d == -1 || r == -1){
            System.out.println("Algun id de robot no existe");
        }

        if(robots[d].transferirEnergia(robots[r], cantidad)){
            System.out.println("Transferencia exitosa.");
            System.out.println("Estado robot donante "+robots[d].getEstado());
            System.out.println("Estado robot receptor "+robots[r].getEstado());
        }else{
            System.out.println("Transferencia infructuosa");
        }
    }

    //3
    static void consultar(){
        for(int i = 0 ; i < 5 ;i++ ){
            System.out.println(robots[i].getEstado());
        }
    }

    //4
        static void atenderDestino() {
        System.out.print("Ingrese la coordenada x destino: ");
        int x = sc.nextInt();
        System.out.print("Ingrese la coordenada y destino: ");
        int y = sc.nextInt();

        int mejorDist = 999;
        int mejorEnergia = 0;
        String resultado = "Ningun robot puede llegar a ese destino";

        for (int i = 0; i < 5; i++) {
            int dist = robots[i].distanciaHasta(x, y);
            int energia = robots[i].getEnergiaDisponible();

            if (dist <= energia) { 
                if (dist < mejorDist || (dist == mejorDist && energia > mejorEnergia)) {
                    mejorDist = dist;
                    mejorEnergia = energia;
                    resultado = "Robot que atiende: " + robots[i].getEstado();
                }
            }
        }

        System.out.println(resultado);
    }

}
