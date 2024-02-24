package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
