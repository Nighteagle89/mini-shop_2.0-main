package com.anton.eshop.controller;

import com.anton.eshop.data.Role;
import com.anton.eshop.dto.UserDTO;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", Role.values());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users";
        }
        userService.save(userDTO);
        model.addAttribute("user", userDTO);
        return "user";
    }

    @GetMapping
    public String userList(Model model) {
        List<UserDTO> users = userService.fetchAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/profile")
    public String profileUser(UserDTO userDTO, Model model, Principal principal) {
        if (Objects.isNull(principal)) {
            throw new RuntimeException("You're not logging!");
        }

        userDTO = UserMapper.MAPPER.userMapUserDTO(
                userService.fetchUserByUsername(principal.getName()));

        model.addAttribute("user", userDTO);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(@Valid UserDTO userDTO, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "redirect:/users/profile";

        if (Objects.nonNull(userDTO.getPassword()) && !userDTO.getPassword().isEmpty() &&
            !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            model.addAttribute("user", userDTO);
            return "profile";
        }

        userService.updateUser(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    private String deleteUser(@PathVariable(name = "id") String user_id) {

        userService.deleteUserById(Long.valueOf(user_id));
        return "redirect:/users";
    }
}
