package br.com.jefferson.calc.model;

import java.util.ArrayList;
import java.util.List;

public class MemoriaCalc {

    private enum TipoComando {
        ZERAR, MUDAR_SINAL, PORCENTAGEM, DIVISAO, MULTIPLICACAO, SUBTRACAO, SOMA, IGUAL, VIRGULA, NUMERO
    }

    ;

    private static final MemoriaCalc instancia = new MemoriaCalc();
    private final List<MemoriaObserver> observadores = new ArrayList<>();

    private TipoComando ultimaOperacao = null; //armazena a última operação teclada
    private boolean substituirDisplay = false; //um novo display é usado sempre após pressionar alguma operação
    private String valorAtual = ""; //valor apresentado na tela do display da calculadora
    private String valorBuffer = ""; // valor armazenado na memória da calculadora


    private MemoriaCalc() {

    }

    public static MemoriaCalc getInstancia() {
        return instancia;
    }

    public void adicionarObservador(MemoriaObserver observador) {
        observadores.add(observador);
    }

    public void processarComando(String comando) {

        TipoComando tipoComando = dectarTipoComando(comando);

        if(tipoComando == null){
            return;
        } else if (tipoComando == TipoComando.ZERAR) {
            valorAtual = "";
            valorBuffer = "";
            substituirDisplay = false;
            ultimaOperacao = null;
        }else if (tipoComando == TipoComando.MUDAR_SINAL && valorAtual.contains("-")) {
            valorAtual = valorAtual.substring(1);
        }else if(tipoComando == TipoComando.MUDAR_SINAL && !valorAtual.contains("-")) {
            valorAtual = "-" + valorAtual;
        } else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA){
            valorAtual = substituirDisplay ? comando : valorAtual + comando;
            substituirDisplay = false;
        } else { // se entrou aqui é porque foi pressionado uma operação (+,-,%,*,/, =)
            substituirDisplay = true;
            valorAtual = obterResultadoOperacao();
            valorBuffer = valorAtual;
            ultimaOperacao = tipoComando;

        }

        //notificando os observadores
        observadores.forEach(o -> o.alterarDisplay(getValorAtual()));
    }

    private String obterResultadoOperacao() {
        // quando for a 1ª vez que o método for acionado ou quando pressionar o 'igual'
        if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL){
            return valorAtual;
        }
        double numeroBuffer = Double.parseDouble(valorBuffer.replace(",","."));
        double numeroAtual = Double.parseDouble(valorAtual.replace(",","."));
        double resultado = 0;

        if ( ultimaOperacao == TipoComando.SOMA){
            resultado = numeroBuffer + numeroAtual;
        }else if ( ultimaOperacao == TipoComando.SUBTRACAO){
            resultado = numeroBuffer - numeroAtual;
        }else if ( ultimaOperacao == TipoComando.DIVISAO){
            resultado = numeroBuffer / numeroAtual;
        }else if ( ultimaOperacao == TipoComando.MULTIPLICACAO){
            resultado = numeroBuffer * numeroAtual;
        } else if ( ultimaOperacao == TipoComando.PORCENTAGEM){
            resultado = (numeroBuffer * numeroAtual)/100;
        }else if (ultimaOperacao == TipoComando.MUDAR_SINAL){
            resultado = numeroAtual * -1;
        }

        String resultadoString = Double.toString(resultado).replace(".", ",");
        //para mostrar no display os números inteiros sem casas decimais
        boolean numerosInteiros = resultadoString.endsWith(",0");
        return numerosInteiros ? resultadoString.replace(",0", "") : resultadoString;
    }

    /*
     * identifica qual tecla foi pressionada da calculadora
     */
    private TipoComando dectarTipoComando(String comando) {

        //ignora os zeros à esquerda
        if (valorAtual.isEmpty() && comando == "0") {
            return null;
        }
        //verifica se o valor digitado pode ou não sere convertido em um valor inteiro.
        try {
            Integer.parseInt(comando);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            //se não for um número...
            switch (comando) {
                case ("AC"):
                    return TipoComando.ZERAR;
                case ("+/-"):
                    return TipoComando.MUDAR_SINAL;
                case ("%"):
                    return TipoComando.PORCENTAGEM;
                case ("/"):
                    return TipoComando.DIVISAO;
                case ("x"):
                    return TipoComando.MULTIPLICACAO;
                case ("-"):
                    return TipoComando.SUBTRACAO;
                case ("+"):
                    return TipoComando.SOMA;
                case ("="):
                    return TipoComando.IGUAL;
                case (","):
                    if( !valorAtual.contains(",")){
                        return TipoComando.VIRGULA;
                    }
                default:
                    return null;

            }

        }
    }

    public String getValorAtual() {
        return valorAtual.isEmpty() ? "0" : valorAtual;
    }

    public String getValorBuffer() {
        return valorBuffer;
    }
}
