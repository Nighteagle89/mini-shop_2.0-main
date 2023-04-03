package com.anton.eshop.service.api;

import com.anton.eshop.dto.CartDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    void addItemInCart(String username, Long productId);

    CartDTO getCartByUsername(String username);
}
