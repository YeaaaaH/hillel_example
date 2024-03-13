package spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.model.Role;
import spring.model.User;
import spring.repository.RoleRepository;
import spring.repository.UserRepository;
import spring.security.model.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Test
    void loadUserByUserNameTest() {
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(createMockUser()));
        UserDetailsImpl userDetails = userService.loadUserByUsername("admin");
        assertNotNull(userDetails);
        assertEquals(2, userDetails.getAuthorities().size());
        assertEquals("admin", userDetails.getUsername());
    }

    private User createMockUser() {
        return User.builder()
                .id(1L)
                .username("admin")
                .email("someemail@gg.com")
                .firstName("firstName")
                .lastName("lastName")
                .roles(List.of(new Role(1L, "ADMIN"), new Role(2L, "CLIENT")))
                .build();
    }
}
