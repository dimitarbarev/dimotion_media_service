package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequest {
    @NotNull
    private MultipartFile file;

    @NotNull
    private Long board_id;

    @NotNull
    private Long user_id;
}
