package com.wildringsfranco.App;

import com.wildringsfranco.Dominio.Guayabita;

import javax.swing.*;


public class Main {
    private static boolean juegoComenzado = false;

    public static void main(String[] args) {
        ImageIcon icono = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\guayaba.png");

        if (!juegoComenzado) {
            int opcion = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Menú Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icono, new Object[]{"Jugar", "Ver Instrucciones"}, null);

            if (opcion == 0) {
                iniciarJuego();
            } else if (opcion == 1) {
                boolean continuarJuego = mostrarInstrucciones();
                if (continuarJuego) {
                    iniciarJuego();
                }
            }
        }
    }

    private static boolean mostrarInstrucciones() {
        ImageIcon icono = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\guayaba.png");
        int opcion = JOptionPane.showOptionDialog(null, "\n\nInstrucciones del juego:\n\n- Debes lanzar un dado y apostar una cantidad de dinero.\n"
                + "- Si sacas 1 o 6, pierdes tu apuesta y cedes el turno al siguiente jugador.\n"
                + "- Si sacas un número del 2 al 5, puedes apostar al pote.\n"
                + "- Si no deseas apostar, cedes tu turno.\n"
                + "- Si sacas un número mayor que en la tirada anterior, ganas dinero del pote.\n"
                + "- Si sacas un número igual o inferior, pierdes lo que apostaste, y el pote aumenta.\n"
                + "- El juego termina cuando no queda dinero en el pote o cuando solo queda un jugador.", "Instrucciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icono, new Object[]{"Jugar", "Salir"}, null);

        return opcion == 0;
    }

    private static void iniciarJuego() {
        Guayabita juego = new Guayabita();
        juego.iniciarJuego();
        juegoComenzado = true;
    }
}
