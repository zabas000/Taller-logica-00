int opcion = 0;
        while (opcion != 5) {
            menu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    seleccionar();
                    break;
                case 2:
                    respuesta();
                    break;
                case 3:
                    pista();
                    break;
                case 4:
                    System.out.println(equipos[actual].getEstado());
                    break;
                case 5:
                    System.out.println("Jornada terminada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        resultados();
    }

    static void menu() {
        System.out.println("\n--- ESCAPE ROOM ---");
        System.out.println("1. Seleccionar equipo");
        System.out.println("2. Registrar respuesta");
        System.out.println("3. Solicitar pista");
        System.out.println("4. Consultar estado");
        System.out.println("5. Terminar jornada");
        System.out.print("Opción: ");
    }

    static void seleccionar() {
    for (int i = 0; i < equipos.length; i++) {
        System.out.println((i + 1) + ". " + equipos[i].getNombre());
    }
    actual = sc.nextInt() - 1;
    System.out.println("Equipo seleccionado: " + equipos[actual].getNombre());
}

    static void respuesta() {
        System.out.print("¿Correcta? (1 = sí, 2 = no): ");
        int r = sc.nextInt();
        if (!equipos[actual].registrarRespuesta(r == 1)) {
            System.out.println("El equipo ya terminó.");
        }
    }

    static void pista() {
    if (equipos[actual].solicitarPista()) {
        System.out.println("Pista otorgada a " + equipos[actual].getNombre());
    } else {
        System.out.println("No se pudo dar la pista a " + equipos[actual].getNombre());
    }
    }

    static void resultados() {
        System.out.println("\n--- ESTADO FINAL ---");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println(equipos[i].getEstado());
        }

        int mejorA = equipos[0].getAcertijosResueltos();
        int mejorP = equipos[0].getPuntaje();
        for (int i = 1; i < equipos.length; i++) {
            int a = equipos[i].getAcertijosResueltos();
            int p = equipos[i].getPuntaje();
            if (a > mejorA || (a == mejorA && p > mejorP)) {
                mejorA = a;
                mejorP = p;
            }
        }

        System.out.println("\nGanador(es):");
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].getAcertijosResueltos() == mejorA && equipos[i].getPuntaje() == mejorP) {
                System.out.println("- " + equipos[i].getNombre());
            }
        }
    }
}