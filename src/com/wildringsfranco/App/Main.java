package com.wildringsfranco.App;

import com.wildringsfranco.Dominio.Guayabita;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ImageIcon icono = new ImageIcon("\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\guayaba.png");
        int opcion = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Menú Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icono, new Object[]{"Jugar", "Ver Instrucciones"}, null);

        if (opcion == 0) {
            Guayabita juego = new Guayabita();
            juego.iniciarJuego();
        } else if (opcion == 1) {
            mostrarInstrucciones();
        }
    }

    private static void mostrarInstrucciones() {
        ImageIcon icono = new ImageIcon("\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\GuayabaP.png");
        String[] opcion = {"Volver"};
        int choice = 0;
        do {
            // Mostrar las instrucciones y obtener la elección del usuario
            choice = JOptionPane.showOptionDialog(null, "Instrucciones del juego:\n\n- Debes lanzar un dado y apostar una cantidad de dinero.\n"
                    + "- Si sacas 1 o 6, pierdes tu apuesta y cedes el turno al siguiente jugador.\n"
                    + "- Si sacas un número del 2 al 5, puedes apostar al pote.\n"
                    + "- Si no deseas apostar, cedes tu turno.\n"
                    + "- Si sacas un número mayor que en la tirada anterior, ganas dinero del pote.\n"
                    + "- Si sacas un número igual o inferior, pierdes lo que apostaste, y el pote aumenta.\n"
                    + "- El juego termina cuando no queda dinero en el pote o cuando solo queda un jugador.", "Instrucciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icono, new Object[]{"Jugar", "Salir"}, null);

            if (choice == 0) {
                Guayabita juego = new Guayabita();
                juego.iniciarJuego();
            }
        } while (choice == 0);
    }
}

