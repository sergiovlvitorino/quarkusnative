package br.com.vitorino.infrastructure.visitor;

public interface Visitor<T, K> {

    K visit(T o);

}
