package com.anton.eshop.service.api;

import com.anton.eshop.data.Item;
import com.anton.eshop.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void create(ItemDTO itemDTO);

    void createByProduct(Long product_id);

    Item toEntity(ItemDTO m);

    List<Item> toEntityList(List<ItemDTO> items);

    ItemDTO toModel(Item e);

    List<ItemDTO> toModelList(List<Item> items);

    void deleteItemByCartIdAndProductId(Long cart_id, Long product_id);
}
