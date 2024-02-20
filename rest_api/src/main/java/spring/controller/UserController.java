package spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.exception.UserNotFoundException;
import spring.model.User;
import spring.model.dto.UserDTO;
import spring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.getUserById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllPayments() {
        return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDTO userDTO) {
        Long user = userRepository.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
