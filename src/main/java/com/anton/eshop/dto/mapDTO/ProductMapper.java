package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.Product;
import com.anton.eshop.dto.ProductDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductMapper MAPPER = new ProductMapper();

    public ProductDTO productMapProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .amount(product.getAmount())
                .build();

    }

    public Product productDTOmapToProduct(ProductDTO productDTO) {
        if (Objects.nonNull(productDTO)) {
            return Product.builder()
                    .id(productDTO.getId())
                    .title(productDTO.getTitle())
                    .price(productDTO.getPrice())
                    .amount(productDTO.getAmount())
                    .build();
        } else
            return null;
    }

    public List<ProductDTO> productsToProductsDTO(List<Product> products) {
        return products.stream()
                .map(this::productMapProductDTO)
                .collect(Collectors.toList());
    }

    public List<Product> productsDTOtoProducts(List<ProductDTO> productsDTO) {
        return productsDTO.stream()
                .map(this::productDTOmapToProduct)
                .collect(Collectors.toList());
    }

}
