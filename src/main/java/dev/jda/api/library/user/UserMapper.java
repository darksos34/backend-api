package dev.jda.api.library.user;

import dev.jda.model.library.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    // Ignores the list of profiles when mapping to DTO if UserDTO doesn't have it
    @Mapping(target = "profiles", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "profiles", ignore = true)
    User toEntity(UserDTO dto);
}
