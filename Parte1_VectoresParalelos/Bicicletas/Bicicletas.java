import java.util.Scanner;
public class Bicicletas {


    static Scanner sc = new Scanner(System.in);
    static int b = 12;

    static int[] codigo = new int [b];
    static int[] cantPrestamos = new int [b];
    static int[] estado = new int [b];// 1 disponible, 2 prestada, 3 mantenimiento
    static int[] estacion = new int [b];// 4 estacionamientos (1,2,3,4)

    //mini-metodos
static boolean codigoRepetido(int cod, int hasta) {
        for (int j = 0; j < hasta; j++) {
            if (codigo[j] == cod) return true;
        }
        return false;
    }

static int menorPrestamos(int estacionbici){
    int menor = 999;
    int pos = -1;
    for (int i = 0; i < b; i++) {
        if (estacion[i] == estacionbici && estado[i] == 1 && cantPrestamos[i] < menor) {
            menor = cantPrestamos[i];
            pos = i;
        }
    }
    return pos;
}

static String nombreEstado(int i) {
    if (estado[i] == 1) return "Disponible";
    if (estado[i] == 2) return "Prestada";
    return "En mantenimiento";
    }

static void mostrarBicicleta(int i) {
    System.out.printf("%-18d %-10d %-18s %-2d%n",
            codigo[i], cantPrestamos[i], nombreEstado(i), estacion[i]);
}

static int bicisDisponiblesEstacion(int estacionbici){
    int count = 0;
    for (int i = 0; i < b; i++) {
        if (estacion[i] == estacionbici && estado[i] == 1) {
            count++;
        }
    }
    return count;
}

static void registrarDatosiniciales(){
        codigo[0] = 101; cantPrestamos[0] = 0; estado[0] = 1; estacion[0] = 1;
        codigo[1] = 102; cantPrestamos[1] = 0; estado[1] = 1; estacion[1] = 1;
        codigo[2] = 103; cantPrestamos[2] = 0; estado[2] = 1; estacion[2] = 1;
        codigo[3] = 104; cantPrestamos[3] = 0; estado[3] = 1; estacion[3] = 2;
        codigo[4] = 105; cantPrestamos[4] = 0; estado[4] = 1; estacion[4] = 2;
        codigo[5] = 106; cantPrestamos[5] = 0; estado[5] = 1; estacion[5] = 2;
        codigo[6] = 107; cantPrestamos[6] = 0; estado[6] = 1; estacion[6] = 3;
        codigo[7] = 108; cantPrestamos[7] = 0; estado[7] = 1; estacion[7] = 3;
        codigo[8] = 109; cantPrestamos[8] = 0; estado[8] = 1; estacion[8] = 3;
        codigo[9] = 110; cantPrestamos[9] = 0; estado[9] = 1; estacion[9] = 4;
        codigo[10] = 111;cantPrestamos[10] = 0;estado[10] = 1;estacion[10] =4;
        codigo[11] = 112;cantPrestamos[11] = 0;estado[11] = 1;estacion[11] =4;
    }
    
    
    //menu
    //1 prestamo
    static void prestarBicicleta() {
        System.out.print("Ingrese la estación donde quiere prestar: ");
        int est = sc.nextInt();
        int pos = menorPrestamos(est);
        if (pos != -1) {
            estado[pos] = 2;
            cantPrestamos[pos]++;
            System.out.println("Bicicleta prestada exitosamente. Codigo: " + codigo[pos]);
        } else if (est > 4) {
            System.out.println("Estacion no valida");
        }else{
            System.out.println("No hay bicicletas disponibles en la estacion " + est + ".");
        }
    }

    //2
    static boolean registrarDevolucion(int codigoBicicleta , int parada){
        int c = 0;
        int posicion= -1;
        for(int i = 0; i < b; i++){
            c += (estacion[i] == parada && estado[i] == 1) ? 1 : 0;
            if(codigo[i] == codigoBicicleta && estado[i] == 2){
                posicion = i;
            }
        }

        if(c < 3){
            estacion[posicion] = parada;
            estado[posicion] = 1;
            return true;
        }else {
            return false;
        }
    }

    //3
    static void enviarMantenimiento(){
        System.out.print("Ingrese el codigo de la bicicleta a enviar a mantenimiento: ");
        int cod = sc.nextInt();

        if(cod < 101 || cod > 112){
            System.out.print("codigo invalido");
        }

        for (int i = 0; i < b; i++) {
            if(codigo[i] == cod) {
                if (estado[i] == 1) {
                    estado[i] = 3;
                    System.out.println("Bicicleta enviada a mantenimiento exitosamente");
                } else {
                    System.out.println("Bicicleta no disponible para enviar a mantenimiento");
                }
            }
        }
    }
    
    //4
    static void habilitarBicicleta(){
        System.out.print("Ingrse el codigo de la bicicleta a habilitar: ");
        int cod = sc.nextInt();
        for (int i = 0; i < b ; i++){
            if(codigo[i] == cod){
                if(estado[i] == 3){
                    estado[i] = 1;
                    System.out.println("Bicicleta habilitada exitosamente");
                } else {
                    System.out.println("Bicicleta no esta en mantenimiento, no se puede habilitar");
                }
            }
        }
    }
    
    //5
    static void verDisponibles(){
        System.out.printf("%-18s %-10s %-18s %-2s%n", "Codigo", "Prestamos", "Estado", "Estacion");
        for (int i = 0; i < b; i++) {
            if(estado[i] == 1) {
                mostrarBicicleta(i);
            }
        }
    }

    //6
    static void resumenFinal(){
    for (int i = 0; i < 4; i++) {
        System.out.println("Total de bicicletas disponibles en la estacion " + (i + 1) + ": " + bicisDisponiblesEstacion(i + 1));
    }

    System.out.println("\nBicicletas prestadas:");
    int canPres = 0;
    for (int i = 0; i < b; i++) {
        if (estado[i] == 2) {
            System.out.println("Codigo " + codigo[i] + " (ultima estacion: " + estacion[i] + ")");
            canPres++;
        }
    }
    if (canPres == 0) {
        System.out.println("Ninguna bicicleta esta prestada.");
    }

    int mayor = 0;
    for (int i = 0; i < b; i++) {
        if (cantPrestamos[i] > mayor) {
            mayor = cantPrestamos[i];
        }
    }

    if (mayor == 0) {
        System.out.println("\nNo se realizo ningun prestamo.");
    } else {
        System.out.println("\nBicicleta(s) con mayor cantidad de prestamos (" + mayor + "):");
        for (int i = 0; i < b; i++) {
            if (cantPrestamos[i] == mayor) {
                System.out.println("Codigo " + codigo[i]);
            }
        }
    }
}

    //main
    public static void main(String[] args) {
        registrarDatosiniciales();

        int opcion = 0;
        while (opcion < 6) {
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
                    prestarBicicleta();
                    break;
                case 2:
                    System.out.print("Ingrese la estacion a devolver: ");
                    int parada = sc.nextInt();
                    System.out.print("Ingrese el codigo de la bicileta: ");
                    int bicicleta = sc.nextInt();
                    registrarDevolucion(bicicleta, parada);
                    if(registrarDevolucion(bicicleta, parada)== true) {
                        System.out.println("Devolucion registrada exitosamente");
                    } else {
                        System.out.println("No se pudo registrar la devolucion");
                    }
                    
                    break;
                case 3:
                    enviarMantenimiento();
                    break;
                case 4:
                    habilitarBicicleta();
                    break;
                case 5:
                    verDisponibles();
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