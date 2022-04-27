package com.reloadly.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {


    @JsonProperty(value = "holderName")
    private String holderName;
    @JsonProperty(value = "holderLastName")
    private String holderLastName;
    @JsonProperty(value = "holderSurName")
    private String holderSurName;
    @JsonProperty(value = "address")
    private String address;

}
