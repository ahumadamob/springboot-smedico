package com.imb2025.smedico.exception;

public class PacienteExpcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PacienteExpcepcion(String mensaje) {
        super(mensaje);
    }
}
