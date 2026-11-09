import java.util.Scanner;

public class Mascota {

    int [] codigo =  new  int [m];
    String[] nombre = new  String [m];
    int[] especie = new int [m];
    int[] edad = new  int [m]
    boolean[] vacuna  = new boolean [m];
    boolean[] adoptada  = new boolean [m];
    Scanner sc = new Scanner(System.in);
    int m = 15;
    
    public static void main(String[] args) {

        int opcion = 0;

        while (opcion!=5) {
            System.out.print("1 Ver disponibles");
            System.out.print("2 Registrar Vacunacion");
            System.out.print("3 Adoptar");
            System.out.print("4 Buscar por expecie y edad maxima");
            System.out.print("5 terminar");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            
            
            switch (opcion) {
            System.out.print("Menu");
            case 1:
                verDisponibles();
                break;
            case 2:
                vacunar();
                break;
            case 3:
                adoptar();
                break;
            case 4:
                buscar();
                break;
            case 5:
                System.out.print("Terminando...");
                break;
            default:
                System.out.print("Opcion invalida");
        }

        }
        
    }




    public void menu (int codigo[],String nombre[] ,int especie[],int edad[] ,boolean vacuna[],boolean adopcion[] ){
        Scanner sc = new Scanner (System.in);
        int op;
        

    }


    public class operaciones(){
        public void consultarMascotas(){

        }

    }







}


