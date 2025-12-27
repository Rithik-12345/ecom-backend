package com.telusko.demo.repository;

import com.telusko.demo.model.Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Prod, Integer> {
}
