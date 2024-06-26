package br.com.joao.library.controllers;

import br.com.joao.library.domain.user.User;
import br.com.joao.library.domain.user.UserDTO;
import br.com.joao.library.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Busca todos os usuários do banco de dados")
    @ApiResponse(responseCode = "200", description = "Retorna todos os usuários")
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(description = "Busca usuário por e-mail no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário com e-mail informado não foi encontrado")
    })
    @GetMapping(params = "email")
    public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
    }

    @Operation(description = "Salva um novo usuário ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios ficaram em branco ou nulo"),
            @ApiResponse(responseCode = "400", description = "E-mail já registrado")
    })
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(new User(userDTO)));
    }

    @Operation(description = "Buscar um usuário por ID no banco de dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retora o usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário com ID informado não foi encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
    }


}
