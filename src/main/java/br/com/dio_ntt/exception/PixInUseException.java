package br.com.dio_ntt.exception;

public class PixInUseException extends RuntimeException {

    public PixInUseException(String message) {
        super(message);
    }

}