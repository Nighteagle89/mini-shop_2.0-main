package com.anton.eshop.repository;

import com.anton.eshop.data.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query(value = "select i.* from carts c, items i, users u, cart_item ci where u.id=:user_id and " +
                   "c.user_id=u.id and c.id=ci.cart_id and i.id=ci.item_id", nativeQuery = true)
    Iterable<Item> findByUserId(String user_id);
}
