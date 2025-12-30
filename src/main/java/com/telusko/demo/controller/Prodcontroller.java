package com.telusko.demo.controller;

import com.telusko.demo.model.Prod;
import com.telusko.demo.repository.ProductRepository;
import com.telusko.demo.service.Prodservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
