package com.wildringsfranco.Dominio;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class Guayabita {
    private int dadoWF = 0;
    private int poteWF;
    private int numeroJugadoresWF;
    private int jugadorActual = 0;
    private Jugador[] jugadoresWF;
    private Random random = new Random();

    public Guayabita() {
        this.poteWF = 0;
    }
    private boolean juegoIniciado = false;

    public void iniciarJuego() {
        if (juegoIniciado) {
            JOptionPane.showMessageDialog(null, "El juego ya ha comenzado.");
            return;
        }
        ImageIcon icon = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\perfil.png");
        this.numeroJugadoresWF = Integer.parseInt((String) JOptionPane.showInputDialog(null,
                "Cuantos usuarios jugaran", "Cantidad de Jugadores", JOptionPane.QUESTION_MESSAGE, icon, null, null));
        this.jugadoresWF = new Jugador[numeroJugadoresWF];

        juegoIniciado = true;
        generarJugadoresWF();

        jugar();
    }

    private void generarJugadoresWF() {
        for (int i = 0; i < numeroJugadoresWF; i++) {
            ImageIcon icon = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\perfil.png");
            String nombreWF = (String) JOptionPane.showInputDialog(null, "Nombre del Jugador " + (i + 1), "Nombre del Jugador", JOptionPane.QUESTION_MESSAGE, icon, null, null);
            jugadoresWF[i] = new Jugador(nombreWF);
            jugadoresWF[i].setDineroWF(obtenerDineroWF());
            poteWF += 500;
        }
    }

    public int obtenerResultadoDadoWF() {
        return random.nextInt(6) + 1;
    }

    public int verApuestaWF(Jugador jugador) {
        int apuestaWF = 0;
        boolean apuestaValida = false;

        while (!apuestaValida) {
            ImageIcon icon = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\apuesta.png");
            String input = (String) JOptionPane.showInputDialog(null, "Ingrese su apuesta" + " ( Usted cuenta con: " + jugador.getDineroWF()
                            + ") y el pote actual es de: " + getPoteWF(),"Realizar apuesta", JOptionPane.QUESTION_MESSAGE,icon,null,null);

            try {
                apuestaWF = Integer.parseInt(input);
                if (apuestaWF >= 1 && apuestaWF <= jugador.getDineroWF() && apuestaWF <= poteWF) {
                    apuestaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Apuesta no válida. Debes apostar un valor entre 1 y tu presupuesto actual.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor numérico válido.");
            }
        }

        return apuestaWF;
    }

    public int obtenerDineroWF() {
        int dineroWF = 0;
        boolean DineroActivo = false;

        Jugador jugador = jugadoresWF[jugadorActual];
        jugadorActual = (jugadorActual + 1) % jugadoresWF.length;

        while (!DineroActivo) {
            ImageIcon icon = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\dinero.png");
            String input = (String) JOptionPane.showInputDialog(null, "Ingresa el Dinero que tiene para apostar Señor(a): "
                    + jugador.getNombreWF(), "Dinero para apostar", JOptionPane.QUESTION_MESSAGE, icon, null, null);

            try {
                dineroWF = Integer.parseInt(input);
                if (dineroWF >= 550) {
                    DineroActivo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Dinero inválido. Debe ser un valor mayor o igual a 550. \n"
                            + "Para poder aportar los 500 del ingreso al juego y tengas mínimo 50 para apostar");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor numérico válido.");
            }
        }


        poteWF += 500;
        jugador.decrementarDinero( - 500);
        return dineroWF;
    }

    public void jugar() {
        boolean juegoActivo = true;
        boolean jugadorGano = false;

        while (juegoActivo) {
            Jugador jugador = jugadoresWF[jugadorActual];
            ImageIcon icono = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\GuayabaP.png");
            JOptionPane.showMessageDialog(null, "Turno del Señor(a) " + jugador.getNombreWF(), "Turno", JOptionPane.QUESTION_MESSAGE, icono);

            int resultadoDados = obtenerResultadoDadoWF();
            String mensaje = "Resultado del dado: " + resultadoDados;
            ImageIcon iconoDado = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\dado" + resultadoDados + ".png");
            JOptionPane.showMessageDialog(null, "Resultado del primer lanzamiento", "Resultado 1er lanzamiento" + resultadoDados, JOptionPane.PLAIN_MESSAGE, iconoDado);

            if (resultadoDados != 1 && resultadoDados != 6) {
                boolean quiereApostar = JOptionPane.showConfirmDialog(
                        null,
                        "Quieres apostar con este dado?",
                        "Apostar",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

                if (quiereApostar) {
                    int apuesta = verApuestaWF(jugador);
                    int resultadoSegundaTirada = obtenerResultadoDadoWF();
                    ImageIcon iconoSegundaDado = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\dado" + resultadoSegundaTirada + ".png");
                    JOptionPane.showMessageDialog(null, "Resultado del segundo lanzamiento: ", "Resultado 2do lanzamiento", JOptionPane.PLAIN_MESSAGE, iconoSegundaDado);

                    if (resultadoSegundaTirada > resultadoDados) {
                        jugador.incrementarDinero(apuesta);
                        poteWF -= apuesta;
                        ImageIcon iconoGanador = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\ganar.png");
                        JOptionPane.showMessageDialog(null, "Ganaste!", "Ganador", JOptionPane.QUESTION_MESSAGE, iconoGanador);
                    } else {
                        poteWF += apuesta;
                        jugador.decrementarDinero(apuesta);
                        ImageIcon iconoPerdedor = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\perdedor.png");
                        JOptionPane.showMessageDialog(null, "Perdiste!", "Perdedor", JOptionPane.QUESTION_MESSAGE, iconoPerdedor);
                    }
                }
            }

            if (jugador.getDineroWF() <= 0) {
                JOptionPane.showMessageDialog(null, jugador.getNombreWF() + " No tienes Dinero, Has sido eliminado del juego.");
                eliminarJugador(jugadorActual);
            }

            if (!quedanJugadoresConDinero() || poteWF <= 0) {
                juegoActivo = false;
            } else {
                jugadorActual = (jugadorActual + 1) % jugadoresWF.length;
            }
        }

        for (int i = 0; i < jugadoresWF.length; i++) {
            if (jugadoresWF[i].getDineroWF() > 0) {
                jugadorGano = true;
                break;
            }
        }

        if (jugadorGano && poteWF <= 0) {
            Jugador jugador = jugadoresWF[jugadorActual];
            ImageIcon iconoGanador = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\ganar.png");
            JOptionPane.showMessageDialog(null, jugador.getNombreWF() + " ganó el pote.", "Ganador Final", JOptionPane.QUESTION_MESSAGE, iconoGanador);
        } else if (jugadorGano) {
            ImageIcon iconoCasino = new ImageIcon("C:\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Imagenes\\casino.png");
            JOptionPane.showMessageDialog(null, "Gano el casino!", "Casino Gana", JOptionPane.QUESTION_MESSAGE, iconoCasino);
        }
    }

    private void eliminarJugador(int indice) {
        Jugador[] nuevosJugadores = new Jugador[jugadoresWF.length - 1];
        int contador = 0;
        for (int i = 0; i < jugadoresWF.length; i++) {
            if (i != indice) {
                nuevosJugadores[contador] = jugadoresWF[i];
                contador++;
            }
        }
        jugadoresWF = nuevosJugadores;
        numeroJugadoresWF--;
        jugadorActual = jugadorActual % numeroJugadoresWF;
    }

    private boolean quedanJugadoresConDinero() {
        for (Jugador jugador : jugadoresWF) {
            if (jugador.getDineroWF() > 0) {
                return true;
            }
        }
        return false;
    }
    public int getPoteWF() {
        return poteWF;
    }
}