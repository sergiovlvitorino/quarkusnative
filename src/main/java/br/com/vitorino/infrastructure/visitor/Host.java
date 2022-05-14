package br.com.vitorino.infrastructure.visitor;

import br.com.vitorino.infrastructure.visitor.Visitor;

public interface Host {

    default void receive(Visitor visitor) {
        visitor.visit(this);
    }

}
