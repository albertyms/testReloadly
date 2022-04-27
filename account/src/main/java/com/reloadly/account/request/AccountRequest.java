package com.reloadly.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountRequest {


    @NotNull(message = "amount cannot be null")
    @JsonProperty(value = "amount")
    private Double amount;
    @JsonProperty(value = "userName")
    @NotNull(message = "userName cannot be null")
    private String userName;
    @JsonProperty(value = "typeAccount")
    @NotNull(message = "typeAccount cannot be null")
    private String typeAccount;

}
