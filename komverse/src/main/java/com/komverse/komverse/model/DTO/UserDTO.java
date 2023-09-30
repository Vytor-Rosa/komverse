package com.komverse.komverse.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
}
