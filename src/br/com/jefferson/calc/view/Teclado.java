package br.com.jefferson.calc.view;

import br.com.jefferson.calc.model.MemoriaCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teclado extends JPanel implements ActionListener {

    private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
    private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
    private final Color COR_LARANJA = new Color(242, 163, 60);

    public Teclado() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints bagConstraints = new GridBagConstraints();

        setLayout(layout);
        /* O atributo 'weight' foi usado para que os botões ocupassem toda a área do teclado,
         * caso contrário ficariam centralizados no meio do teclado.
         */
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;

        //Linha 1
        adicionarBotao("AC", COR_CINZA_ESCURO, bagConstraints, 0, 0);
        adicionarBotao("+/-", COR_CINZA_ESCURO, bagConstraints, 1, 0);
        adicionarBotao("%", COR_CINZA_ESCURO, bagConstraints, 2, 0);
        adicionarBotao("/", COR_LARANJA, bagConstraints, 3, 0);

        //Linha 2
        adicionarBotao("7", COR_CINZA_CLARO, bagConstraints, 0, 1);
        adicionarBotao("8", COR_CINZA_CLARO, bagConstraints, 1, 1);
        adicionarBotao("9", COR_CINZA_CLARO, bagConstraints, 2, 1);
        adicionarBotao("x", COR_LARANJA, bagConstraints, 3, 1);

        //Linha 3
        adicionarBotao("4", COR_CINZA_CLARO, bagConstraints, 0, 2);
        adicionarBotao("5", COR_CINZA_CLARO, bagConstraints, 1, 2);
        adicionarBotao("6", COR_CINZA_CLARO, bagConstraints, 2, 2);
        adicionarBotao("-", COR_LARANJA, bagConstraints, 3, 2);

        //Linha 4
        adicionarBotao("1", COR_CINZA_CLARO, bagConstraints, 0, 3);
        adicionarBotao("2", COR_CINZA_CLARO, bagConstraints, 1, 3);
        adicionarBotao("3", COR_CINZA_CLARO, bagConstraints, 2, 3);
        adicionarBotao("+", COR_LARANJA, bagConstraints, 3, 3);

        //Linha 5
        bagConstraints.gridwidth = 2; // o botão 'zero' irá ocupar o lugar de dois botões.
        adicionarBotao("0", COR_CINZA_CLARO, bagConstraints, 0, 4);
        bagConstraints.gridwidth = 1; // volta ao padrão.
        adicionarBotao(",", COR_CINZA_CLARO, bagConstraints, 2, 4);
        adicionarBotao("=", COR_LARANJA, bagConstraints, 3, 4);

    }

    private void adicionarBotao(String texto, Color cor, GridBagConstraints bc, int x, int y) {

        bc.gridx = x; //coluna
        bc.gridy = y;//linha
        Botao botao = new Botao(texto, cor);
        add(botao, bc);
        botao.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton botao = (JButton) e.getSource();
            //processa o comando e notifica os observadores
            MemoriaCalc.getInstancia().processarComando(botao.getText());
        }

    }
}
