package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Service.UserService;
import ClaimService.ClaimService.ClaimService.Validations.UserValidator;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        BindingResult bindingResult = new BeanPropertyBindingResult(userRequestDTO, "userRequestDTO");
        userValidator.validate(userRequestDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        UserResponseDTO savedUserDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(savedUserDTO);
    }
    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userUpdateDTO) {
        try {
//            userUpdateDTO.setId(id);
            userService.updateUser(id,userUpdateDTO);
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
}
