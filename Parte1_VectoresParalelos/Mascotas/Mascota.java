import java.util.Scanner;

public class Mascota {

    static Scanner sc = new Scanner(System.in);
    static int m = 15;

    static String[] nombre = new String[m];
    static int[] especie = new int[m];          // 1 perro, 2 gato
    static int[] edad = new int[m];
    static boolean[] vacuna = new boolean[m];
    static boolean[] adoptada = new boolean[m]; // false = disponible
    static int[] codigo = new int[m];


    static  int  cantidadM = 5; // cantidad de mascotas registradas
    
    //mini-metodos
    static int buscarPorCodigo(int cod) {
        for (int i = 0; i < cantidadM; i++) {
            if (codigo[i] == cod) return i;
        }
        return -1;
    }

    static boolean codigoRepetido(int cod, int hasta) {
        for (int j = 0; j < hasta; j++) {
            if (codigo[j] == cod) return true;
        }
        return false;
    }

    //apta = disponible, edad >= 3, vacunada
    static boolean cumpleCondiciones(int i) {
        return !adoptada[i] && edad[i] >= 3 && vacuna[i];
    }

static void mostrarMascota(int i) {
    System.out.printf("%-8d %-10s %-8s %-8d %-10s %s%n",
            codigo[i], nombre[i],
            (especie[i] == 1 ? "Perro" : "Gato"),
            edad[i],
            (vacuna[i] ? "Si" : "No"),
            (cumpleCondiciones(i) ? "Si" : "No"));
}

    static int contarM(int tipo){
        int cantidad = 0;
        for (int i = 0; i < cantidadM; i++) {
            if (especie[i] == tipo && adoptada[i]) 
                cantidad++;
        }
        return cantidad;
    }

    //datos iniciales
    static void registrarDatosIniciales() {
        codigo[0] = 101; nombre[0] = "Max";   especie[0] = 1; edad[0] = 12; vacuna[0] = true;  adoptada[0] = false;
        codigo[1] = 102; nombre[1] = "Luna";  especie[1] = 2; edad[1] = 2;  vacuna[1] = false; adoptada[1] = false;
        codigo[2] = 103; nombre[2] = "Rocky"; especie[2] = 1; edad[2] = 5;  vacuna[2] = true;  adoptada[2] = false;
        codigo[3] = 104; nombre[3] = "Mia";   especie[3] = 2; edad[3] = 8;  vacuna[3] = true;  adoptada[3] = false;
        codigo[4] = 105; nombre[4] = "Toby";  especie[4] = 1; edad[4] = 3;  vacuna[4] = false; adoptada[4] = false;

        // 10 teclado
        int i=5;
        boolean continuar = true;
        System.out.println("¿Desea registrar mascotas? (1 si, 0 no): ");
        continuar = sc.nextInt() == 1;
        while (continuar && i < 15 ) {
            System.out.println("Mascota " + (i + 1));

            do {
                System.out.print("Codigo: ");
                codigo[i] = sc.nextInt();
            } while (codigoRepetido(codigo[i], i));

            System.out.print("Nombre: ");
            nombre[i] = sc.next();

            do {
                System.out.print("Especie (1 perro, 2 gato): ");
                especie[i] = sc.nextInt();
            } while (especie[i] != 1 && especie[i] != 2);

            do {
                System.out.print("Edad en meses: ");
                edad[i] = sc.nextInt();
            } while (edad[i] <= 0);

            System.out.print("Vacunada (1 si, 0 no): ");
            vacuna[i] = sc.nextInt() == 1;

            System.out.print("Adoptada (1 si, 0 no): ");
            adoptada[i] = sc.nextInt() == 1;

            System.out.print("Desea registrar otra mascota? (1 si, 0 no): ");
            continuar = sc.nextInt() == 1;
            i++;
            cantidadM++;
            }
        }


    // menu-metodos
    // 1
    static void verDisponibles() {
        System.out.println("\n--------------- MASCOTAS DISPONIBLES ---------------");
        System.out.printf("%-8s %-10s %-8s %-8s %-10s %s%n",
                "Codigo", "Nombre", "Especie", "Meses", "Vacunada", "Apta");
        System.out.println("------------------------------------------------------");
        int c = 0;
        for (int i = 0; i < cantidadM; i++) { 
            if (!adoptada[i]) {
                mostrarMascota(i);
                c++;
            }
        }
        if (c == 0) System.out.println("No hay mascotas disponibles");
    }

    // 2
    static void vacunar() {
        System.out.print("Codigo de la mascota: ");
        int pos = buscarPorCodigo(sc.nextInt());
        if (pos == -1) {
            System.out.println("Codigo no encontrado.");
        } else if (vacuna[pos]) {
            System.out.println("Resultados: "+nombre[pos] + " ya esta vacunada. Datos conservados");
        } else {
            vacuna[pos] = true;
            System.out.println("Vacunacion registrada para " + nombre[pos]);
        }
    }

    //3
    static void adoptar(){
    System.out.print("Ingrese el codigo");
    int code = buscarPorCodigo(sc.nextInt());
    if(code == -1){
        System.out.println("Mascota no encontrada");
    }else if(adoptada[code]){
        System.out.println("La mascota " + nombre[code] + " ya esta adoptada");
    }else if(!adoptada[code] && edad[code] >= 3 && vacuna[code] == true){
        System.out.println("Mascota " + nombre[code] + " apta para adopcion -adoptada");
        adoptada[code] = true;
    }else{
        System.out.println("Mascota no apta para adopcion");
        }
    }

    // 4
    static void buscarTipoEdad(){
        System.out.print("Especie( 1 perro ; 2 gato): ");
        int esp = sc.nextInt();
        System.out.print("Edad maxima en meses: ");
        int edadMax = sc.nextInt();

        System.out.println();
        System.out.printf("%-8s %-10s %-8s %-8s %-10s %s%n",
                "Codigo", "Nombre", "Especie", "Meses", "Vacunada", "Apta");
        System.out.println("------------------------------------------------------");

        int c = 0;
        for(int i = 0 ; i < cantidadM ; i++){
            if(cumpleCondiciones(i) && especie[i] == esp && edad[i] <= edadMax){
                mostrarMascota(i);
                c++;
            }

        }
        if(c == 0) System.out.println("No hay mascotas disponibles");
    }

   //5
    static void resumenFinal(){
        System.out.println("\nResumen final");
        System.out.println("Perros adoptados: " + contarM(1));
        System.out.println("Gatos adoptados: " + contarM(2));
            int noVacunadas = 0;
            int mMayorEdad = -1;
            
        for(int i = 0 ; i < cantidadM ; i++){
            if(!adoptada[i] && !vacuna[i]){
                noVacunadas++;
            }
        }
        System.out.println("Mascotas no vacunadas: " + noVacunadas);

        for(int i = 0 ; i < cantidadM ; i++){
            if(adoptada[i])
                continue;
            if(mMayorEdad == -1 || edad[i] > edad[mMayorEdad]){
                mMayorEdad = i;
            }
        }

        if(mMayorEdad == -1){
            System.out.println("No quedan mascotas disponibles.");
        }else{
            System.out.println("Mascota(s) de mayor edad disponible (" + edad[mMayorEdad] + " meses):");
            for(int i = 0 ; i < cantidadM ; i++){
                if(!adoptada[i] && edad[i] == edad[mMayorEdad]){
                    mostrarMascota(i);
                }
            }
        }
    }

    //6
    static double porcentaje() {
        int adoptadas = contarM(1) + contarM(2);
        return adoptadas * 100.0 / cantidadM;
    }


    //main
    public static void main(String[] args) {
        registrarDatosIniciales();

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n1 Ver disponibles");
            System.out.println("2 Registrar Vacunacion");
            System.out.println("3 Adoptar");
            System.out.println("4 Buscar por especie y edad maxima");
            System.out.println("5 Resumen");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
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
                    buscarTipoEdad();
                    break;
                case 5:
                    resumenFinal();
                    System.out.printf("Porcentaje adoptado: %.0f%%\n", porcentaje());
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}




//Una mascota está disponible cuando no ha sido adoptada
// apta para adopción cuando está disponible, tiene 3 o más meses de edad y está vacunada.