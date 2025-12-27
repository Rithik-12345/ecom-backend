package com.telusko.demo.service;

import com.telusko.demo.model.Prod;
import com.telusko.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Prodservice {

    @Autowired
    private ProductRepository repo;


    public List<Prod> getAllProducts() {
        return repo.findAll();
    }

    public Prod getproductById(int id) {
        return repo.findById(id).get();
    }
}
