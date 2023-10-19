package com.wildringsfranco.Dominio;

import javax.swing.*;

class Jugador {
    private String nombreWF;
    private int dineroWF;
    private ImageIcon icono;
    private int resultadoDadoWF;
    private int apuestaWF;

    public Jugador(String nombreWF, int dineroWF, ImageIcon icono) {
        this.nombreWF = nombreWF;
        this.dineroWF = dineroWF;
        this.icono = icono;
    }

    // Getters y setters para los atributos

    public String getNombreWF() {
        return nombreWF;
    }

    public void setNombreWF(String nombreWF) {
        this.nombreWF = nombreWF;
    }

    public int getDineroWF() {
        return dineroWF;
    }

    public void setDineroWF(int dineroWF) {
        this.dineroWF = dineroWF;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public int getResultadoDadoWF() {
        return resultadoDadoWF;
    }

    public void setResultadoDadoWF(int resultadoDadoWF) {
        this.resultadoDadoWF = resultadoDadoWF;
    }

    public int getApuestaWF() {
        return apuestaWF;
    }

    public void setApuestaWF(int apuestaWF) {
        this.apuestaWF = apuestaWF;
    }
}
