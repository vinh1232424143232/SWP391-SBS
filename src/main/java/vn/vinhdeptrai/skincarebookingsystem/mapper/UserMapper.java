package vn.vinhdeptrai.skincarebookingsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.vinhdeptrai.skincarebookingsystem.dto.request.UserCreationRequest;
import vn.vinhdeptrai.skincarebookingsystem.dto.request.UserUpdateRequest;
import vn.vinhdeptrai.skincarebookingsystem.dto.response.UserResponse;
import vn.vinhdeptrai.skincarebookingsystem.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
