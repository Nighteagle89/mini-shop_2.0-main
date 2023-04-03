package com.anton.eshop.dto;

import com.anton.eshop.data.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 4, max = 16)
    private String password;

    @NotBlank
    @Size(min = 4, max = 16)
    private String matchingPassword;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String number;

    private Role role = Role.CLIENT;
}
