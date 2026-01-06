package com.telusko.demo.controller;

import com.telusko.demo.model.Prod;
import com.telusko.demo.repository.ProductRepository;
import com.telusko.demo.service.Prodservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Prodcontroller {

    @Autowired
    private Prodservice service;

    @RequestMapping("/")
    public String greet() {
        return "Hello World";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Prod>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Prod> getproduct(@PathVariable int id) {
        return new ResponseEntity<>(service.getproductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Prod product, @RequestPart MultipartFile imageFile) {
        try {
            Prod product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Prod product = service.getproductById(productId);
        byte[] imageFile = product.getImageDate();
        String imageType = product.getImageType();

        if (imageType == null || imageType.isBlank()) {
            imageType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageType))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateproduct(@PathVariable int id, @RequestPart Prod product, @RequestPart MultipartFile imageFile) throws IOException {
        Prod product1 = service.updateProduct(id, product, imageFile);

        if (product1 != null) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteproduct(@PathVariable int id) {
        Prod product = service.getproductById(id);
        if (product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product_Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Prod>> searchProducts(@RequestParam String keyword) {
            System.out.println("Searching with : "+keyword);
            List<Prod> products = service.searchProducts(keyword);
            return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
