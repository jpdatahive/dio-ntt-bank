package br.com.dio_ntt.exception;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {
        super(message);
    }

}
