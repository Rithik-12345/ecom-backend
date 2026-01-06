package com.telusko.demo.repository;

import com.telusko.demo.model.Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Prod, Integer> {

    //JPQL -> JPA Query Language
    @Query("SELECT p from Prod p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Prod> searchProducts(String keyword);
}
