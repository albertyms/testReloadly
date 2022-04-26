package com.reloadly.transaction.util;

public enum TypeAccount {

    SAVING("Saving"),
    CHECKING("Checking");

    public final String typeRole;

    TypeAccount(String typeRole) {
        this.typeRole = typeRole;
    }

}
