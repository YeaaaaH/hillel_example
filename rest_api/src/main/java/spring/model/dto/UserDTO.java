package spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.util.validation.UniqueUsername;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotBlank
    @Size(min = 4, message = "size must be at least 3 symbols")
    @UniqueUsername(message = "user name is not unique")
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
}
