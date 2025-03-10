package com.proyecto.ecommerce.backend.infrastructure.rest;

import com.proyecto.ecommerce.backend.application.UserService;
import com.proyecto.ecommerce.backend.domain.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }
}
