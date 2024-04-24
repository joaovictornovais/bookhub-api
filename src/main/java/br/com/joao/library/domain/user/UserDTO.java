package br.com.joao.library.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public record UserDTO(
        @NotBlank(message = "First Name should not be blank")
        String firstName,
        @NotBlank(message = "Last Name should not be blank")
        String lastName,
        @NotBlank(message = "E-mail should not blank")
        @Email(message = "Example of an valid e-mail: your_name@your_provider.com")
        String email
) {
}
