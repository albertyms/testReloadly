package com.reloadly.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountRequest {

    @JsonProperty(value = "identificationNumber")
    @NotNull(message = "identificationNumber cannot be null")
    private String identificationNumber;
    @JsonProperty(value = "holderName")
    @NotNull(message = "holderName cannot be null")
    private String holderName;
    @JsonProperty(value = "holderLastName")
    @NotNull(message = "holderLastName cannot be null")
    private String holderLastName;
    @JsonProperty(value = "holderSurName")
    private String holderSurName;
    @NotNull(message = "address cannot be null")
    @JsonProperty(value = "address")
    private String address;
    @NotNull(message = "amount cannot be null")
    @JsonProperty(value = "amount")
    private Double amount;
    @NotNull(message = "email cannot be null")
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "userName")
    @NotNull(message = "userName cannot be null")
    private String userName;
    @JsonProperty(value = "typeAccount")
    @NotNull(message = "typeAccount cannot be null")
    private String typeAccount;

}
