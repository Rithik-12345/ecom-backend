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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Prodcontroller {

    @Autowired
    private Prodservice service;

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Prod>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Prod> getproduct(@PathVariable int id){
        return new ResponseEntity<>(service.getproductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Prod product, @RequestPart MultipartFile imageFile){
        try {
            Prod product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
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
}
