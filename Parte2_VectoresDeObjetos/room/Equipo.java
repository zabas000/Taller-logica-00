public class Equipo {
    //datos constantes
    private static final int TOTAL_ACERTIJOS = 5;
    private static final int PUNTAJE_INICIAL = 100;
    private static final int PISTAS_INICIALES = 3;
    private static final int PUNTOS_ACIERTO = 20;
    private static final int PENALIZACION_ERROR = 10;
    private static final int PENALIZACION_PISTA = 15;


    private String nombre;
    private int acertijosResueltos;
    private int pistasDisponibles;
    private int puntaje;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.acertijosResueltos = 0;
        this.pistasDisponibles = PISTAS_INICIALES;
        this.puntaje = PUNTAJE_INICIAL;
    }

    //1
    public boolean registrarRespuesta(boolean correcta) {
    if (completoAcertijos()) {
        return false;
    }
    if (correcta) {
        acertijosResueltos++;
        puntaje += PUNTOS_ACIERTO;
    } else {
        if (puntaje >= PENALIZACION_ERROR) {
            puntaje -= PENALIZACION_ERROR;
        } else {
            puntaje = 0;
        }
    }
    return true;
    }

    //2
    public boolean solicitarPista() {
        if (completoAcertijos() || pistasDisponibles <= 0) {
            return false;
        }
        pistasDisponibles--;

        if (puntaje >= PENALIZACION_PISTA) {
            puntaje -= PENALIZACION_PISTA;
        } else {
            puntaje = 0;
        }
        return true;
    }


    public boolean completoAcertijos() {
        return acertijosResueltos >= TOTAL_ACERTIJOS;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAcertijosResueltos() {
        return acertijosResueltos;
    }

    public int getPistasDisponibles() {
        return pistasDisponibles;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getEstado() {
        String estado = "Equipo: " + nombre
                + " | Acertijos: " + acertijosResueltos + "/" + TOTAL_ACERTIJOS
                + " | Pistas: " + pistasDisponibles
                + " | Puntaje: " + puntaje;
        if (completoAcertijos()) {
            estado += " | COMPLETADO";
        }
        return estado;
    }

}