package com.product.restful.controller;

import com.product.restful.dto.BaseController;
import com.product.restful.dto.WebResponse;
import com.product.restful.dto.product.CreateProductRequest;
import com.product.restful.dto.product.ProductDTO;
import com.product.restful.dto.product.UpdateProductRequest;
import com.product.restful.dto.user.CreateUserRequest;
import com.product.restful.dto.user.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest extends BaseController {


    @Autowired
    UserController userController;

    @Test
    @Order(1)
    void createUser() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username("perofero")
                .firstName("pero")
                .lastName("perovski")
                .password("abc123")
                .email("pero@gmail.com")
                .build();
//        BaseResponse<UserDTO> createUserResponse = userController.createUser(createUserRequest);
//        assertEquals(createUserRequest.getUsername() , createUserResponse.get);



//        assertEquals(HttpStatus.CREATED, createProductResponse.getStatus());
//        assertNotNull(createProductResponse.getData());
//        assertNotNull(createProductResponse.getData().getId());
//        assertNotNull(createProductResponse.getData().getCreatedAt());

    }

//    @Test
//    @Order(2)
//    void getProductById() throws RuntimeException {
//        // masukkan id, yang sudah tersimpan
//        String productId = "macbook-pro-14-2021";
//        ResponseEntity<WebResponse<ProductDTO>> getProductResponse = productController.getProductById(productId);
////        assertEquals(HttpStatus.OK.value(), getProductResponse.getCode());
//        assertEquals(HttpStatus.OK, getProductResponse.getStatus());
//        assertEquals(productId, getProductResponse.getData().getId());
//
//        log.info("ID: {}", getProductResponse.getData().getId());
//        log.info("Name: {}", getProductResponse.getData().getName());
//        log.info("Price: {}", getProductResponse.getData().getPrice());
//        log.info("Quantity: {}", getProductResponse.getData().getQuantity());
//        log.info("Description: {}", getProductResponse.getData().getDescription());
//        log.info("Created By: {}", getProductResponse.getData().getCreatedBy());
//        log.info("Created At: {}", getProductResponse.getData().getCreatedAt());
//        log.info("Updated By: {}", getProductResponse.getData().getUpdatedBy());
//        log.info("Updated At: {}", getProductResponse.getData().getUpdatedAt());
//    }
//
//    @Test
//    @Order(3)
//    void updateProduct() throws RuntimeException {
//        String id = "hp-pavilion-x360";
//        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
//                .name("Test Update Controller")
//                .price(new BigDecimal("6789000"))
//                .description("This is description controller")
//                .quantity(5678)
//                .build();
//        WebResponse<GetProductResponse> updateProductResponse = productController.updateProduct(id, updateProductRequest);
//        assertEquals(HttpStatus.OK.value(), updateProductResponse.getCode());
//        assertEquals(HttpStatus.OK, updateProductResponse.getStatus());
//        assertEquals(id, updateProductResponse.getData().getId());
//        assertNotNull(updateProductResponse.getData().getUpdatedAt());
//
//        log.info("ID: {}", updateProductResponse.getData().getId());
//        log.info("Name: {}", updateProductResponse.getData().getName());
//        log.info("Price: {}", updateProductResponse.getData().getPrice());
//        log.info("Quantity: {}", updateProductResponse.getData().getQuantity());
//        log.info("Description: {}", updateProductResponse.getData().getDescription());
//        log.info("Created By: {}", updateProductResponse.getData().getCreatedBy());
//        log.info("Created At: {}", updateProductResponse.getData().getCreatedAt());
//        log.info("Updated By: {}", updateProductResponse.getData().getUpdatedBy());
//        log.info("Updated At: {}", updateProductResponse.getData().getUpdatedAt());
//    }
//
//    @Test
//    @Order(4)
//    void deleteProduct() throws RuntimeException {
//        String id = "hp-envy-x360";
//        WebResponse<String> response = productController.deleteProduct(id);
//        assertEquals(HttpStatus.OK.value(), response.getCode());
//        assertEquals(HttpStatus.OK, response.getStatus());
//        assertNull(response.getData());
//        log.info("Product: {}", response);
//    }
//
//    @Test
//    @Order(5)
//    void getAllProduct() {
//        WebResponse<List<GetProductResponse>> getProductResponse = productController.getAllProduct();
//        assertEquals(HttpStatus.OK.value(), getProductResponse.getCode());
//        assertEquals(HttpStatus.OK, getProductResponse.getStatus());
//        // total data 15, karena sudah dihapus 1, maka sisa 14
//        assertEquals(14, getProductResponse.getData().size());
//    }
}