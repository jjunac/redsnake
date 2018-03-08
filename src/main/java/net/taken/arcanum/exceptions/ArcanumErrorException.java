package net.taken.arcanum.exceptions;

public abstract class ArcanumErrorException extends Exception {

    private String name;
    private String message;

    public ArcanumErrorException(String name, String message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, message);
    }
}
