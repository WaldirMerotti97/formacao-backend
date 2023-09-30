package br.com.waldir.userserviceapi.mapper;

import br.com.waldir.userserviceapi.entity.User;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    UserResponse fromEntity(final User entity);

    @Mapping(target = "id", ignore = true)
    User fromRequest(final CreateUserRequest createUserRequest);

    @Mapping(target = "id", ignore = true)
    User update(UpdateUserRequest updateUserRequest, @MappingTarget User entity);
}
