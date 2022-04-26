package com.reloadly.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @JsonProperty(value = "username")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @JsonProperty(value = "email")
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    @JsonProperty(value = "password")
    private String password;

}
