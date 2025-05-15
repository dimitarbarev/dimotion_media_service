package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private Long id;

    private String name;

    private String type;

    private byte[] data;

    private Long board_id;

    private Long user_id;
}
