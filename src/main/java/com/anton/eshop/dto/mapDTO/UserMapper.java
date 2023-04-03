package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.User;
import com.anton.eshop.dto.UserDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserMapper MAPPER = new UserMapper();

    public UserDTO userMapUserDTO(User user) {
        if (Objects.nonNull(user))
            return UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .number(user.getNumber())
                    .build();
        else
            return null;
    }

    public User userDTOmapToUser(UserDTO userDTO) {
        if (Objects.nonNull(userDTO)) {
            return User.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .number(userDTO.getNumber())
                    .build();

        } else
            return null;
    }

    public List<UserDTO> usersToUsersDTO(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::userMapUserDTO)
                .collect(Collectors.toList());
    }

    public List<User> productsDTOtoProducts(List<UserDTO> usersDTO) {
        return usersDTO.stream()
                .filter(Objects::nonNull)
                .map(this::userDTOmapToUser)
                .collect(Collectors.toList());
    }
}
