package br.com.jefferson.calc.view;

import javax.swing.JFrame;
import java.awt.*;

public class Calculadora extends JFrame {

    public Calculadora(){

        organizarLayout();
        setSize(232, 322);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela no centro do monitor
        setVisible(true);
    }

    private Object organizarLayout() {
        setLayout(new BorderLayout());

        Display display = new Display();
        display.setPreferredSize(new Dimension(232, 60));
        add(display, BorderLayout.NORTH);
        Teclado teclado = new Teclado();
        add (teclado, BorderLayout.CENTER);

        return null;
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
