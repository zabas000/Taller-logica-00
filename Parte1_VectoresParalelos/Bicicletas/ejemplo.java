import java.util.Scanner;

public class Bicicletas {

    static Scanner sc = new Scanner(System.in);
    static int m = 12;

    static int[] codigo = {201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212};
    static int[] estacion = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
    static int[] prestamos = new int[m];   // inician en 0
    static int[] estado = new int[m];      // 1 disponible, 2 prestada, 3 mantenimiento

    //mini-metodos
    static int buscarPorCodigo(int cod) {
        for (int i = 0; i < m; i++) {
            if (codigo[i] == cod) return i;
        }
        return -1;
    }

    static boolean estacionValida(int est) {
        return est >= 1 && est <= 4;
    }

    // disponible con menos prestamos en esa estacion (empate: la primera)
    static int elegirBici(int est) {
        int pos = -1;
        for (int i = 0; i < m; i++) {
            if (estado[i] == 1 && estacion[i] == est) {
                if (pos == -1 || prestamos[i] < prestamos[pos]) pos = i;
            }
        }
        return pos;
    }

    static int contarDisponibles(int est) {
        int c = 0;
        for (int i = 0; i < m; i++) {
            if (estado[i] == 1 && estacion[i] == est) c++;
        }
        return c;
    }

    static int mayorPrestamos() {
        int mayor = 0;
        for (int i = 0; i < m; i++) {
            if (prestamos[i] > mayor) mayor = prestamos[i];
        }
        return mayor;
    }

    static String nombreEstado(int i) {
        if (estado[i] == 1) return "Disponible";
        if (estado[i] == 2) return "Prestada";
        return "Mantenimiento";
    }

    static void encabezado() {
        System.out.printf("%-8s %-10s %-10s %s%n", "Codigo", "Estacion", "Prestamos", "Estado");
        System.out.println("----------------------------------------------");
    }

    static void mostrarBici(int i) {
        System.out.printf("%-8d %-10d %-10d %s%n", codigo[i], estacion[i], prestamos[i], nombreEstado(i));
    }

    //datos iniciales
    static void registrarDatosIniciales() {
        for (int i = 0; i < m; i++) {
            estado[i] = 1;   // todas disponibles
        }
    }

    //menu-metodos

    // 1
    static void prestar() {
        System.out.println("\n===== PRESTAR =====");
        System.out.print("Estacion (1 a 4): ");
        int est = sc.nextInt();

        if (!estacionValida(est)) {
            System.out.println("Resultado: estacion invalida.");
            return;
        }
        int pos = elegirBici(est);
        if (pos == -1) {
            System.out.println("Resultado: no hay bicicletas disponibles en la estacion " + est + ".");
        } else {
            estado[pos] = 2;
            prestamos[pos]++;
            System.out.println("Resultado: prestamo autorizado. Bicicleta " + codigo[pos] + ".");
        }
    }

    // 2
    static void devolver() {
        System.out.println("\n===== DEVOLVER =====");
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());
        System.out.print("Estacion de llegada (1 a 4): ");
        int est = sc.nextInt();

        if (pos == -1) {
            System.out.println("Resultado: codigo no encontrado.");
        } else if (!estacionValida(est)) {
            System.out.println("Resultado: estacion invalida.");
        } else if (estado[pos] != 2) {
            System.out.println("Resultado: la bicicleta no esta prestada.");
        } else {
            estacion[pos] = est;
            estado[pos] = 1;
            System.out.println("Resultado: devolucion registrada en la estacion " + est + ".");
        }
    }

    // 3
    static void mantenimiento() {
        System.out.println("\n===== ENVIAR A MANTENIMIENTO =====");
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());

        if (pos == -1) {
            System.out.println("Resultado: codigo no encontrado.");
        } else if (estado[pos] != 1) {
            System.out.println("Resultado: solo se pueden enviar bicicletas disponibles.");
        } else {
            estado[pos] = 3;
            System.out.println("Resultado: bicicleta " + codigo[pos] + " en mantenimiento.");
        }
    }

    // 4
    static void habilitar() {
        System.out.println("\n===== HABILITAR =====");
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());

        if (pos == -1) {
            System.out.println("Resultado: codigo no encontrado.");
        } else if (estado[pos] != 3) {
            System.out.println("Resultado: la bicicleta no esta en mantenimiento.");
        } else {
            estado[pos] = 1;
            System.out.println("Resultado: bicicleta " + codigo[pos] + " habilitada.");
        }
    }

    // 5
    static void consultar() {
        System.out.println("\n===== DISPONIBILIDAD =====");
        encabezado();
        for (int i = 0; i < m; i++) {
            mostrarBici(i);
        }
    }

    // 6
    static void resumenFinal() {
        System.out.println("\n===== RESUMEN FINAL =====");

        System.out.println("Disponibles por estacion:");
        for (int est = 1; est <= 4; est++) {
            System.out.println("  Estacion " + est + ": " + contarDisponibles(est));
        }

        System.out.println("\nBicicletas prestadas:");
        encabezado();
        int c = 0;
        for (int i = 0; i < m; i++) {
            if (estado[i] == 2) {
                mostrarBici(i);
                c++;
            }
        }
        if (c == 0) System.out.println("Ninguna.");

        int mayor = mayorPrestamos();
        System.out.println("\nMayor cantidad de prestamos (" + mayor + "):");
        encabezado();
        for (int i = 0; i < m; i++) {
            if (prestamos[i] == mayor) mostrarBici(i);
        }
    }

    //main
    public static void main(String[] args) {
        registrarDatosIniciales();

        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n===== BICICLETAS DEL CAMPUS =====");
            System.out.println("1. Prestar");
            System.out.println("2. Devolver");
            System.out.println("3. Enviar a mantenimiento");
            System.out.println("4. Habilitar");
            System.out.println("5. Consultar disponibilidad");
            System.out.println("6. Terminar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: prestar(); break;
                case 2: devolver(); break;
                case 3: mantenimiento(); break;
                case 4: habilitar(); break;
                case 5: consultar(); break;
                case 6: resumenFinal(); break;
                default: System.out.println("Opcion invalida");
            }
        }
    }
}

