package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.Product;
import com.anton.eshop.dto.ItemDTO;
import com.anton.eshop.dto.ProductDTO;

public class ItemMapper {
    public static final ItemMapper MAPPER = new ItemMapper();

    public ItemDTO productDTOMapToItemModel(ProductDTO productDTO) {
        return new ItemDTO(
                productDTO.getId(),
                productDTO.getTitle(),
                productDTO.getPrice(),
                productDTO.getAmount()
        );
    }

    public ItemDTO productMapToItemModel(Product product) {
        return new ItemDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getAmount()
        );
    }
}