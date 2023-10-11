package com.example.carsharing.mapper;

import com.example.carsharing.domain.User;
import com.example.carsharing.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDto mapUserToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .isTopCustomer(user.isTopCustomer())
                .build();
    }
    public User mapUserDtoToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .isTopCustomer(userDto.isTopCustomer())
                .build();
    }
    public List<UserDto>mapToUserDtoList(List<User>users){
        return users.stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }
}
