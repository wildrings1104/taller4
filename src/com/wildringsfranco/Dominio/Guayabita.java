package com.wildringsfranco.Dominio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Guayabita {
    private List<Jugador> jugadoresWF;
    private int poteWF;
    private int apuestaMinimaWF;
    private ImageIcon[] imagenesDados;

    public Guayabita() {
        this.jugadoresWF = new ArrayList<>();
        this.poteWF = 0;
        this.apuestaMinimaWF = 0;
        this.imagenesDados = new ImageIcon[6]; // Arreglo para 6 imágenes de dados
        cargarImagenesDados(); // Cargar las imágenes de los dados
        iniciarJuego();
    }
    public void iniciarJuego() {
        ImageIcon icon = new ImageIcon("\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\GuayabaP.png");

        int numeroJugadores = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el número de jugadores:", "Número de Jugadores", JOptionPane.QUESTION_MESSAGE, icon, null, null));
        poteWF = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Ingrese el monto inicial del pote:","valor inicial del pote", JOptionPane.QUESTION_MESSAGE, icon, null, null));
        apuestaMinimaWF = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el valor mínimo de apuesta inicial:", "Apuesta Mínima", JOptionPane.QUESTION_MESSAGE, icon, null, null));

        for (int i = 0; i < numeroJugadores; i++) {
            String nombreWF = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre del Jugador " + (i + 1) + ":", "Nombre del Jugador", JOptionPane.QUESTION_MESSAGE, icon, null, null);
            int dineroWF = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el dinero del jugador " + (i + 1) + ":", "Dinero del Jugador", JOptionPane.QUESTION_MESSAGE, icon, null, null));
            jugadoresWF.add(new Jugador(nombreWF, dineroWF, icon));
        }

        poteWF = apuestaMinimaWF * numeroJugadores;

        while (poteWF > 0 && jugadoresWF.size() > 1) {
            for (Jugador jugador : jugadoresWF) {
                if (jugador.getDineroWF() <= 0) {
                    jugadoresWF.remove(jugador);
                    continue;
                }

                lanzarDadosWF(jugador);

                if (jugador.getResultadoDadoWF() == 1 || jugador.getResultadoDadoWF() == 6) {
                    continue;
                }

                int opcionApuesta = JOptionPane.showConfirmDialog(null, jugador.getNombreWF() + ", ¿deseas hacer una apuesta?", "Guayabita", JOptionPane.YES_NO_OPTION);

                if (opcionApuesta == JOptionPane.YES_OPTION) {
                    realizarApuestaWF(jugador);
                }
            }
        }

        mostrarResultados();
    }

    private void cargarImagenesDados() {
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\uno" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\dos" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\tres" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\cuatro" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\cinco" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
        for (int i = 0; i < 6; i++) {
            String nombreImagen = "\\Users\\Public\\Documents\\Taller4\\src\\com\\wildringsfranco\\Dominio\\seis" + (i + 1) + ".png";
            imagenesDados[i] = new ImageIcon(nombreImagen);
        }
    }

    public void lanzarDadosWF(Jugador jugador) {
        int resultadoDado = (int) (Math.random() * 6) + 1;

        // Obtén la imagen del dado correspondiente al resultado
        ImageIcon nombreImagen = imagenesDados[resultadoDado - 1];

        jugador.setResultadoDadoWF(resultadoDado);

        // Mostrar el resultado del dado con la imagen correspondiente
        JOptionPane.showMessageDialog(null, "Resultado del dado: " + resultadoDado, "Lanzamiento de Dados", JOptionPane.INFORMATION_MESSAGE, nombreImagen);

        if (resultadoDado == 1 || resultadoDado == 6) {
            // Si saca 1 o 6, no puede apostar y cede el turno.
            JOptionPane.showMessageDialog(null, jugador.getNombreWF() + ", has obtenido un 1 o un 6, por lo que no puedes realizar una apuesta y cedes el turno.", "Guayabita", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void realizarApuestaWF(Jugador jugador) {
        if (jugador.getResultadoDadoWF() == 1 || jugador.getResultadoDadoWF() == 6) {
            JOptionPane.showMessageDialog(null, jugador.getNombreWF() + ", has obtenido un 1 o un 6, por lo que no puedes realizar una apuesta.", "Guayabita", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int apuesta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de la apuesta para " + jugador.getNombreWF() + ":"));

        if (apuesta >= apuestaMinimaWF && apuesta <= jugador.getDineroWF()) {
            jugador.setApuestaWF(apuesta);
            poteWF += apuesta;
            jugador.setDineroWF(jugador.getDineroWF() - apuesta);
        } else {
            JOptionPane.showMessageDialog(null, "La apuesta debe ser mayor o igual a la apuesta mínima y no puede exceder el dinero del jugador.", "Error en la apuesta", JOptionPane.ERROR_MESSAGE);
            realizarApuestaWF(jugador);
        }
    }

    public void mostrarResultados() {
        StringBuilder resultados = new StringBuilder("Resultados del juego:\n\n");

        for (Jugador jugador : jugadoresWF) {
            resultados.append(jugador.getNombreWF()).append(": ").append(jugador.getDineroWF()).append(" dinero restante\n");
        }

        JOptionPane.showMessageDialog(null, resultados.toString(), "Resultados - Guayabita", JOptionPane.INFORMATION_MESSAGE);
    }
}