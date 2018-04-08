package net.taken.redsnake.interpretor;

import lombok.Getter;

public abstract class Symbol {

    @Getter
    private final String name;

    public Symbol(String name) {
        this.name = name;
    }
}
