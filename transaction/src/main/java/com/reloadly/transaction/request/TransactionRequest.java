package com.reloadly.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionRequest {


    @JsonProperty(value = "account")
    private Long accountId;
    @JsonProperty(value = "amount")
    private Double amount;
    @JsonProperty(value = "typeTransaction")
    private String typeTransaction;

}
