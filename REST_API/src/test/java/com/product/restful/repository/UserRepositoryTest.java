package com.product.restful.repository;

import com.product.restful.entity.Role;
import com.product.restful.entity.enumerator.RoleName;
import com.product.restful.entity.user.User;
import com.product.restful.entity.user.UserPassword;
import com.product.restful.mock.MockEntityObjects;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {



    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPasswordRepository userPasswordRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Order(1)
    void createUser() {

        User userMock = MockEntityObjects.getEntityMockUser();
        UserPassword userPasswordMock = MockEntityObjects.getEntityMockUserPassword();
//        Set<Role> roleSet = MockEntityObjects.getEntityMockRoles();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getByName(RoleName.ADMIN.name()).get());


        userPasswordMock.setUser(userMock);

        userMock.setUserPassword(userPasswordMock);
        userMock.setRoles(roleSet);
        userPasswordRepository.save(userPasswordMock);

        User user = userRepository.save(userMock);

        assertEquals(userMock.getFirstName(), user.getFirstName());
        assertEquals(userMock.getLastName(), user.getLastName());
        assertEquals(userMock.getUsername(), user.getUsername());
        assertEquals(userMock.getEmail(), user.getEmail());
        assertEquals(userMock.getStatusRecord(), user.getStatusRecord());
        assertEquals(userMock.isUserActive(), user.isUserActive());
        assertEquals(userMock.getUserPassword().getPassword(), user.getUserPassword().getPassword());
    }

    @Test
    @Order(2)
    void updateUser() {

        User user = userRepository.getUserByName(MockEntityObjects.getEntityMockUser().getUsername());
        user.setFirstName("newName");
        user.setLastName("newLastname");

        user = userRepository.save(user);
        assertEquals("newName", user.getFirstName());
        assertEquals("newLastname", user.getLastName());

    }

    @Test
    @Order(3)
    void deleteUser() {

        Optional<User> userOptional = userRepository.findByUsername(MockEntityObjects.getEntityMockUser().getUsername());

        assertTrue(userOptional.isPresent(), "User present");

        User user = userOptional.get();
//        userRepository.delete(user);
        userRepository.delete(user);

        userPasswordRepository.delete(user.getUserPassword());

        Optional<User> userDeleted = userRepository.findByUsername(MockEntityObjects.getEntityMockUser().getUsername());

        assertFalse(userDeleted.isPresent(), "User deleted");

    }




}
