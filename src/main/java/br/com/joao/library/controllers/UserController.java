package br.com.joao.library.controllers;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.domain.user.UserDTO;
import br.com.joao.library.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(params = "email")
    public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(new User(userDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
    }

}
