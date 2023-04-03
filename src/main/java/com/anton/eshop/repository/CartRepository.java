package com.anton.eshop.repository;

import com.anton.eshop.data.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {

}
