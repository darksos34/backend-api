package dev.jda.backend.api.user;

import dev.jda.domain.models.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .code(user.getCode())
                .build();
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .code(dto.getCode())
                .build();
    }
}
