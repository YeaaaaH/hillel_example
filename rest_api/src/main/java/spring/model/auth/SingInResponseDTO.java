package spring.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingInResponseDTO {
    String accessToken;
}
