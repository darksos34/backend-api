package dev.jda.backend.api.profile;

import dev.jda.domain.models.ProfileDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    ProfileDTO toDto(Profile profile);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    Profile toEntity(ProfileDTO dto);
}
