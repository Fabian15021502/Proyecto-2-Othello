import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OthelloGame {

    static final int BOARD_SIZE = 8;
    static char[][] Tablero = new char[BOARD_SIZE][BOARD_SIZE];
    static char JugadorActual = 'B';
    static OthelloFrame othelloFrame;

    public static void main(String[] args) {
        IniciarTablero();
        SwingUtilities.invokeLater(() -> {
            othelloFrame = new OthelloFrame();
            othelloFrame.setVisible(true);
        });
    }

    static void IniciarTablero() {
        for (int i = 0; i < BOARD_SIZE; i++)
         {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Tablero[i][j] = '-';
            }
        }
        Tablero[3][3] = 'B';
        Tablero[3][4] = 'W';
        Tablero[4][3] = 'W';
        Tablero[4][4] = 'B';
    }

    static void Movimiento(int fila, int col, char Jugador) {
        boolean isValidMove = AuxMovimiento(fila, col, Jugador);
        if (isValidMove) {
            Tablero[fila][col] = Jugador;
            GirarDisco(fila, col, Jugador);
            TableroBoton();
            Puntajes();
            JugadorActual = (JugadorActual == 'B') ? 'W' : 'B';
        } else {
            JOptionPane.showMessageDialog(null, "Imagen descubierta ó movimiento invalido. Intente nuevamente");
        }
    }

    static void TableroBoton() {
        for (Component comp : othelloFrame.getTableroPanel().getComponents()) {
            if (comp instanceof DiscoBoton) {
                DiscoBoton button = (DiscoBoton) comp;
                int fila = button.getFila();
                int col = button.getCol();
                button.setDisco(Tablero[fila][col]);
                button.repaint();
            }
        }
        othelloFrame.getTableroPanel().revalidate();
        othelloFrame.getTableroPanel().repaint();
    }

    static void Puntajes() {
        int ContNegro = 0;
        int ContBlanco = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (Tablero[i][j] == 'B') {
                    ContNegro++;
                } else if (Tablero[i][j] == 'W') {
                    ContBlanco++;
                }
            }
        }
        othelloFrame.Puntajes(ContNegro, ContBlanco);
    }

    static boolean AuxMovimiento(int fila, int col, char Jugador) {
        // Verificar si la celda está vacía
        if (Tablero[fila][col] != '-') {
            return false;
        }
        // Iterar sobre todas las direcciones posibles
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Evitar la dirección (0,0) que representa la celda actual
                if (i == 0 && j == 0) {
                    continue;
                }
                // Inicializar las coordenadas del jugador en la dirección actual
                int jugadorFila = fila + i;
                int jugadorColumna = col + j;
                boolean JugadorBuscado = false;
                boolean Secuencia = false;
                List<int[]> giroFicha = new ArrayList<>();
                while (jugadorFila >= 0 && jugadorFila < BOARD_SIZE && jugadorColumna >= 0 && jugadorColumna < BOARD_SIZE) {
                    char Celda = Tablero[jugadorFila][jugadorColumna];
                    if (Celda == Jugador) {
                        Secuencia = JugadorBuscado;
                        break;
                    } else if (Celda == '-') {
                        // No se ha encontrado una secuencia válida, salir del bucle
                        break;
                    } else {
                        JugadorBuscado = true;
                        // Almacenar la coordenada del jugador para voltear discos si es un movimiento válido
                        giroFicha.add(new int[]{jugadorFila, jugadorColumna});
                    }
                    jugadorFila += i;
                    jugadorColumna += j;
                }
                if (Secuencia && JugadorBuscado) {
                    for (int[] ficha : giroFicha) {
                        int giroFila = ficha[0];
                        int giroColumna = ficha[1];
                        Tablero[giroFila][giroColumna] = Jugador;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    static void GirarDisco(int fila, int col, char Jugador) {
        int jugadorFila, jugadorColumna;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                jugadorFila = fila + i;
                jugadorColumna = col + j;
                boolean Direccion = false;
                while (jugadorFila >= 0 && jugadorFila < BOARD_SIZE && jugadorColumna >= 0 && jugadorColumna < BOARD_SIZE) {
                    if (Tablero[jugadorFila][jugadorColumna] == Jugador) {
                        if (Direccion) {
                            int flipFila = fila + i;
                            int flipCol = col + j;

                            while (!(flipFila == jugadorFila && flipCol == jugadorColumna)) {
                                Tablero[flipFila][flipCol] = Jugador;
                                flipFila += i;
                                flipCol += j;
                            }
                        }
                        break;
                    } else if (Tablero[jugadorFila][jugadorColumna] == '-') {
                        break;
                    } else {
                        jugadorFila += i;
                        jugadorColumna += j;
                        Direccion = true;
                    }
                }
            }
        }
    }
}
