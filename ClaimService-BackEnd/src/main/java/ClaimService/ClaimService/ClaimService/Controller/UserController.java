package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.ClaimService.Service.UserService;
import ClaimService.ClaimService.DTO.Request.UserLoginRequest;
import ClaimService.ClaimService.DTO.Request.UserUpdateDTO;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.ObjectError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;

        this.userRepository = userRepository;
    }

    // finally fixed
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errorMessages = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        UserResponseDTO savedUserDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(savedUserDTO);
    }
    // needs fixing
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            UserResponseDTO dto = userService.updateUser(id, userUpdateDTO);
            return ResponseEntity.ok("User updated successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> checkUserCredentials(@Valid @RequestBody UserLoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());

            if (isPasswordValid) {
                UserResponseDTO response = modelMapper.map(user, UserResponseDTO.class);
                response.setValid(true);

                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("isValidUser", true);
                resultMap.put("user", response);

                return ResponseEntity.ok(resultMap);
            }
        }

        return ResponseEntity.ok().body(new HashMap<>());
    }
}
