package com.anton.eshop.service.impl;

import com.anton.eshop.data.Cart;
import com.anton.eshop.data.Item;
import com.anton.eshop.data.Product;
import com.anton.eshop.dto.ItemDTO;
import com.anton.eshop.dto.mapDTO.ItemMapper;
import com.anton.eshop.repository.CartRepository;
import com.anton.eshop.repository.ItemRepository;
import com.anton.eshop.repository.ProductRepository;
import com.anton.eshop.service.api.ItemService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper mapper = new ItemMapper();

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void create(ItemDTO itemDTO) {
        itemRepository.save(toEntity(itemDTO));
    }

    @Override
    public void createByProduct(Long product_id) {
        Product product = productRepository.getReferenceById(product_id);
        Item item = toEntity(mapper.productMapToItemModel(product));
        item.setProduct(product);
        itemRepository.save(item);
    }

    @Override
    public Item toEntity(ItemDTO m) {
        Product product = new Product();
        product.setId(m.getId());

        Item entity = Item.builder()
                .id(m.getId())
                .title(m.getTitle())
                .product(product)
                .quantity(m.getAmount())
                .price(m.getPrice())
                .build();

        return entity;
    }

    @Override
    public List<Item> toEntityList(List<ItemDTO> items) {
        if (Objects.isNull(items)) return Collections.emptyList();
        return items.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public ItemDTO toModel(Item e) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(e.getProduct().getId());
        itemDTO.setPrice(e.getProduct().getPrice());
        itemDTO.setAmount(e.getProduct().getAmount());
        itemDTO.setTitle(e.getProduct().getTitle());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> toModelList(List<Item> items) {
        if (Objects.isNull(items)) return Collections.emptyList();
        return items.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteItemByCartIdAndProductId(Long cart_id, Long product_id) {
        if (cartRepository.findById(cart_id).isPresent()) {
            Cart cart = cartRepository.findById(cart_id).get();
            cart.getItems().stream()
                    .filter(it -> itemRepository.findById(product_id).isPresent())
                    .forEach(it -> {
                        if (Objects.equals(it.getId(), product_id))
                            itemRepository.deleteById(it.getId());
                    });
        }
    }
}
