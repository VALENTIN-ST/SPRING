package com.product.restful.controller;

import com.product.restful.dto.BaseController;
import com.product.restful.dto.MessageResponse;
import com.product.restful.dto.WebResponse;
import com.product.restful.dto.role.CreateRoleRequest;
import com.product.restful.dto.user.CreateUserRequest;
import com.product.restful.dto.user.UpdateUserRequest;
import com.product.restful.dto.user.UserDTO;
import com.product.restful.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public BaseResponse<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        final UserDTO userDto = userService.createUser(user);
        return ok(userDto);
    }

    @PutMapping(value = "/{username}")
//    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    public BaseResponse<UserDTO> updateUser(@Valid @RequestBody UpdateUserRequest newUser, @PathVariable(name = "username") String username) {
        final UserDTO userDto = userService.updateUser(username, newUser);
        return ok(userDto);
    }

    @PutMapping(value = "/addRole")
//    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public BaseResponse<UserDTO>  addRoleToUser(@RequestParam(required = true) String username, @RequestBody CreateRoleRequest createRoleRequest) {
        UserDTO userDto = userService.addRoleToUser(username, createRoleRequest.getName().toUpperCase());
        return ok(userDto);
    }

    @DeleteMapping(value = "/deleteUser/{username}")
//    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable(name = "username") String username) {
        userService.deleteUser(username);
        return "You successfully deleted profile of: " + username;
    }

}
