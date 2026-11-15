package Parte2_VectoresDeObjetos.room;

public class Equipo {
    private String nombre;
    private int acertijosResueltos;
    private int pistasDisponibles;
    private int puntajeAcumulado;

    public Equipo (String nombre) {
        this.nombre = nombre;
        this.acertijosResueltos = 0;
        this.pistasDisponibles = 3;
        this.puntajeAcumulado = 100;
    }
//metodos
//1
    public boolean registrarRespuesta (boolean respuestaCorrecta) {
        if(completoAcertijos()){
            return false;
        }
            if(respuestaCorrecta) {
                acertijosResueltos++;
                puntajeAcumulado += 20;
        }else{
            if(puntajeAcumulado >= 10){
                puntajeAcumulado -= 10;
            }else {
                puntajeAcumulado = 0;
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
        if (puntajeAcumulado >= 15) {
            puntajeAcumulado -= 15;
        } else {
            puntajeAcumulado = 0;
        }
        return true;
    }

//3
    public boolean completoAcertijos () {
        if(acertijosResueltos < 5) {
            return false; 
        } else {
            return true; 
        }
    }
    
//mini-metodo
    public String getNombre(){
        return nombre;
    }

    public int getAcertijosResueltos() {
        return acertijosResueltos;
    }

    public int getPistasDisponibles() {
        return pistasDisponibles;
    }

    public int getPuntaje() {
        return puntajeAcumulado;
    }

    public String getEstado() {
        String estado = "Equipo: " + nombre
                + "  Acertijos: " + acertijosResueltos + " de " + 5
                + "  Pistas: " + pistasDisponibles
                + "  Puntaje: " + puntajeAcumulado;
        if (completoAcertijos()) {
            estado += "  COMPLETADO";
        }
        return estado;
    }


}

