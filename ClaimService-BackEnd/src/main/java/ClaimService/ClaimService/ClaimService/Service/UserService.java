package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Convert UserRequestDTO to User entity
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setName(userRequestDTO.getName());
        user.setSurname(userRequestDTO.getSurname());
        user.setRole(userRequestDTO.getRole());
        // Hash the password
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);
        // Save the user entity
        User savedUser = userRepository.save(user);
        // Convert saved User entity to UserResponseDTO
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(savedUser.getUsername());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setRole(savedUser.getRole());
        return userResponseDTO;
    }
    public UserResponseDTO getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponseDTO userDTO = new UserResponseDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            return userDTO;
        } else {
            throw new UserNotFoundException(userId);
        }
    }
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
    private UserResponseDTO convertToUserDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}


