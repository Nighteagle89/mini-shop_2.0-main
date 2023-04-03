package com.anton.eshop.repository;

import com.anton.eshop.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    <S extends Product> S save(S s);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(Long aLong);
}
