package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Request.UserUpdateDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userUpdateDTO.getUsername());
            user.setPassword(userUpdateDTO.getPassword());
            user.setEmail(userUpdateDTO.getEmail());
            user.setName(userUpdateDTO.getName());
            user.setSurname(userUpdateDTO.getSurname());
            user.setRole(userUpdateDTO.getRole());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException(userUpdateDTO.getId());
        }
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