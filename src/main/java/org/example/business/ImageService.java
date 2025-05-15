package org.example.business;

import org.example.model.ImageRequest;
import org.example.model.ImageResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    ImageResponse save(ImageRequest request) throws IOException;

    Optional<ImageResponse> findById(Long id);

    Optional<List<ImageResponse>> findByBoardId(Long boardId);

    Optional<List<ImageResponse>> findByUserId(Long userId);

    void deleteByImageId(Long id);

    void deleteByBoardId(Long id);

    void deleteByUserId(Long id);
}
