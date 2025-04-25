package vn.huynguyen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.huynguyen.dto.request.UserRequestDTO;
import vn.huynguyen.dto.response.ResponseData;
import vn.huynguyen.dto.response.ResponseErorr;
import vn.huynguyen.dto.response.UserDetailResponseDTO;
import vn.huynguyen.exception.ResourceNotFoundException;
import vn.huynguyen.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        try {
            Long userId = userService.saveUser(userRequestDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User created successfully!", userId);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e.getCause());
            return new ResponseErorr(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable String userId, @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully!");
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable String userId, @RequestParam boolean status) {
        return new ResponseData(HttpStatus.ACCEPTED.value(), "User status changed successfully!");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable String userId) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully!");
    }

    @GetMapping("/{userId}")
    public ResponseData<?> getUser(@PathVariable Long userId) {

        try {
            return new ResponseData<>(HttpStatus.OK.value(), "User get successfully!", userService.getUser(userId));
        } catch (ResourceNotFoundException e) {
            log.error("Error getting user: {}", e.getMessage(), e.getCause());
            return new ResponseErorr(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @GetMapping("/list")
    public ResponseData<List<UserDetailResponseDTO>> getAllUser(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {


        return new ResponseData<>(HttpStatus.OK.value(), "Get all user successfully!", userService.getAllUsers(pageNo, pageSize));
    }
}
