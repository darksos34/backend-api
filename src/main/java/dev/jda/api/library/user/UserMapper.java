package dev.jda.api.library.user;

import dev.jda.api.library.profile.Profile;
import dev.jda.model.library.dto.ProfileDTO;
import dev.jda.model.library.dto.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    ProfileDTO toDto(Profile profile);

    Profile toEntity(ProfileDTO dto);
}