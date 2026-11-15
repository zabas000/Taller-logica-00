
    import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Equipo[] equipos = new Equipo[4];
        equipos[0] = new Equipo("Alfa");
        equipos[1] = new Equipo("Beta");
        equipos[2] = new Equipo("Gamma");
        equipos[3] = new Equipo("Delta");

        int actual = -1;
        int opcion;

        do {
            System.out.println("\n===== ESCAPE ROOM =====");
            System.out.println("Equipo seleccionado: " + (actual == -1 ? "ninguno" : equipos[actual].getNombre()));
            System.out.println("1. Seleccionar equipo");
            System.out.println("2. Registrar respuesta");
            System.out.println("3. Solicitar pista");
            System.out.println("4. Consultar estado");
            System.out.println("5. Terminar jornada");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    for (int i = 0; i < equipos.length; i++) {
                        System.out.println((i + 1) + ". " + equipos[i].getNombre());
                    }
                    int sel = leerEntero("Equipo: ");
                    if (sel >= 1 && sel <= equipos.length) {
                        actual = sel - 1;
                    } else {
                        System.out.println("Equipo inválido.");
                    }
                    break;
                case 2:
                    if (actual == -1) {
                        System.out.println("Primero selecciona un equipo.");
                    } else if (equipos[actual].completoAcertijos()) {
                        System.out.println("Este equipo ya completó los acertijos.");
                    } else {
                        System.out.println("1. Correcta  2. Incorrecta");
                        int r = leerEntero("Respuesta: ");
                        if (r == 1 || r == 2) {
                            equipos[actual].registrarRespuesta(r == 1);
                            System.out.println(equipos[actual].getEstado());
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;
                case 3:
                    if (actual == -1) {
                        System.out.println("Primero selecciona un equipo.");
                    } else if (equipos[actual].solicitarPista()) {
                        System.out.println("Pista otorgada.");
                        System.out.println(equipos[actual].getEstado());
                    } else {
                        System.out.println("No se pudo otorgar la pista (sin pistas o equipo terminado).");
                    }
                    break;
                case 4:
                    if (actual == -1) {
                        System.out.println("Primero selecciona un equipo.");
                    } else {
                        System.out.println(equipos[actual].getEstado());
                    }
                    break;
                case 5:
                    System.out.println("Jornada terminada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        mostrarResultados(equipos);
    }

    private static void mostrarResultados(Equipo[] equipos) {
        System.out.println("\n===== ESTADO FINAL =====");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println(equipos[i].getEstado());
        }

        // Mejor equipo: más acertijos; si empatan, mayor puntaje
        int mejorAcertijos = equipos[0].getAcertijosResueltos();
        int mejorPuntaje = equipos[0].getPuntaje();
        for (int i = 1; i < equipos.length; i++) {
            int a = equipos[i].getAcertijosResueltos();
            int p = equipos[i].getPuntaje();
            if (a > mejorAcertijos || (a == mejorAcertijos && p > mejorPuntaje)) {
                mejorAcertijos = a;
                mejorPuntaje = p;
            }
        }

        int ganadores = 0;
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].getAcertijosResueltos() == mejorAcertijos
                    && equipos[i].getPuntaje() == mejorPuntaje) {
                ganadores++;
            }
        }

        System.out.println(ganadores == 1 ? "\nGANADOR:" : "\nEMPATE entre:");
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].getAcertijosResueltos() == mejorAcertijos
                    && equipos[i].getPuntaje() == mejorPuntaje) {
                System.out.println("- " + equipos[i].getNombre());
            }
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

