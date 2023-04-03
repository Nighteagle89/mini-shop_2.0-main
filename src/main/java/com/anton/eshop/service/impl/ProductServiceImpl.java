package com.anton.eshop.service.impl;

import com.anton.eshop.data.Product;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.dto.mapDTO.ItemMapper;
import com.anton.eshop.dto.mapDTO.ProductMapper;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.repository.CartRepository;
import com.anton.eshop.repository.ProductRepository;
import com.anton.eshop.service.api.CartService;
import com.anton.eshop.service.api.ItemService;
import com.anton.eshop.service.api.ProductService;
import com.anton.eshop.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper = ProductMapper.MAPPER;
    private final ItemMapper itemMapper = ItemMapper.MAPPER;
    private final UserMapper userMapper = UserMapper.MAPPER;

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    private final UserService userService;
    private final ItemService itemService;
    private final CartService cartService;


    public ProductServiceImpl(ProductRepository productRepository, CartRepository cartRepository, UserService userService, ItemService itemService, CartService cartService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @Override
    public List<ProductDTO> fetchAll() {
        return productMapper.productsToProductsDTO(productRepository.findAll());
    }

    @Override
    public ProductDTO fetchId(Long id) {
        ProductDTO productDTO = new ProductDTO();
        for (Product product : productRepository.findAll()) {
            if (product.getId().equals(id)) {
                productDTO = productMapper.productMapProductDTO(product);
                break;
            }
        }

        return productDTO;
    }

    @Override
    public void deleteProductById(Long product_id) {
        if (productRepository.existsById(product_id)) {
            productRepository.deleteById(product_id);
        }
    }


    @Override
    public void create(ProductDTO productDTO) {
        if (Objects.nonNull(productDTO)) {
            Product product = Product.builder()
                    .id(productDTO.getId())
                    .title(productDTO.getTitle())
                    .amount(productDTO.getAmount())
                    .price(productDTO.getPrice())
                    .items(new ArrayList<>())
                    .build();
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product is null");
        }
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product updateProduct = productRepository.getReferenceById(productDTO.getId());
        boolean isCheck = false;

        if (!Objects.equals(updateProduct.getPrice(), productDTO.getPrice())) {
            updateProduct.setPrice(productDTO.getPrice());
            isCheck = true;
        }

        if (!Objects.equals(updateProduct.getTitle(), productDTO.getTitle()))  {
            updateProduct.setTitle(productDTO.getTitle());
            isCheck = true;
        }

        if (!Objects.equals(updateProduct.getAmount(), productDTO.getAmount()))  {
            updateProduct.setAmount(productDTO.getAmount());
            isCheck = true;
        }

        if (isCheck) productRepository.save(updateProduct);
    }


}
