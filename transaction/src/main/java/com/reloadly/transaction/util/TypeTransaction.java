package com.reloadly.transaction.util;

public enum TypeTransaction {

    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal ");

    public final String typeTransaction;

    TypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

}
