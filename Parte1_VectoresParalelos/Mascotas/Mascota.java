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

    //mini-metodos
    static int buscarPorCodigo(int cod) {
        for (int i = 0; i < m; i++) {
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

    static boolean cumpleCondiciones(int i) {
        return !adoptada[i] && edad[i] >= 3 && vacuna[i];
    }

    static void mostrarMascota(int i) {
        System.out.println(codigo[i] + " , " + nombre[i] + " , "
                + (especie[i] == 1 ? "Perro" : "Gato") + " , "
                + edad[i] + " meses , vacunada: " + (vacuna[i] ? "Si" : "No")
                + " , apta para adopcion: " + (cumpleCondiciones(i) ? "Si" : "No"));
    }

    //datos iniciales
    static void registrarDatosIniciales() {
        // 5 mascotas fijas
        codigo[0] = 101; nombre[0] = "Max";   especie[0] = 1; edad[0] = 12; vacuna[0] = true;
        codigo[1] = 102; nombre[1] = "Luna";  especie[1] = 2; edad[1] = 2;  vacuna[1] = false;
        codigo[2] = 103; nombre[2] = "Rocky"; especie[2] = 1; edad[2] = 5;  vacuna[2] = true;
        codigo[3] = 104; nombre[3] = "Mia";   especie[3] = 2; edad[3] = 8;  vacuna[3] = true;
        codigo[4] = 105; nombre[4] = "Toby";  especie[4] = 1; edad[4] = 1;  vacuna[4] = false;

        // 10 mascotas por teclado
        for (int i = 5; i < m; i++) {
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
        }
        // adoptada empieza en false para todas
    }

    // menu-metodos
    // 1
    static void verDisponibles() {
        int c = 0;
        for (int i = 0; i < m; i++) {
            if (!adoptada[i]) {
                mostrarMascota(i);
                c++;
            }
        }
        if (c == 0) System.out.println("No hay mascotas disponibles");
    }

    // 2
    static void vacunar() {
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());
        if (pos == -1) {
            System.out.println("Codigo no encontrado.");
        } else if (vacuna[pos]) {
            System.out.println("Ya esta vacunada. Datos conservados.");
        } else {
            vacuna[pos] = true;
            System.out.println("Vacunacion registrada.");
        }
    }

    // 3
    static void adoptar() {
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());
        if (pos == -1) {
            System.out.println("Codigo no encontrado.");
        } else if (adoptada[pos]) {
            System.out.println("Rechazada: ya fue adoptada.");
        } else if (edad[pos] < 3) {
            System.out.println("Rechazada: tiene menos de 3 meses.");
        } else if (!vacuna[pos]) {
            System.out.println("Rechazada: no esta vacunada.");
        } else {
            adoptada[pos] = true;
            System.out.println("Adopcion autorizada.");
        }
    }

    // 4
    static void buscar() {
        System.out.print("Especie (1 perro, 2 gato): ");
        int esp = sc.nextInt();
        System.out.print("Edad maxima en meses: ");
        int max = sc.nextInt();
        int c = 0;
        for (int i = 0; i < m; i++) {
            if (cumpleCondiciones(i) && especie[i] == esp && edad[i] <= max) {
                mostrarMascota(i);
                c++;
            }
        }
        if (c == 0) System.out.println("No hay mascotas que cumplan los criterios.");
    }

    // 5. resumen final
    static void resumenFinal() {
        int perros = 0, gatos = 0, sinVacuna = 0, mayor = -1;
        for (int i = 0; i < m; i++) {
            if (adoptada[i]) {
                if (especie[i] == 1) perros++;
                else gatos++;
            } else {
                if (!vacuna[i]) sinVacuna++;
                if (edad[i] > mayor) mayor = edad[i];
            }
        }

        System.out.println("\nPerros adoptados: " + perros);
        System.out.println("Gatos adoptados: " + gatos);
        System.out.println("Disponibles sin vacunar: " + sinVacuna);

        if (mayor == -1) {
            System.out.println("No quedan mascotas disponibles.");
        } else {
            System.out.println("Mayor edad disponible (" + mayor + " meses):");
            for (int i = 0; i < m; i++) {
                if (!adoptada[i] && edad[i] == mayor)
                    System.out.println(" - " + codigo[i] + " " + nombre[i]);
            }
        }

        System.out.println("Porcentaje adoptado: " + ((perros + gatos) * 100.0 / m) + "%");
    }

    //main
    public static void main(String[] args) {
        registrarDatosIniciales();

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n1 Ver disponibles");
            System.out.println("2 Registrar vacunacion");
            System.out.println("3 Adoptar");
            System.out.println("4 Buscar por especie y edad maxima");
            System.out.println("5 Terminar");
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
                    buscar();
                    break;
                case 5:
                    resumenFinal();
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}