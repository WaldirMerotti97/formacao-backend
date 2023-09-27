package br.com.waldir.userserviceapi.mapper;

import br.com.waldir.userserviceapi.entity.User;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    UserResponse fromEntity(final User entity);

    @Mapping(target = "id", ignore = true)
    User fromRequest(final CreateUserRequest createUserRequest);
}
