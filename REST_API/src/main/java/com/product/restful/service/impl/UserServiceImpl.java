package com.product.restful.service.impl;

import com.product.restful.dto.MessageResponse;
import com.product.restful.dto.user.CreateUserRequest;
import com.product.restful.dto.user.UpdateUserRequest;
import com.product.restful.dto.user.UserIdentityAvailability;
import com.product.restful.dto.user.UserDTO;
import com.product.restful.entity.Role;
import com.product.restful.entity.enumerator.RoleName;
import com.product.restful.entity.user.ResetPassword;
import com.product.restful.entity.user.User;
import com.product.restful.entity.user.UserPassword;
import com.product.restful.mapper.UserMapper;
import com.product.restful.repository.ResetPasswordRepository;
import com.product.restful.repository.RoleRepository;
import com.product.restful.repository.UserPasswordRepository;
import com.product.restful.repository.UserRepository;
import com.product.restful.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordRepository resetPasswordRepository;
    private final UserPasswordRepository userPasswordRepository;
    public final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ResetPasswordRepository resetPasswordRepository, UserPasswordRepository userPasswordRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordRepository = resetPasswordRepository;
        this.userPasswordRepository = userPasswordRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        return new UserIdentityAvailability(!userRepository.existsByUsername(username));
    }

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email) {
        return new UserIdentityAvailability(!userRepository.existsByEmail(email));
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(userId+" not found"));
    }

    @Override
    public void checkUsernameIsExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }
    }

    @Override
    public void checkEmailIsExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already taken");
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.getUserByName(username);
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDTO createAdmin(CreateUserRequest userRequest) {
        checkUsernameIsExists(userRequest.getUsername());
        checkEmailIsExists(userRequest.getEmail());

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .build();

        user.setRoles(new HashSet<>(Collections.singleton(
                roleRepository.getByName(RoleName.ADMIN.name())
                        .orElseThrow(() -> new RuntimeException(USER_ROLE_NOT_SET)))));

        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        checkUsernameIsExists(userDTO.getUsername());
        checkEmailIsExists(userDTO.getEmail());

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setUserActive(false);

        Set<Role> roleSet = new HashSet<>();
        if (userRepository.count() == 0) {//First created user should be admin
            roleSet.add(roleRepository.getByName(RoleName.ADMIN.name())
                    .orElseThrow(() -> new RuntimeException(USER_ROLE_NOT_SET)));
            roleSet.add(roleRepository.getByName(RoleName.USER.name())
                    .orElseThrow(() -> new RuntimeException(USER_ROLE_NOT_SET)));
        }else{
            roleSet.add(roleRepository.getByName(RoleName.USER.name())
                    .orElseThrow(() -> new RuntimeException(USER_ROLE_NOT_SET)));
        }

        user.setRoles(roleSet);

        UserPassword userPassword = new UserPassword();
        userPassword.setUser(user);
//        userPassword.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userPassword.setPassword(userDTO.getPassword());
        user.setUserPassword(userPassword);

        userRepository.save(user);
        userPasswordRepository.save(userPassword);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setUser(user);
        resetPasswordRepository.save(resetPassword);

        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        User user = userRepository.getUserByName(username);
        user.addRole(roleRepository.getByName(roleName).orElseThrow(() -> new RuntimeException(USER_ROLE_NOT_SET)));
        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id+" not found"));
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDTO updateUser(String username, UpdateUserRequest updateUserRequest) {
        User user = userRepository.getUserByName(username);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.getUserByName(username);
        userRepository.deleteById(user.getId());
    }


    @Override
    public void verifyUserByUsernameOrEmail(String usernameOrEmail) {
        userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
    }

}
