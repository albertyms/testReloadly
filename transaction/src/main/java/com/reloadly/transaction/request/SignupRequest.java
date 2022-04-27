package com.reloadly.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @NotNull(message = "username cannot be null")
    @JsonProperty(value = "username")
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @NotNull(message = "email cannot be null")
    @Size(max = 50)
    @Email
    @JsonProperty(value = "email")
    private String email;
    private Set<String> role;
    @NotBlank
    @Size(min = 6, max = 40)
    @JsonProperty(value = "password")
    private String password;
    @NotBlank
    @JsonProperty(value = "identificationNumber")
    @NotNull(message = "identificationNumber cannot be null")
    private Long identificationNumber;
    @NotBlank
    @NotNull(message = "username cannot be null")
    @JsonProperty(value = "holderName")
    @NotNull(message = "holderName cannot be null")
    private String holderName;
    @NotBlank
    @NotNull(message = "username cannot be null")
    @JsonProperty(value = "holderLastName")
    @NotNull(message = "holderLastName cannot be null")
    private String holderLastName;
    @NotBlank
    @NotNull(message = "username cannot be null")
    @JsonProperty(value = "holderSurName")
    private String holderSurName;
    @NotBlank
    @NotNull(message = "username cannot be null")
    @NotNull(message = "address cannot be null")
    @JsonProperty(value = "address")
    private String address;

}
