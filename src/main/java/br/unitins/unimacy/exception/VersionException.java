package br.unitins.unimacy.exception;

import java.io.Serial;

public class VersionException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public VersionException(String message) {
        super(message);
    }
}
