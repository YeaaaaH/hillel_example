package spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.util.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
