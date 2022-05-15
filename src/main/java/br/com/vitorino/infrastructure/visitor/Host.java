package br.com.vitorino.infrastructure.visitor;

public interface Host {

    default void receive(Visitor visitor) {
        visitor.visit(this);
    }

}
