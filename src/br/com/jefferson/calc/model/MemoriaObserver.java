package br.com.jefferson.calc.model;

@FunctionalInterface
public interface MemoriaObserver {

    void alterarDisplay(String novoValor);
}
