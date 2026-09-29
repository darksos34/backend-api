package dev.jda.backend.api.user;

import dev.jda.backend.api.profile.ProfileRepresentationAssembler;
import dev.jda.domain.models.UserDTO;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
@NullMarked
public class UserRepresentationAssembler
        implements RepresentationModelAssembler<User, UserDTO> {

    private final UserMapper userMapper;
    private final ProfileRepresentationAssembler profileAssembler;

    @Override
    public  UserDTO toModel(User entity) {

        UserDTO dto = userMapper.toDto(entity);

        dto.setProfiles(
                entity.getProfiles() == null
                        ? List.of()
                        : entity.getProfiles().stream()
                        .map(profileAssembler::toModel)
                        .toList()
        );

        dto.add(
                linkTo(
                        methodOn(UserController.class)
                                .getUserByUuid(entity.getUuid())
                ).withSelfRel()
        );

        return dto;
    }
}
