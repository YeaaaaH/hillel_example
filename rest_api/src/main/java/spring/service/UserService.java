package spring.service;

import example.logger.aspect.Loggable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.exception.UserNotFoundException;
import spring.model.Role;
import spring.model.User;
import spring.model.dto.UserDTO;
import spring.repository.RoleRepository;
import spring.repository.UserRepository;
import spring.security.model.UserDetailsImpl;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Loggable
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Long register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        Role role = roleRepository.findByName("CLIENT");
        user.setRoles(List.of(role));
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
    }
}
