package com.imb2025.smedico.exception;

/**
 * Excepción personalizada para indicar que un EstadoTurno no existe.
 */
public class EstadoTurnoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EstadoTurnoNotFoundException(String message) {
        super(message);
    }
}

