package Parte2_VectoresDeObjetos.room;

public class borrar { //esto no va 
    public class Equipo {
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

    // Retorna false si el equipo ya terminó y no se pudo registrar
    public boolean registrarRespuesta(boolean correcta) {
        if (completoAcertijos()) {
            return false;
        }
        if (correcta) {
            acertijosResueltos++;
            puntaje += PUNTOS_ACIERTO;
        } else {
            puntaje = Math.max(0, puntaje - PENALIZACION_ERROR);
        }
        return true;
    }

    // Retorna false si no quedan pistas o si el equipo ya terminó (sin tocar el puntaje)
    public boolean solicitarPista() {
        if (completoAcertijos() || pistasDisponibles <= 0) {
            return false;
        }
        pistasDisponibles--;
        puntaje = Math.max(0, puntaje - PENALIZACION_PISTA);
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
        return String.format("Equipo: %s | Acertijos: %d/%d | Pistas: %d | Puntaje: %d%s",
                nombre, acertijosResueltos, TOTAL_ACERTIJOS, pistasDisponibles, puntaje,
                completoAcertijos() ? " | COMPLETADO" : "");
    }
}
}
