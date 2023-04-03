package com.anton.eshop.service.api;

import com.anton.eshop.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    List<ProductDTO> fetchAll();

    ProductDTO fetchId(Long id);

    void deleteProductById(Long product_id);

}
