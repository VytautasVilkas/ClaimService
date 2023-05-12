package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.ClaimService.Validations.UserValidator;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Request.UserUpdateDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserValidator userValidator;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;

    }
    //fixed finally
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
    public User updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (userUpdateDTO.username() != null) {
            existingUser.setUsername(userUpdateDTO.username());
        }
        if (userUpdateDTO.email() != null) {
            existingUser.setEmail(userUpdateDTO.email());
        }
        if (userUpdateDTO.name() != null) {
            existingUser.setName(userUpdateDTO.name());
        }
        if (userUpdateDTO.surname() != null) {
            existingUser.setSurname(userUpdateDTO.surname());
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
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
        return responseDTOs;
    }
    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
    public List<String> validateUser(UserRequestDTO userUpdateDTO) {
        BindingResult bindingResult = new BeanPropertyBindingResult(userUpdateDTO, "userRequestDTO");
        userValidator.validate(userUpdateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}