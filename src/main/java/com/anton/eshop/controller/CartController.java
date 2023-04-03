package com.anton.eshop.controller;

import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.service.api.CartService;
import com.anton.eshop.service.api.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;


    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String aboutCart(Model model, Principal principal) {
        if (Objects.isNull(principal)) {
            CartDTO cartDTO = new CartDTO();
            model.addAttribute("cart", cartDTO);
        } else {
            CartDTO cartDTO = cartService.getCartByUsername(principal.getName());
            model.addAttribute("cart", cartDTO);
        }
        return "cart";
    }

//    @GetMapping("{cart_id}/delete//{product_id}")
//    public ModelAndView deleteProductFromCart(
//            @PathVariable(name = "cart_id") String cart_id,
//            @PathVariable(name = "product_id") Long product_id,
//            Principal principal, Model model) {
//        if (Objects.nonNull(principal)) {
//            CartDTO cartDTO = cartService.getCartByUsername(principal.getName());
//            cartService.deleteProductByCartIdAndProductId(Long.parseLong(cart_id), product_id);
//            model.addAttribute("cart", cartDTO);
//            return new ModelAndView("redirect:/cart");
//        }
//
//        return new ModelAndView("redirect:/cart");
//    }
}
