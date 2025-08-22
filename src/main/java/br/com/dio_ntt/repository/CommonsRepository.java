package br.com.dio_ntt.repository;

import br.com.dio_ntt.exception.NoFundsEnoughException;
import br.com.dio_ntt.model.Wallet;

public final class CommonsRepository {

    private CommonsRepository() {
        // Construtor privado
    }

    public static void checkFundsForTransaction(final Wallet source, final long amount) {
        if (source.getFunds() < amount) {
            throw new NoFundsEnoughException("Sua conta não tem dinheiro o suficiente para realizar essa transação");
        }
    }
}