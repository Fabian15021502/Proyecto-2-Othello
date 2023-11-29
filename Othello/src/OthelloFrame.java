import javax.swing.*;
import java.awt.*;

public class OthelloFrame extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static JLabel Puntuacionnegro;
    private static JLabel PuntuacionBlanco;
    private OthelloPanel TableroPanel;  

    public OthelloFrame() {
        setTitle("Othello Proyecto final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // Configuración del panel para el tablero del juego
        TableroPanel = new OthelloPanel(this);  // Pasa la instancia de OthelloFrame al OthelloPanel
        GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        TableroPanel.setLayout(grid);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                DiscoBoton button = new DiscoBoton('-', i, j);
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    OthelloGame.Movimiento(finalI, finalJ, OthelloGame.JugadorActual);
                    button.setDisco(OthelloGame.Tablero[finalI][finalJ]);
                    button.repaint();
                });
                TableroPanel.add(button);
            }
        }

        // Agrega un panel en la parte superior para el marcador
        JPanel PuntajePanel = new JPanel();
        Puntuacionnegro = new JLabel("JUGADOR 1: 2");
        PuntuacionBlanco = new JLabel("JUGADOR 2: 2");
        PuntajePanel.add(Puntuacionnegro);
        PuntajePanel.add(PuntuacionBlanco);

        // Agrega los paneles al marco
        add(PuntajePanel, BorderLayout.NORTH);
        add(TableroPanel, BorderLayout.CENTER);

        // Crea una instancia de OthelloGame
        new OthelloGame();
    }

    public void Puntajes(int PuntosNegro, int PuntosBlanco) {
        Puntuacionnegro.setText("JUGADOR 1: " + PuntosNegro);
        PuntuacionBlanco.setText("JUGADOR 2: " + PuntosBlanco);
    }

    // Agrega este método para obtener el panel del tablero
    public OthelloPanel getTableroPanel() {
        return TableroPanel;
    }
}
