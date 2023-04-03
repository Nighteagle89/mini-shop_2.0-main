package com.anton.eshop.service.api;

import com.anton.eshop.data.User;
import com.anton.eshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    List<UserDTO> fetchAll();

    User fetchUserByUsername(String username);

    void updateUser(UserDTO userDTO);

    void deleteUserById(Long user_id);
}
