package Parte2_VectoresDeObjetos.Robot;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int opcion = 0;

    public static void main(String[] args ){
        int[] iden       = {1, 2, 3, 4, 5};
        int[] posX      = {0, 3, 3, 10, -2};
        int[] posY      = {0, 4, 4, 2, 5};
        int[] energias  = {50, 30, 80, 100, 10};

        opcion = 0;
        while(opcion < 4 ){
            System.out.println("\n--- ROBOTS MENSAJEROS ---");
            System.out.println("1. Mover Robot");
            System.out.println("2. Transferir energía ");
            System.out.println("3. Consultar estado");
            System.out.println("4. Finalizar");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mover();
                break;

                case 2:
                    //transferir();
                break;

                case 3:
                    //consultar();
                break;

                case 4:
                    //finalizar();
                break;
            
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    //mini-metdos#2

    static void mover(){
        System.out.print("Ingrese el identificador del robot a mover: ");
        int id = sc.nextInt();

        for(int i = 0 ; i < 5;i++){
            if(iden[i] == id )
        }
    }

    static  void transferir(){

    }
















}
