package br.com.jefferson.calc.view;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {

    public Botao(String texto, Color cor) {
        setText(texto);
        setOpaque(true);
        setBackground(cor);
        setForeground(Color.white);
        setFont(new Font("courier", Font.PLAIN, 15));
        setBorder(BorderFactory.createLineBorder(Color.black));

    }
}
