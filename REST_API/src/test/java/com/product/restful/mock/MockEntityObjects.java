package com.product.restful.mock;

import com.product.restful.entity.Role;
import com.product.restful.entity.enumerator.RoleName;
import com.product.restful.entity.enumerator.StatusRecord;
import com.product.restful.entity.user.User;
import com.product.restful.entity.user.UserPassword;

import java.util.HashSet;
import java.util.Set;

public class MockEntityObjects {

    public static User getEntityMockUser() {

            User user = User.builder()
                    .username("Pero123")
                    .firstName("Pero")
                    .lastName("Perovski")
                    .username("pero123")
                    .email("pero12333@gmail.com")
                    .statusRecord(StatusRecord.ACTIVE)
                    .userActive(true)
                    .build();

                return user;
    }

    public static UserPassword getEntityMockUserPassword() {

        UserPassword userPassword = UserPassword.builder()
                .password("pass123").build();

        return userPassword;
    }


    public static Role getEntityMockRoleAdmin() {
        return Role.builder()
//                .id(1l)
                .name(RoleName.ADMIN)
                .statusRecord(StatusRecord.ACTIVE)
                .build();
    }

    public static Role getEntityMockRoleUser() {
        return Role.builder()
//                .id(1l)
                .name(RoleName.USER)
                .statusRecord(StatusRecord.ACTIVE)
                .build();
    }


}