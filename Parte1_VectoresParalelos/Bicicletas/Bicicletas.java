import java.util.Scanner;
public class Bicicletas {


    static Scanner sc = new Scanner(System.in);
    static int b = 12;

    static int[] codigo = new int [b];
    static int[] cantPrestamos = new int [b];// num
    static int[] estado = new int [b];// 1 disponible, 2 prestada, 3 mantenimiento
    static int[] estacion = new int [b];// 4 estacionamientos (1,2,3,4)

    //mini-metodos
    static boolean codigoRepetido(int cod, int hasta) {
        for (int j = 0; j < hasta; j++) {
            if (codigo[j] == cod) return true;
        }
        return false;
    }

    static verificarEstado(int i) {
        switch (estado[i]) {
            case 1:
                System.out.println("Disponible");
                break;
            case 2:
                System.out.println("Prestada");
                break;
            case 3:
                System.out.println("En mantenimiento");
                break;
        }
    }

    static int contarPrestamos(int i) {
            
    }

    static void mostrarBicicleta(int i) {
        System.out.printf("%-8d %-10d %-8s %-8d%n",
                codigo[i], cantPrestamos[i],
                (estado[i] == 1 ? "Disponible" : (estado[i] == 2 ? "Prestada" : "En mantenimiento")),
                estacion[i]);
    }


    static void registrarDatosiniciales(){
        codigo[0] = 101; cantPrestamos[0] = 5; estado[0] = 1; estacion[0] = 1;
        codigo[1] = 102; cantPrestamos[1] = 2; estado[1] = 2; estacion[1] = 2;
        codigo[2] = 103; cantPrestamos[2] = 3; estado[2] = 3; estacion[2] = 3;
        codigo[3] = 104; cantPrestamos[3] = 4; estado[3] = 1; estacion[3] = 4;

        int i=4;
        boolean continuar = true;
        System.out.println("¿Desea registrar una bicicleta? (1 si, 0 no): ");
        continuar = sc.nextInt() == 1;
        while (continuar && i < 12 ) {
            System.out.println("Bicicleta " + (i + 1));

            do {
                System.out.print("Codigo: ");
                codigo[i] = sc.nextInt();
            } while (codigoRepetido(codigo[i], i));

            do {
                System.out.print("Cantidad de prestamos: ");
                cantPrestamos[i] = sc.nextInt();
            } while (cantPrestamos[i] < 0);

            do {
                System.out.print("Estado (1 disponible, 2 prestada, 3 en reparación): ");
                estado[i] = sc.nextInt();
            } while (estado[i] < 1 || estado[i] > 3);

            do {
                System.out.print("Estacion (1, 2, 3, 4): ");
                estacion[i] = sc.nextInt();
            }while (estacion[i] < 1 || estacion[i] > 4);
        }
    }

    //menu
    //1 pretamo

    static 














    //main
    public static void main(String[] args) {
        registrarDatosiniciales();

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n1 Prestar una bicicleta");
            System.out.println("2 Devolver una bicicleta");
            System.out.println("3 Enviar a mantenimiento");
            System.out.println("4 Habilitar bicicleta");
            System.out.println("5 Consultar disponibles");
            System.out.println("6 Resumen final");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    verDisponibles();
                    break;
                case 2:
                    devolverBicicleta();
                    break;
                case 3:
                    enviarMantenimiento();
                    break;
                case 4:
                    habilitarBicicleta();
                    break;
                case 5:
                    resumenFinal();
                    break;
                case 6:
                    resumenFinal();
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    
}



//para despues :
//preguntar si esta disponible , si si lo esta enviar a mantenimiento, si no esta disponible no se puede enviar a mantenimiento
//preguntar si esta en mantenimiento, si si lo esta habilitarla, si no esta en mantenimiento no se puede habilitar

//crear un mini metodo para contar la cantidad de prestamos de cada bicicleta
//
