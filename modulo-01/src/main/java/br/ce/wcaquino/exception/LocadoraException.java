package br.ce.wcaquino.exception;

public class LocadoraException extends Exception {

    public LocadoraException() {
    }

    public LocadoraException(String message) {
        super(message);
    }

    public LocadoraException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocadoraException(Throwable cause) {
        super(cause);
    }
}
