package com.telusko.demo.service;

import com.telusko.demo.model.Prod;
import com.telusko.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Prod addProduct(Prod product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());

        product.getImageType(imageFile.getContentType());

        product.setImageDate(imageFile.getBytes());

        return repo.save(product);
    }
}
