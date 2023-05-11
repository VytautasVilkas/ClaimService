package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Service.UserService;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        UserResponseDTO savedUserDTO = userService.createUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
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
