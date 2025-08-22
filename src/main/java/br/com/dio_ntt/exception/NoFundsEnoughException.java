package br.com.dio_ntt.exception;

public class NoFundsEnoughException extends RuntimeException {

    public NoFundsEnoughException(String message) {
        super(message);
    }
}
