package com.example.cuentaservice.exception;

public class BalanceInsuficienteException extends RuntimeException {
    public BalanceInsuficienteException(String message) {
        super(message);
    }
}
