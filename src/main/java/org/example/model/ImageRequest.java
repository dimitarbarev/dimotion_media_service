package org.example.model;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequest {
    @NonNull
    private MultipartFile file;

    @NotNull
    private Long board_id;

    @NotNull
    private Long user_id;
}
