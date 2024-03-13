package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;
import spring.model.dto.UserDTO;
import spring.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<SingInResponseDTO> signin(@RequestBody SignInRequestDTO requestDTO) {
        return new ResponseEntity<>(authService.signin(requestDTO), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(authService.signup(userDTO), HttpStatus.CREATED);
    }
}
