import javax.swing.*;

public class DiscoBoton extends JButton {

    private char DiscoFicha;
    private int fila;
    private int col;

    public DiscoBoton(char disk, int fila, int col) {
        this.DiscoFicha = disk;
        this.fila = fila;
        this.col = col;
        setIcon(new ImageIcon(getImagen()));
    }

    public void setDisco(char disk) {
        this.DiscoFicha = disk;
        setIcon(new ImageIcon(getImagen()));
        revalidate();
        repaint();
        validate();
    }

    public int getFila() {
        return fila;
    }

    public int getCol() {
        return col;
    }

    private String getImagen() {
        if (DiscoFicha == 'B') {
            return "C:\\Users\\pablo\\OneDrive\\Desktop\\ProyectoOthello\\Othello\\lib\\OIP.jpg";
        } else if (DiscoFicha == 'W') {
            return "C:\\Users\\pablo\\OneDrive\\Desktop\\ProyectoOthello\\Othello\\lib\\lambo.jpg";
        } else {
            return "";
        }
    }
}
