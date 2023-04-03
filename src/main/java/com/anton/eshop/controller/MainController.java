package com.anton.eshop.controller;

import com.anton.eshop.dto.UserDTO;
import com.anton.eshop.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) { // 404-error
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/reg/create")
    public String showUserSignIn(Model model) {
        model.addAttribute("user", new UserDTO());
        return "reg";
    }

    @PostMapping("/reg/create")
    public String createUserSignIn(UserDTO userDTO, Model model) {
        if (Objects.isNull(userDTO)) {
            model.addAttribute("error", "В вашем запросе ошибка.");
            return "redirect:/reg/create";
        }

        userService.save(userDTO);
        return "redirect:/login";
    }
}
