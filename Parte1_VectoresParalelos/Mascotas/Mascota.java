import java.util.Scanner;

public class Mascota {


    public static void main(String[] args) {
        int opcion = 0;
        while (opcion!=5) {
            System.out.print("1 Ver disponibles");
            System.out.print("2 Registrar Vacunacion");
            System.out.print("3 Adoptar");
            System.out.print("4 Buscar por expecie y edad maxima");
            System.out.print("5 terminar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            
            
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
        Scanner sc = new Scanner(System.in);
        int m = 15;
        String[] nombre = new  String [m];
        int[] especie = new int [m];
        int[] edad = new  int [m];
        boolean[] vacuna  = new boolean [m];
        boolean[] adoptada  = new boolean [m];
        int [] codigo =  new  int [m];
        

        nombre = {"Max","Luna","Rocky","Mia","Toby"};
        static int[] especie = {1,2,1,2,1};
        static int[] edad = {12,2,5,8,1};
        static boolean[] vacuna= {true,false,true,true,false};
        static boolean[] adoptada = new boolean[m];// false = disponible
        static int[] codigo = {101,102,103,104,105};
        
      //mini-metodos

        static int buscarPorCodigo(int cod){
            for(int i = 0 ; i < m ; i++){
                if (codigo[i] == cod)
                    return i;
            }
            return -1;
        }


        static boolean cumpleCondiciones(int i){
            return !adoptada[i] && edad[i]>=3 && vacuna[i]
        }

        static void mostrarMascota(int i){
        System.out.println(codigo[i] + " , " + nombre[i] + " , "
                + (especie[i] == 1 ? "Perro" : "Gato") + " , "
                + edad[i] + " meses , vacunada: " + (vacunada[i] ? "Si" : "No")
                + " , apta para adopcion: " + (cumpleCondiciones(i) ? "Si" : "No"));
        }


        //menu-metodos
        //1
       static void consultarDisponibles(){
        int c = 0;
        for(int i = 0; i < m ; i++ ){
            if(!adoptada[i]){
                mostrarMascota(i);
                c++
            }
        }
        if(c==0) System.out.print("No hay mascotas disponibles")
       }


        //2
       static void registrarVacunacion() {
        System.out.print("Codigo: ");
        int code = sc.nextInt();
        int pos = buscarPorCodigo(code);
        if (pos == -1) {
            System.out.println("Codigo no encontrado.");
        } else if (vacunada[pos]) {
            System.out.println("Ya esta vacunada. Datos conservados.");
        } else {
            vacunada[pos] = true;
            System.out.println("Vacunacion registrada.");
        }




        //3








        
        
        
        public static int verDisponibles( ){
            int c = 0;
            for(int i ; i< m ; i++ ){

            }
        
        }










    }

}


