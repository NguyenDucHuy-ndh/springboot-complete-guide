package vn.huynguyen.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.huynguyen.dto.request.UserRequestDTO;
import vn.huynguyen.dto.response.ResponseData;
import vn.huynguyen.dto.response.ResponseErorr;
import vn.huynguyen.dto.response.ResponseSuccess;
import vn.huynguyen.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private UserService userService;

    @PostMapping("/")
    public ResponseData<UserRequestDTO> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        try {
            userService.addUser(userRequestDTO);
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error occurred while creating user!");
        } catch (Exception e) {
            return new ResponseErorr(HttpStatus.BAD_REQUEST.value(), "Save user fail");

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
    public ResponseData<?> getUser(@PathVariable String userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "User get successfully!");
    }
}
