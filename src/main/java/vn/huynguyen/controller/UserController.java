package vn.huynguyen.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.huynguyen.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/")
    public String addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return "User created successfully!";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @RequestBody UserRequestDTO userRequestDTO) {
        return "User updated successfully!";
    }

    @PatchMapping("/{userId}")
    public String partialUpdateUser(@PathVariable String userId, @RequestBody UserRequestDTO userRequestDTO) {
        return "User partially updated successfully!";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "User deleted successfully!";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable String userId) {
        return "User details retrieved successfully!";
    }
}
