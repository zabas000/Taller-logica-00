import java.util.Scanner;

public class Refugio {

    static Scanner sc = new Scanner(System.in);
    static final int N = 15;

    // Vectores paralelos
    static int[] codigo = new int[N];
    static String[] nombre = new String[N];
    static int[] especie = new int[N];          // 1 perro, 2 gato
    static int[] edad = new int[N];             // meses
    static boolean[] vacunada = new boolean[N];
    static boolean[] adoptada = new boolean[N]; // false = disponible

    // ---------- Metodos auxiliares ----------
    static int buscarPorCodigo(int cod) {
        for (int i = 0; i < N; i++) {
            if (codigo[i] == cod) return i;
        }
        return -1;
    }

    static boolean estaDisponible(int i) {
        return !adoptada[i];
    }

    static boolean cumpleCondiciones(int i) {
        return estaDisponible(i) && edad[i] >= 3 && vacunada[i];
    }

    -----static boolean codigoRepetido(int cod, int hasta) {
        for (int j = 0; j < hasta; j++) {
            if (codigo[j] == cod) return true;
        }
        return false;
    }

    static void mostrarMascota(int i) {
        System.out.println(codigo[i] + " | " + nombre[i] + " | "
                + (especie[i] == 1 ? "Perro" : "Gato") + " | "
                + edad[i] + " meses | vacunada: " + (vacunada[i] ? "Si" : "No")
                + " | apta para adopcion: " + (cumpleCondiciones(i) ? "Si" : "No"));
    }

    // ---------- 1. Registro inicial ----------
    static void registrarDatosIniciales() {
        for (int i = 0; i < N; i++) {
            System.out.println("\nMascota " + (i + 1) + " de " + N);

            int cod;
            do {
                System.out.print("Codigo: ");
                cod = sc.nextInt();
                if (codigoRepetido(cod, i)) System.out.println("Ese codigo ya existe.");
            } while (codigoRepetido(cod, i));
            codigo[i] = cod;

            System.out.print("Nombre: ");
            nombre[i] = sc.next();

            do {
                System.out.print("Especie (1 perro, 2 gato): ");
                especie[i] = sc.nextInt();
            } while (especie[i] != 1 && especie[i] != 2);

            do {
                System.out.print("Edad en meses (mayor que 0): ");
                edad[i] = sc.nextInt();
            } while (edad[i] <= 0);

            int v;
            do {
                System.out.print("Vacunada (1 si, 0 no): ");
                v = sc.nextInt();
            } while (v != 0 && v != 1);
            vacunada[i] = (v == 1);

            adoptada[i] = false;
        }
    }

    // ---------- 2. Opciones del menu ----------
    static void consultarDisponibles() {
        int c = 0;
        for (int i = 0; i < N; i++) {
            if (estaDisponible(i)) {
                mostrarMascota(i);
                c++;
            }
        }
        if (c == 0) System.out.println("No hay mascotas disponibles.");
    }

    // 3. Vacunacion
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
    }

    // 4. Adopcion
    static void realizarAdopcion() {
        System.out.print("Codigo: ");
        int pos = buscarPorCodigo(sc.nextInt());
        if (pos == -1) {
            System.out.println("Codigo no encontrado.");
        } else if (adoptada[pos]) {
            System.out.println("Rechazada: ya fue adoptada.");
        } else if (edad[pos] < 3) {
            System.out.println("Rechazada: tiene menos de 3 meses.");
        } else if (!vacunada[pos]) {
            System.out.println("Rechazada: no esta vacunada.");
        } else {
            adoptada[pos] = true;
            System.out.println("Adopcion autorizada.");
        }
    }

    // 5. Busqueda por especie y edad maxima
    static void buscarPorEspecieYEdad() {
        System.out.print("Especie (1 perro, 2 gato): ");
        int esp = sc.nextInt();
        System.out.print("Edad maxima en meses: ");
        int max = sc.nextInt();
        int c = 0;
        for (int i = 0; i < N; i++) {
            if (cumpleCondiciones(i) && especie[i] == esp && edad[i] <= max) {
                mostrarMascota(i);
                c++;
            }
        }
        if (c == 0) System.out.println("No hay mascotas que cumplan los criterios.");
    }

    // ---------- 6 y 7. Resumen final ----------
    static int contarAdoptadasPorEspecie(int esp) {
        int c = 0;
        for (int i = 0; i < N; i++) {
            if (adoptada[i] && especie[i] == esp) c++;
        }
        return c;
    }

    static int contarDisponiblesSinVacuna() {
        int c = 0;
        for (int i = 0; i < N; i++) {
            if (estaDisponible(i) && !vacunada[i]) c++;
        }
        return c;
    }

    static int mayorEdadDisponible() {
        int mayor = -1;
        for (int i = 0; i < N; i++) {
            if (estaDisponible(i) && edad[i] > mayor) mayor = edad[i];
        }
        return mayor;
    }

    static void resumenFinal() {
        int perros = contarAdoptadasPorEspecie(1);
        int gatos = contarAdoptadasPorEspecie(2);
        int mayor = mayorEdadDisponible();

        System.out.println("\n===== RESUMEN FINAL =====");
        System.out.println("Perros adoptados: " + perros);
        System.out.println("Gatos adoptados: " + gatos);
        System.out.println("Disponibles sin vacunar: " + contarDisponiblesSinVacuna());

        if (mayor == -1) {
            System.out.println("No quedan mascotas disponibles.");
        } else {
            System.out.println("Mayor edad disponible (" + mayor + " meses):");
            for (int i = 0; i < N; i++) {
                if (estaDisponible(i) && edad[i] == mayor)
                    System.out.println(" - " + codigo[i] + " " + nombre[i]);
            }
        }

        double porcentaje = (perros + gatos) * 100.0 / N;
        System.out.println("Porcentaje adoptado: " + porcentaje + "%");
    }

    // ---------- main ----------
    public static void main(String[] args) {
        registrarDatosIniciales();

        int opcion;
        do {
            System.out.println("\n1. Ver disponibles");
            System.out.println("2. Registrar vacunacion");
            System.out.println("3. Realizar adopcion");
            System.out.println("4. Buscar por especie y edad maxima");
            System.out.println("5. Terminar");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            if (opcion == 1) consultarDisponibles();
            else if (opcion == 2) registrarVacunacion();
            else if (opcion == 3) realizarAdopcion();
            else if (opcion == 4) buscarPorEspecieYEdad();
            else if (opcion != 5) System.out.println("Opcion invalida.");

        } while (opcion != 5);

        resumenFinal();
        sc.close();
    }
}