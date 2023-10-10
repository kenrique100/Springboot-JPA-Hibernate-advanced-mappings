package com.kbf.Api.mapper;

import com.kbf.Api.model.User;
import com.kbf.Api.model.dto.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
}