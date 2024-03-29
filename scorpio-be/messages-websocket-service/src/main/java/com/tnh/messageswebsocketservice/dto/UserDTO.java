package com.tnh.messageswebsocketservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

    private String id;

    @NotBlank
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String username;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 6, max = 32)
    private String password;

    @Pattern(regexp = "^[a-zA-Z]*$")
    @Size(min = 2, max = 50)
    @NotBlank
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$")
    @Size(min = 2, max = 50)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String activationKey;


    public UserDTO(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
