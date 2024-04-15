package br.com.joao.library.controllers;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.domain.user.UserDTO;
import br.com.joao.library.services.UserService;
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

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.create(new User(userDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUser(id));
    }

}
