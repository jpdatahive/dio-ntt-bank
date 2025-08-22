package br.com.dio_ntt.exception;

public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException(String message) {
        super(message);
    }

}