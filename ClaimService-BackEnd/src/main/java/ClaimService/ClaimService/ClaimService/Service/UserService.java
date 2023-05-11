package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.springframework.context.support.BeanDefinitionDsl;
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

        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setName(userRequestDTO.getName());
        user.setSurname(userRequestDTO.getSurname());
        user.setRole(Role.CLIENT);
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(savedUser.getUsername());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setRole(savedUser.getRole());
        return userResponseDTO;
    }
    public User updateUser(Long id, UserRequestDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (userUpdateDTO.getUsername() != null) {
            existingUser.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getPassword() != null) {
            existingUser.setPassword(userUpdateDTO.getPassword());
        }
        if (userUpdateDTO.getName() != null) {
            existingUser.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getSurname() != null) {
            existingUser.setSurname(userUpdateDTO.getSurname());
        }
        if (userUpdateDTO.getEmail() != null) {
            existingUser.setEmail(userUpdateDTO.getEmail());
        }

        return userRepository.save(existingUser);
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
        // Convert User entities to UserResponseDTO objects
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
        return responseDTOs;
    }

    // Helper method to convert User entity to UserResponseDTO
    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        // Map relevant fields from User entity to UserResponseDTO
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        // Add other fields as needed
        return dto;
    }

}