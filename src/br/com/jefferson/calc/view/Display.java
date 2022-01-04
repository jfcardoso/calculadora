package br.com.jefferson.calc.view;

import br.com.jefferson.calc.model.MemoriaCalc;
import br.com.jefferson.calc.model.MemoriaObserver;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements MemoriaObserver {

    private final JLabel label;

    public Display() {
        MemoriaCalc.getInstancia().adicionarObservador(this); // adicionado como listener
        setBackground(new Color(46, 49, 50));
        label = new JLabel(MemoriaCalc.getInstancia().getValorAtual());
        label.setForeground(Color.white);
        label.setFont(new Font("courier", Font.PLAIN, 25));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 23)); //alinhamento à direita
        add(label);
    }

    @Override
    public void alterarDisplay(String novoValor) {
        label.setText(novoValor);
    }
}
