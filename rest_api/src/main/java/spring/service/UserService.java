package spring.service;

import org.springframework.stereotype.Service;
import spring.exception.UserNotFoundException;
import spring.model.User;
import spring.model.dto.UserDTO;
import spring.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Long saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername());
        return userRepository.save(user).getId();
    }
}
