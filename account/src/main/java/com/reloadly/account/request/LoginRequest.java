package com.reloadly.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    @JsonProperty(value = "username")
    private String username;

    @NotBlank
    @JsonProperty(value = "password")
    private String password;

}
