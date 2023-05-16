package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import ClaimService.ClaimService.ClaimService.Exception.EmailAlreadyExistsException;
import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Exception.UsernameAlreadyExistsException;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Request.UserUpdateDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


    }

    //fixed finally
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(userRequestDTO.getUsername());
        }
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(userRequestDTO.getEmail());
        }
        User user = modelMapper.map(userRequestDTO, User.class);
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);
        // default useris bus clientas
        user.setRole(Role.CLIENT);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);

    }

    // nesutvarkiau iki galo
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        if (userRepository.existsByUsername(userUpdateDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(userUpdateDTO.getUsername());
        }
        if (userRepository.existsByEmail(userUpdateDTO.getEmail())) {
            throw new EmailAlreadyExistsException(userUpdateDTO.getEmail());
        }
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        modelMapper.map(userUpdateDTO, existingUser);
        User updatedUser = userRepository.save(existingUser);
        UserResponseDTO dto = modelMapper.map(updatedUser, UserResponseDTO.class);
        return dto;
    }

    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        return userResponseDTO;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());

        return userResponseDTOs;
    }


}