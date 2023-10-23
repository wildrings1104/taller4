package com.wildringsfranco.Dominio;

import javax.swing.*;

public class Jugador {
    private String nombreWF;
    private int dineroWF;

    private int resultadoDadoWF;
    private int apuestaWF;

    public Jugador(String nombreWF) {
        this.nombreWF = nombreWF;
        this.apuestaWF = 500;
        this.resultadoDadoWF = 0;

    }
    public void incrementarDinero(int monto){
        dineroWF += monto;
    }

    public void decrementarDinero(int monto){
        dineroWF -= monto;
    }

    public String getNombreWF() {
        return nombreWF;
    }

    public int getDineroWF() {
        return dineroWF;
    }

    public int getResultadoDadoWF() {
        return resultadoDadoWF;
    }

    public int getApuestaWF() {
        return apuestaWF;
    }

    public void setNombreWF(String nombreWF) {
        this.nombreWF = nombreWF;
    }

    public void setDineroWF(int dineroWF) {
        this.dineroWF = dineroWF;
    }

    public void setResultadoDadoWF(int resultadoDadoWF) {
        this.resultadoDadoWF = resultadoDadoWF;
    }

    public void setApuestaWF(int apuestaWF) {
        this.apuestaWF = apuestaWF;
    }
}
