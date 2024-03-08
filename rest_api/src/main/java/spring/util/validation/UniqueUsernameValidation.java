package spring.util.validation;

import spring.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidation implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    public UniqueUsernameValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(username);
    }
}
